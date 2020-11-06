package com.game.myapp.module.sign;

import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.LogfType;
import com.game.common.logf.impl.RoleSignLog;
import com.game.common.user.UserBean;
import com.game.core.handler.MsgBack;
import com.game.db.entity.SignEntity;
import com.game.generate.bean.HeroBean;
import com.game.generate.bean.SignBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.function.ISystemId;
import com.game.myapp.module.item.EItemType;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.utils.TimeUtils;
import com.game.vo.item.ItemVo;

import buffer.CGReceiveSevenSignRewardMsg;
import buffer.GCGetSevenSignStatusMsg;
import buffer.GCGetSevenSignStatusMsg.GCGetSevenSignStatusProto;
import buffer.GCReceiveSevenSignRewardMsg;

/**
 * 签到管理类
 */
public class SignManager {
	private final int SUCCESS = 0;
	private final int PARAM_ERROR = 1;// 参数错误
	private final int DAY_ERROR = 2;// 领取天数与服务器不符
	private final int FAILD = 3;// 签到失败

	private final int NOT_REWARD = 0;// 不可领取
	private final int CAN_REWARD = 1;// 可领取
	private final int ALREADY_REWARD = 2;// 已领取

	/**
	 * 七日签到
	 * 
	 * @param msgBack
	 * 
	 * @return
	 * @
	 */
	public void CGReceiveSevenSignRewardMsg(MsgBack msgBack, long uid, CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto proto) {
		UserBean user = Globals.getUserManager().getUserBean(uid);
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			GCReceiveSevenSignRewardMsg.GCReceiveSevenSignRewardProto.Builder builder = GCReceiveSevenSignRewardMsg.GCReceiveSevenSignRewardProto.newBuilder();
			msgBack.addBuilder(builder);
			int day = proto.getDay();
			if (day < 1 || day > 7) {
				builder.setStatus(PARAM_ERROR);
				return;
			}

			SignEntity signEntity = getSignEntity(uid);
			if (signEntity.getSevenSignday() + 1 != day) {
				builder.setStatus(DAY_ERROR);
				return;
			}

			// 检测今天是否已经签过了
			if (TimeUtils.isToday(signEntity.getSevenSignTime())) {
				builder.setStatus(DAY_ERROR);
				return;
			}

			SignBean bean = Datas.get(SignBean.class, day);
			if (bean == null) {
				builder.setStatus(FAILD);
				return;
			}

			ItemVo itemVo = bean.getItemVo();
			// 存在英雄转换为英雄碎片
			if (EItemType.HERO.is(itemVo.getType()) && GameGlobals.heroManager.hasHero(uid, itemVo.getId())) {
				HeroBean heroBean = Datas.get(HeroBean.class, itemVo.getId());
				itemVo = heroBean.getItem();
			}

			GameGlobals.bagManager.addItem(uid, itemVo, LogfConstants.CHANNEL_SEVEN_SIGN, String.valueOf(bean.getId()));
			builder.setStatus(SUCCESS);
			builder.setReward(itemVo.toItemString());

			// 更新数据库
			signEntity.setSevenSignday(day);
			signEntity.setSevenSignTime(System.currentTimeMillis());
			Globals.getEntityProxy().updateAsync(signEntity);
			// 检测小红点
			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.LOBBY_SIGN);
			// 日志打点
			signLog(user, LogfType.ACTIVITY_SEVEN_SIGN, day, itemVo.getType(), itemVo.getId(), itemVo.getNum());
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 获取七日签到的状态
	 * 
	 * @param msgBack
	 */
	public void CGGetSevenSignStatusMsg(MsgBack msgBack, long uid) {
		GCGetSevenSignStatusMsg.GCGetSevenSignStatusProto.Builder builder = GCGetSevenSignStatusMsg.GCGetSevenSignStatusProto.newBuilder();
		msgBack.addBuilder(builder);
		SignEntity signEntity = getSignEntity(uid);
		packGCGetSevenSignStatus(uid, signEntity, builder);
	}

	/**
	 * 检测玩家是否可领取奖励
	 * 
	 * @return true:可领取 false:不可领取
	 * @
	 */
	public boolean checkReceiveSevenReward(long uid) {
		// 功能未开启
		if (!GameGlobals.functionOpenManager.onCheck(uid, ISystemId.SYSTEM_SEVEN_SIGN)) {
			return false;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			SignEntity signEntity = Globals.getEntityProxy().get(SignEntity.class, uid);
			if (signEntity == null) {
				return true;
			}

			// 七日签满
			if (signEntity.getSevenSignday() >= 7) {
				return false;
			}

			// 是否可签
			return !TimeUtils.isToday(signEntity.getSevenSignTime());
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 是否显示签到按钮
	 * 
	 */
	public boolean isShowSignButton(long uid) {
		SignEntity signEntity = getSignEntity(uid);
		return signEntity.getSevenSignday() < 7;
	}

	/**
	 * 初始化玩家签到表
	 */
	private SignEntity getSignEntity(long uid) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			SignEntity signEntity = Globals.getEntityProxy().get(SignEntity.class, uid);
			if (signEntity != null) {
				return signEntity;
			}

			signEntity = new SignEntity();
			signEntity.setUid(uid);
			signEntity.setSevenSignday(0);
			signEntity.setSevenSignTime(0);
			Globals.getEntityProxy().insert(signEntity);
			return signEntity;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 已经领取过奖励数据封装
	 */
	private void packGCGetSevenSignStatus(long uid, SignEntity signEntity, GCGetSevenSignStatusProto.Builder builder) {
		// 已经领取过的
		int day = signEntity.getSevenSignday();// 已经领过的天数
		for (SignBean bean : Datas.getDataMap(SignBean.class).values()) {
			GCGetSevenSignStatusMsg.GCSivenSignProto.Builder proto = GCGetSevenSignStatusMsg.GCSivenSignProto.newBuilder();
			proto.setDay(bean.getId());
			proto.setUnitId(bean.getItem());
			proto.setUnitType(bean.getItemtype());
			proto.setNum(bean.getNum());
			if (bean.getId() < day + 1) {// 已经领取过的
				proto.setStatus(ALREADY_REWARD);
			} else if (bean.getId() > day + 1) {
				proto.setStatus(NOT_REWARD);
			} else {
				if (!TimeUtils.isToday(signEntity.getSevenSignTime())) {// 有可领取的
					proto.setStatus(CAN_REWARD);
				} else {// 没有可领取的
					proto.setStatus(NOT_REWARD);
				}
			}
			builder.addSivenSigns(proto);
		}
	}

	/**
	 * 签到打点
	 */
	private void signLog(UserBean user, LogfType logType, int day, int itemType, int itemId, int itemNum) {
		// 签到日志
		RoleSignLog signLog = new RoleSignLog(logType, user);
		signLog.setDay(day);
		signLog.setItemType(itemType);
		signLog.setItemId(itemId);
		signLog.setItemNum(itemNum);
		LogfPrinter.getInstance().push(signLog);
	}
}
