package com.game.myapp.module.luckdraw;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.logf.LogfConstants;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.StoreLuckDrawLog;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.core.handler.MsgBack;
import com.game.db.entity.LuckDrawInfoEntity;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.GemextractBean;
import com.game.generate.bean.GemluckBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.utils.RandomUtil;
import com.game.vo.item.ItemVo;

import buffer.GCLuckDrawMsg;

/**
 * 单抽 1)优先使用幸运值double库
 * 2)第二次抽奖必掉库
 * 3)随机必掉库
 * 
 * @author LPF
 */
public class OneDrawHandler extends AbsExtract {

	@Override
	public void extractReward(MsgBack msgBack, long uid) {
		GCLuckDrawMsg.GCLuckDrawMsgProto.Builder builder = GCLuckDrawMsg.GCLuckDrawMsgProto.newBuilder();
		msgBack.addBuilder(builder);
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (role == null) {
			builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
			return;
		}
		GemluckBean gemluckBean = GameGlobals.luckDrawManager.getGemluckBeanByType(LuckDrawManager.LUCK_DRAW_ONE_TYPE);
		// start
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			if (role.getDrawLuckNum() + gemluckBean.getType() > GameGlobals.configManager.getDaiLyLuckDrawNum()) {
				builder.setStatus(ErrorCodeConst.LUCK_DRAW_NOT_ENOUGH);
				return;
			}
			LuckDrawInfoEntity drawInfoEntity = Globals.getEntityProxy().get(LuckDrawInfoEntity.class, uid);
			Long currTime = System.currentTimeMillis();
			long nextFreeTime = drawInfoEntity.getNextFreeLuckDrawTime();
			boolean isTimeFree = (currTime >= nextFreeTime);

			int configIsFree = gemluckBean.getIsfree();
			String cost = gemluckBean.getPrice();
			int itemType = Integer.parseInt(cost.split(",")[0]);
			int itemId = Integer.parseInt(cost.split(",")[1]);
			int costCash = Integer.parseInt(cost.split(",")[2]);

			// 验证资源
			if (configIsFree == 1) {
				if (!isTimeFree && !GameGlobals.unitManager.isEnough(role, itemId, costCash)) {
					builder.setStatus(ErrorCodeConst.RESOURCE_NOT_ENOUGH);
					return;
				}
				if (isTimeFree) {// 免费抽取
					costCash = 0;
				}
			} else {
				if (!GameGlobals.unitManager.isEnough(role, itemId, costCash)) {
					builder.setStatus(ErrorCodeConst.RESOURCE_NOT_ENOUGH);
					return;
				}
			}

			int drawNum = drawInfoEntity.getOneDrawNum();
			// 1)先确定从哪个库取数据
			boolean isDouble = drawInfoEntity.getLuckNum() >= GameGlobals.configManager.getLuckMaxNum();
			int poolType = selectDataPoolType(drawInfoEntity.getOneDrawInfo(), drawNum, isDouble);
			// 2)取出数据
			List<GemextractBean> pools = GameGlobals.luckDrawManager.getDataPool(poolType);
			GemextractBean gemextractBean = RandomUtil.random(pools);
			int dropType = gemextractBean.getItemType();
			int dropItemId = gemextractBean.getItemId();
			int dropItemNum = gemextractBean.getNum();
			// 转换下(处理英雄和装饰)
			ItemVo drop = GameGlobals.itemManager.packItemInfo(uid, dropType, dropItemId, dropItemNum);
			// 4)修改抽取次数
			OneDrawInfoVo drawInfoVo = GameGlobals.luckDrawManager.getOneDrawInfo(drawInfoEntity.getOneDrawInfo());
			int raund = gemluckBean.getRaund();
			//double的情况下抽奖数量不变
			if (!isDouble) {
				if (drawInfoVo.getNum() + 1 == raund) {
					drawInfoEntity.setOneDrawInfo(GameGlobals.luckDrawManager.initOneDrawInfo());
				} else {
					drawInfoEntity.setOneDrawInfo(GameGlobals.luckDrawManager.oneDrawInfoToJson(drawInfoVo.getFree(), drawInfoVo.getNum() + 1));
				}
			}
			// 5)修改幸运值
			if (isDouble) {
				drawInfoEntity.setLuckNum(0);
			} else {
				int luckNum = Math.min(GameGlobals.configManager.getLuckMaxNum(), drawInfoEntity.getLuckNum() + gemluckBean.getLuck());
				drawInfoEntity.setLuckNum(luckNum);
			}
			// 6)修改下次的免费时间
			if (isTimeFree) {// 免费抽取
				drawInfoEntity.setNextFreeLuckDrawTime(currTime + gemluckBean.getIsfreshtime() * 1000);
			}
			// 7)消耗
			GameGlobals.unitManager.cost(uid, itemType, itemId, costCash, LogfConstants.CHANNEL_LUCKDRAW, gemluckBean.getId() + "");

			GameGlobals.bagManager.addItem(uid, drop, LogfConstants.CHANNEL_LUCKDRAW, String.valueOf(gemextractBean.getId()));

			// 7）封装数据返给客户端
			GCLuckDrawMsg.RewardProto.Builder temp = GCLuckDrawMsg.RewardProto.newBuilder();
			temp.setUnitType(drop.getType());
			temp.setUnitId(drop.getId());
			temp.setNum(drop.getNum());
			temp.setExt(poolType + "," + (isDouble ? "1" : "0"));
			builder.addRewards(temp);
			builder.setStatus(ErrorCodeConst.SUCCESS);
			builder.setNextFreeTime(drawInfoEntity.getNextFreeLuckDrawTime());
			builder.setCurrLuckNum(drawInfoEntity.getLuckNum());
			builder.setIsDouble(isDouble);
			int cash = GameGlobals.unitManager.getRoleResourceNum(itemId, role);
			builder.setCash(itemType + "," + itemId + "," + cash);

			// 修改抽取次数
			drawInfoEntity.setOneDrawNum(drawInfoEntity.getOneDrawNum() + 1);
			role.setDrawLuckNum(role.getDrawLuckNum() + gemluckBean.getType());
			builder.setLuckDrawNum(GameGlobals.configManager.getDaiLyLuckDrawNum() - role.getDrawLuckNum());
			// 更新DB
			Globals.getEntityProxy().updateAsync(role);
			Globals.getEntityProxy().updateAsync(drawInfoEntity);
			// 添加日志
			UserBean user = Globals.getUserManager().getUserBean(uid);
			StoreLuckDrawLog drawLog = new StoreLuckDrawLog(user);
			drawLog.setDrawType(LuckDrawManager.LUCK_DRAW_ONE_TYPE);
			drawLog.setCost(costCash);
			drawLog.addItem(dropType, dropItemId, dropItemNum);
			drawLog.setFree(isTimeFree ? 1 : 0);
			drawLog.setDoubleDraw(isDouble ? 1 : 0);
			LogfPrinter.getInstance().push(drawLog);
		} finally {
			lock.unlock();
		}
	}

	private int selectDataPoolType(String info, int drawNum, boolean isDouble) {
		if (isDouble) {
			return DOUBLE_MUST_POOL_TYPE;
		}
		// 第二次抽取必掉
		if (drawNum == 1) {
			return MUST_DROP_POOL_TYPE;
		}
		OneDrawInfoVo drawInfoVo = GameGlobals.luckDrawManager.getOneDrawInfo(info);
		if (drawInfoVo.getNum() + 1 == drawInfoVo.getFree()) {
			return SENIOR_DAYA_POOL_TYPE;
		} else {
			return NORMAL_DATA_POOL_TYPE;
		}
	}
}
