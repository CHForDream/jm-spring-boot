package com.game.myapp.module.luckdraw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
import com.google.common.collect.Lists;

import buffer.GCLuckDrawMsg;

/**
 * 10连抽
 * 
 * @author 刘朋飞
 *
 */
public class TenDrawHandler extends AbsExtract {

	private static Random rnd = new Random();
	private static List<FiveDrawRateVo> vos = new ArrayList<FiveDrawRateVo>();

	@Override
	public void extractReward(MsgBack msgBack, long uid) {
		GemluckBean gemluckBean = GameGlobals.luckDrawManager.getGemluckBeanByType(LuckDrawManager.LUCK_DRAW_TEN_TYPE);
		GCLuckDrawMsg.GCLuckDrawMsgProto.Builder builder = GCLuckDrawMsg.GCLuckDrawMsgProto.newBuilder();
		msgBack.addBuilder(builder);

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
			if (role == null) {
				builder.setStatus(ErrorCodeConst.ROLE_NOT_EXIST);
				return;
			}

			//抽奖次数不足
			if (role.getDrawLuckNum() + gemluckBean.getType() > GameGlobals.configManager.getDaiLyLuckDrawNum()) {
				builder.setStatus(ErrorCodeConst.LUCK_DRAW_NOT_ENOUGH);
				return;
			}
			LuckDrawInfoEntity drawInfoEntity = Globals.getEntityProxy().get(LuckDrawInfoEntity.class, uid);
			String cost = gemluckBean.getPrice();
			int itemType = Integer.parseInt(cost.split(",")[0]);
			int itemId = Integer.parseInt(cost.split(",")[1]);
			int costCash = Integer.parseInt(cost.split(",")[2]);
			if (!GameGlobals.unitManager.isEnough(role, itemId, costCash)) {
				builder.setStatus(ErrorCodeConst.RESOURCE_NOT_ENOUGH);
				return;
			}
			// 高级库取几个数据
			int seniorDataNum = getSeniorDataNum(gemluckBean);
			// 普通库取的数据
			int normalDataNum = LuckDrawManager.LUCK_DRAW_TEN_TYPE - seniorDataNum;
			// 2)取数据
			List<GemextractBean> seniorItems = new ArrayList<GemextractBean>();
			List<GemextractBean> normalItems = new ArrayList<GemextractBean>();
			List<GemextractBean> allItems = new ArrayList<GemextractBean>();
			List<GemextractBean> seniorData = GameGlobals.luckDrawManager.getDataPool(SENIOR_DAYA_POOL_TYPE);
			List<GemextractBean> normalData = GameGlobals.luckDrawManager.getDataPool(NORMAL_DATA_POOL_TYPE);

			for (int num = 0; num < seniorDataNum; num++) {
				GemextractBean gemextractBean = RandomUtil.random(seniorData);
				seniorItems.add(gemextractBean);
			}
			for (int num = 0; num < normalDataNum; num++) {
				GemextractBean gemextractBean = RandomUtil.random(normalData);
				normalItems.add(gemextractBean);
			}

			// 4)是否需要double
			boolean isDouble = drawInfoEntity.getLuckNum() + gemluckBean.getLuck() >= GameGlobals.configManager.getLuckMaxNum();
			GemextractBean doubleBean = null;
			//double 随机替换
			if (isDouble) {
				List<GemextractBean> pools = GameGlobals.luckDrawManager.getDataPool(DOUBLE_MUST_POOL_TYPE);
				doubleBean = RandomUtil.random(pools);
				int poolType = getDoubleDataPoolType(gemluckBean);
				if (poolType == SENIOR_DAYA_POOL_TYPE) {
					int index = rnd.nextInt(seniorItems.size());
					Collections.replaceAll(seniorItems, seniorItems.get(index), doubleBean);
				} else {
					int index = rnd.nextInt(normalItems.size());
					Collections.replaceAll(normalItems, normalItems.get(index), doubleBean);
				}
			}
			// 修改幸运值
			int luckNum = 0;
			if (isDouble) {
				int reduceLuckNum = drawInfoEntity.getLuckNum() + gemluckBean.getLuck() - GameGlobals.configManager.getLuckMaxNum();
				luckNum = Math.max(0, reduceLuckNum);
			} else {
				luckNum = Math.min(GameGlobals.configManager.getLuckMaxNum(), drawInfoEntity.getLuckNum() + gemluckBean.getLuck());
			}
			drawInfoEntity.setLuckNum(luckNum);
			GameGlobals.unitManager.cost(uid, itemType, itemId, costCash, LogfConstants.CHANNEL_LUCKDRAW, gemluckBean.getId() + "");

			// 3)整合数据
			allItems.addAll(normalItems);
			allItems.addAll(seniorItems);

			boolean doubleFlag = false;
			List<ItemVo> dropList = Lists.newArrayList();
			for (GemextractBean bean : allItems) {
				GCLuckDrawMsg.RewardProto.Builder temp = GCLuckDrawMsg.RewardProto.newBuilder();
				String ext = String.valueOf(bean.getGroup()) + ",";
				if (!doubleFlag && doubleBean == bean) {
					ext += "1";
					doubleFlag = true;
				} else {
					ext += "0";
				}
				//转换下英雄和装饰
				ItemVo itemVo = GameGlobals.itemManager.packItemInfo(uid, bean.getItemType(), bean.getItemId(), bean.getNum());
				dropList.add(itemVo);
				temp.setExt(ext);
				temp.setUnitType(itemVo.getType());
				temp.setUnitId(itemVo.getId());
				temp.setNum(itemVo.getNum());
				builder.addRewards(temp);
				GameGlobals.bagManager.addItem(uid, itemVo, LogfConstants.CHANNEL_LUCKDRAW, String.valueOf(bean.getId()));
			}

			builder.setStatus(ErrorCodeConst.SUCCESS);
			int cash = GameGlobals.unitManager.getRoleResourceNum(itemId, role);
			builder.setCash(itemType + "," + itemId + "," + cash);
			builder.setCurrLuckNum(drawInfoEntity.getLuckNum());
			builder.setNextFreeTime(drawInfoEntity.getNextFreeLuckDrawTime());
			builder.setIsDouble(isDouble);

			// 更新DB
			role.setDrawLuckNum(role.getDrawLuckNum() + +gemluckBean.getType());
			builder.setLuckDrawNum(GameGlobals.configManager.getDaiLyLuckDrawNum() - role.getDrawLuckNum());
			Globals.getEntityProxy().updateAsync(role);
			// 更新抽奖信息
			Globals.getEntityProxy().updateAsync(drawInfoEntity);

			// 添加日志
			UserBean user = Globals.getUserManager().getUserBean(uid);
			StoreLuckDrawLog drawLog = new StoreLuckDrawLog(user);
			drawLog.setDrawType(LuckDrawManager.LUCK_DRAW_TEN_TYPE);
			drawLog.setCost(costCash);
			drawLog.getItemList().addAll(dropList);
			drawLog.setFree(0);
			drawLog.setDoubleDraw(isDouble ? 1 : 0);
			LogfPrinter.getInstance().push(drawLog);
		} finally {
			lock.unlock();
		}
	}

	private int getSeniorDataNum(GemluckBean gemluckBean) {
		// List<FiveDrawRateVo> vos = new ArrayList<FiveDrawRateVo>();
		if (vos.size() == 0) {
			String raundnum = gemluckBean.getRaundnum();
			String[] rands = raundnum.split(",");
			for (String rand : rands) {
				int num = Integer.parseInt(rand.split(":")[0]);
				int rate = Integer.parseInt(rand.split(":")[1]);
				FiveDrawRateVo vo = new FiveDrawRateVo();
				vo.setNum(num);
				vo.setRate(rate);
				vos.add(vo);
			}
		}
		FiveDrawRateVo randVo = RandomUtil.random(vos);
		return randVo.getNum();
	}

	private int getDoubleDataPoolType(GemluckBean gemluckBean) {
		int randNum = rnd.nextInt(100);
		if (randNum >= gemluckBean.getLuckaddnum()) {
			return NORMAL_DATA_POOL_TYPE;
		} else {
			return SENIOR_DAYA_POOL_TYPE;
		}
	}

}
