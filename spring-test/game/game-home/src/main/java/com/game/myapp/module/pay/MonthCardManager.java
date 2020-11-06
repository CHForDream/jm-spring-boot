package com.game.myapp.module.pay;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.constants.MailConts;
import com.game.core.handler.MsgBack;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.utils.TimeUtils;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

import buffer.CGGetMonthCardInfoMsg.CGGetMonthCardInfoProto;
import buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto;
import buffer.CGSubscribeMonthCardBMsg.CGSubscribeMonthCardBProto;
import buffer.GCGetMonthCardInfoMsg;
import buffer.GCReceiveNoLimitPowerMsg.GCReceiveNoLimitPowerProto;
import buffer.GCSubscribeMonthCardBMsg.GCSubscribeMonthCardBProto;

/**
 * 月卡
 * <p>
 * 月卡A 1.开通后立即获得一定钻石 2.每日领取再固定钻石，邮件发放 3.体力上限+1
 * 
 * 月卡B 开通后开启以下效果 1.每日可领取30分钟无限体力时间 2.连胜银币奖励提升 3.每日可以购买超值礼包 4.体力恢复速度增加
 * 5.每日勋章增加（单人PVP增加次数限制，次数叫勋章）
 * </p>
 * 
 * @author lpf
 *
 */
public class MonthCardManager {

	public final long buyAddTime = 31 * 24 * 60 * 60 * 1000L;// 正常购买月卡增加时间

	public final long experienceCardAddTime = 3 * 24 * 60 * 60 * 1000L;// 月卡A体验卡

	public void CGGetMonthCardInfo(MsgBack msgBack, long uid, CGGetMonthCardInfoProto msg) {
		GCGetMonthCardInfoMsg.GCGetMonthCardInfoProto.Builder builder = GCGetMonthCardInfoMsg.GCGetMonthCardInfoProto.newBuilder();
		msgBack.addBuilder(builder);
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}

		builder.setMonthCardAendTime(role.getMonthCardEndTime());
		builder.setMonthCardBEndTime(role.getMonthCardBEndTime());
		builder.setSubscribeMonthCardB(role.getSubscribeMonthCardB());
		builder.setNoPowerLimitReceiveNum(role.getMonthCardBNoLimitReceiveNum());
		builder.setStatus(ErrorCodeConst.SUCCESS);
		// 取消月卡小红点
		GameGlobals.redHatManager.cancelRedHat(uid, RedHatBehavior.WELFARE_ACTIVITY_MONTH_CARD_BUY_PANEL);
	}

	/**
	 * 领取免费体力(月卡B)
	 * 
	 * @param msgBack
	 * 
	 * @param userBean
	 * @param msg
	 */
	public void CGReceiveNoLimitPower(MsgBack msgBack, UserBean userBean, CGReceiveNoLimitPowerProto msg) {
		GCReceiveNoLimitPowerProto.Builder builder = GCReceiveNoLimitPowerProto.newBuilder();
		msgBack.addBuilder(builder);
		long uid = userBean.getUid();
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			if (!this.checkInMonthCardBTime(uid)) {
				builder.setStatus(ErrorCodeConst.NO_IN_MONTH_CARD_TIME);
				return;
			}

			String monthCardBNoLimitPowerTimeConfig = GameGlobals.configManager.getMonthCardBNoLimitPowerTime();
			int monthCardBNoLimitPowerNum = Integer.parseInt(monthCardBNoLimitPowerTimeConfig.split(",")[1]);

			if (role.getMonthCardBNoLimitReceiveNum() >= monthCardBNoLimitPowerNum) {
				builder.setStatus(ErrorCodeConst.NOHAVE_POWER_LIMIT_RECEIVE_NUM);
				return;
			}

			int monthCardBNoLimitPowerTime = Integer.parseInt(monthCardBNoLimitPowerTimeConfig.split(",")[0]);
			long newTime = GameGlobals.roleManager.updateRoleNoLimitPowerTime(uid, monthCardBNoLimitPowerTime);
			builder.setNoLimitPowerTime((int) (newTime - (System.currentTimeMillis() / 1000L)));

			role.setMonthCardBNoLimitReceiveNum(role.getMonthCardBNoLimitReceiveNum() + 1);
			builder.setStatus(ErrorCodeConst.SUCCESS);
			return;
		} finally {
			lock.unlock();
		}
	}

	public void CGSubscribeMonthCardB(MsgBack msgBack, UserBean userBean, CGSubscribeMonthCardBProto msg) {
		GCSubscribeMonthCardBProto.Builder builder = GCSubscribeMonthCardBProto.newBuilder();
		msgBack.addBuilder(builder);
		long uid = userBean.getUid();
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}

			int opt = msg.getOptType();
			if (opt != role.getSubscribeMonthCardB()) {
				role.setSubscribeMonthCardB(opt);
				Globals.getEntityProxy().updateAsync(role);
			}
		} finally {
			lock.unlock();
		}

		builder.setStatus(ErrorCodeConst.SUCCESS);
	}

	/**********************************
	 * 月卡A
	 ****************************************/
	/**
	 * 更新月卡A结束时间
	 * 
	 * @param uid
	 */
	public void updateRoleMonthCardA(long uid, long addTime) {
		long now = System.currentTimeMillis();
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				return;
			}

			long oldEndTime = role.getMonthCardEndTime();
			if (oldEndTime >= now) {
				role.setMonthCardEndTime(oldEndTime + addTime);
			} else {
				role.setMonthCardEndTime(now + addTime);
				role.setMonthCardLastRewardTime(now);
				// 发送邮件
				sendMonthCardAReward(uid, 1);
			}
			role.save();
		} finally {
			lock.unlock();
		}

		// 检测月卡小红点
		GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.WELFARE_ACTIVITY_MONTH_CARD_BUY_PANEL);
	}

	/**
	 * 检测是否在月卡A时间内
	 * 
	 * @param uid
	 * @return
	 */
	public boolean checkInMonthCardATime(RoleEntity role) {
		if (role == null) {
			return false;
		}
		return role.getMonthCardEndTime() > System.currentTimeMillis();
	}

	/**
	 * 检测发送月卡奖励邮件
	 * 
	 * @param role
	 */
	public void checkSendMonthCardAReward(RoleEntity role) {
		long lastRewardTime = role.getMonthCardLastRewardTime();
		long endTime = role.getMonthCardEndTime();
		boolean hasReward = TimeUtils.differentDays(lastRewardTime, endTime) > 0;
		if (!hasReward) {
			return;
		}

		long now = System.currentTimeMillis();
		int rewardDays = TimeUtils.differentDays(lastRewardTime, now);
		if (rewardDays <= 0) {
			return;
		}

		// 发送月卡奖励, 更新月卡奖励时间
		sendMonthCardAReward(role.getUid(), rewardDays);
		role.setMonthCardLastRewardTime(now);
		role.save();
	}

	/**********************************
	 * 月卡B
	 ****************************************/
	/**
	 * 更新月卡B结束时间
	 * 
	 * @param uid
	 */
	public void updateRoleMonthCardB(long uid, long addTime) {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return;
		}

		long oldEndTime = role.getMonthCardBEndTime();
		long currTime = System.currentTimeMillis();
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			if (oldEndTime >= currTime) {
				role.setMonthCardBEndTime(oldEndTime + addTime);
			} else {
				role.setMonthCardBEndTime(currTime + addTime);
			}
			Globals.getEntityProxy().updateAsync(role);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 检测是否在月卡B时间内
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public boolean checkInMonthCardBTime(long uid) {
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			return false;
		}
		return role.getMonthCardBEndTime() > System.currentTimeMillis();
	}

	/**
	 * 发送月卡A奖励邮件
	 * 
	 * @param uid
	 * @param rewardDays
	 */
	private void sendMonthCardAReward(long uid, int rewardDays) {
		if (rewardDays < 1) {
			return;
		}

		List<ItemVo> monthCardRewardItemList = GameGlobals.configManager.getMonthCardRewardItemList();
		List<ItemVo> itemList = ItemUtil.merge(monthCardRewardItemList);// clone一下数据
		if (rewardDays > 1) {
			for (ItemVo itemVo : itemList) {
				itemVo.setNum(itemVo.getNum() * rewardDays);
			}
		}

		GameGlobals.mailManager.addMail(MailConts.MAIL_TYPE_SYSTEM, GameGlobals.configManager.getMonthCardMailTitle(),
				GameGlobals.configManager.getMonthCardMailContent(), uid, 0, ItemUtil.toInfoString(itemList), 0L, "");
	}
}