package com.game.myapp.module.shop;

import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.ItemBuyLog;
import com.game.common.user.UserBean;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBack;
import com.game.db.entity.HeroEntity;
import com.game.db.entity.RoleEntity;
import com.game.generate.bean.HeroBean;
import com.game.generate.bean.ShopBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.item.EItemType;

import buffer.CGShopBuyMsg.CGShopBuyProto;
import buffer.GCShopBuyMsg.GCShopBuyProto;

public class ShopManager {
	/** 月卡开启 */
	public static final int SHOP_OPEN_TYPE_MONTH_CARD = 1;

	public void CGShopBuyMsg(MsgBack msgBack, long uid, CGShopBuyProto protoInstance) {
		GCShopBuyProto.Builder gcMsg = GCShopBuyProto.newBuilder();
		msgBack.addBuilder(gcMsg);
		gcMsg.setNum(protoInstance.getNum());
		gcMsg.setShopId(protoInstance.getShopId());

		int costItemId = protoInstance.getCostType();

		ShopBean shopBean = Datas.get(ShopBean.class, protoInstance.getShopId());
		if (shopBean == null) {
			Loggers.serverLogger.error("No shop item for id: " + protoInstance.getShopId());
			gcMsg.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}
		if (shopBean.getOpenType() == SHOP_OPEN_TYPE_MONTH_CARD) {
			// 商品必须在月卡期间购买
			if (!GameGlobals.monthCardManager.checkInMonthCardBTime(uid)) {
				// 不在月卡期间， 不可以购买
				gcMsg.setStatus(ErrorCodeConst.NO_IN_MONTH_CARD_TIME);
				return;
			}
		}

		if (EItemType.HERO.is(shopBean.getItemType())) {
			HeroEntity hero = GameGlobals.heroManager.getHeroEntity(uid, shopBean.getItemId());
			if (hero != null) {
				gcMsg.setStatus(ErrorCodeConst.SHOP_HERO_IS_EXIST);
				return;
			}
			// 英雄未开放不能购买
			HeroBean heroBean = Datas.get(HeroBean.class, shopBean.getItemId());
			if (heroBean != null && heroBean.getLockStatus() == 0) {
				gcMsg.setStatus(ErrorCodeConst.SHOP_HERO_LOCK_ERROR);
				return;
			}
		}

		int num = protoInstance.getNum();
		if (num <= 0) {
			gcMsg.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}

		// 消耗货币信息
		Integer[] item = shopBean.getCostMap().get(costItemId);
		if (item == null) {
			Loggers.itemLogger.error("No currency id for shop item! shopId = " + shopBean.getId() + ", currencyId = " + costItemId);
			gcMsg.setStatus(ErrorCodeConst.ERROR_PARAM);
			return;
		}
		int costItemType = item[0];
		int costId = item[1];
		int costNum = item[2];

		RoleEntity roleDB = GameGlobals.roleManager.getRoleEntity(uid);
		if (!GameGlobals.unitManager.isEnough(roleDB, costId, costNum * num)) {
			gcMsg.setStatus(ErrorCodeConst.RESOURCE_NOT_ENOUGH);
			return;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			if (!GameGlobals.unitManager.cost(uid, costItemType, costId, costNum * num, LogfConstants.CHANNEL_SHOP, protoInstance.getShopId() + "")) {
				gcMsg.setStatus(ErrorCodeConst.RESOURCE_NOT_ENOUGH);
				gcMsg.setCash(roleDB.getCoin());
				return;
			}
			if (shopBean.getShopType() == EShopType.GIFT.getType() && shopBean.getItemType() == EItemType.GIFT.getType()) {
				gcMsg.setDbId(shopBean.getItemId());
				GameGlobals.giftManager.addGiftAwards(uid, shopBean.getItemId(), shopBean.getItemNum() * num, LogfConstants.CHANNEL_SHOP);
			} else {
				GameGlobals.bagManager.addItem(uid, shopBean.getItemType(), shopBean.getItemId(), shopBean.getItemNum() * num, LogfConstants.CHANNEL_SHOP,
						String.valueOf(shopBean.getId()));
				gcMsg.setDbId(shopBean.getItemId());
			}

			// add log
			this.addBuyItemLog(uid, costItemType + "," + costId + "," + costNum * num,
					shopBean.getItemType() + "," + shopBean.getItemId() + "," + shopBean.getItemNum() * num);
		} finally {
			lock.unlock();
		}
		gcMsg.setStatus(ErrorCodeConst.SUCCESS);
		gcMsg.setCash(roleDB.getCoin());
	}

	/**
	 * 添加购买道具的日志
	 * 
	 * @param uid
	 * @param costInfo
	 * @param buyInfo
	 */
	private void addBuyItemLog(long uid, String costInfo, String buyInfo) {
		try {
			UserBean user = Globals.getUserManager().getUserBean(uid);
			LogfPrinter.getInstance().push(new ItemBuyLog(user, costInfo, buyInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
