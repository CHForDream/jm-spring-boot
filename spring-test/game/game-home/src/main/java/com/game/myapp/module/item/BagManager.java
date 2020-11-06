package com.game.myapp.module.item;

import java.util.List;

import com.game.common.logf.LogfPrinter;
import com.game.common.logf.LogfType;
import com.game.common.logf.impl.ItemLog;
import com.game.common.user.UserBean;
import com.game.core.handler.MsgBack;
import com.game.db.entity.GiftEntity;
import com.game.db.entity.ItemEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.achieve.EAchieveBehavior;
import com.game.myapp.module.function.ISystemId;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.myapp.module.role.EPowerChannel;
import com.game.vo.item.ItemVo;

import buffer.CGGetBagListMsg.CGGetBagListProto;
import buffer.CGSellBagMsg.CGSellBagProto;
import buffer.GCGetBagListMsg;
import buffer.GCGetBagListMsg.GCGetBagListProto;
import buffer.GCGetBagListMsg.GCGetBagListProto.Builder;

public class BagManager {
	public void CGGetBagListMsg(MsgBack msgBack, UserBean userBean, CGGetBagListProto cgMsg) {
		GCGetBagListMsg.GCGetBagListProto.Builder backMsg = GCGetBagListMsg.GCGetBagListProto.newBuilder();
		backMsg.setType(cgMsg.getType());
		msgBack.addBuilder(backMsg);

		if (cgMsg.getType() == EItemType.ITEM.getType()) {
			// 道具
			packItemToMessage(userBean.getUid(), backMsg);
		} else if (cgMsg.getType() == EItemType.GIFT.getType()) {
			// 礼包
			packGiftToMessage(userBean.getUid(), backMsg);
		} else {
			// 全部背包（道具和礼包）
			packItemToMessage(userBean.getUid(), backMsg);
			packGiftToMessage(userBean.getUid(), backMsg);
		}
	}

	public void CGSellBagMsg(MsgBack msgBack, UserBean userBean, CGSellBagProto cgMsg) {
		if (cgMsg.getUnitType() == EItemType.ITEM.getType()) {
			GameGlobals.itemManager.sellItem(msgBack, userBean.getUid(), (int) cgMsg.getDbId(), cgMsg.getUnitNum());
		}
		if (cgMsg.getUnitType() == EItemType.GIFT.getType()) {
			GameGlobals.giftManager.sellGift(msgBack, userBean.getUid(), (int) cgMsg.getDbId(), cgMsg.getUnitNum());
		}
	}

	public void addItem(long uid, int itemType, int itemId, int itemNum, String channel, String id, String... param) {
		addItem(uid, new ItemVo(itemType, itemId, itemNum), channel, id, param);
	}

	public void addItem(long uid, ItemVo item, String channel, String id, String... param) {
		EItemType eItemType = EItemType.valueOf(item.getType());
		if (eItemType == null) {
			return;
		}

		switch (eItemType) {
		case ITEM:
			addItem(uid, item.getId(), item.getNum());
			break;
		case GIFT:
			GameGlobals.giftManager.addGift(uid, item.getId(), item.getNum());
			break;
		case HERO:
			GameGlobals.heroManager.addHero(uid, item.getId());
			break;

		default:
			break;
		}

		addItemComplete(uid, item, channel, id, param);
	}

	private void addItem(long uid, int itemId, int itemNum) {
		EItemId eItemId = EItemId.valueOfOrDefault(itemId, EItemId.UNKNOWN);
		if (eItemId.isItem()) {
			GameGlobals.itemManager.addItem(uid, itemId, itemNum);
			return;
		}

		switch (eItemId) {
		case COIN:
		case RECHARGE_CASH:
		case REWARD_CASH:
		case DUPSTAR:
			GameGlobals.unitManager.add(uid, itemId, itemNum);
			return;
		case ACTIVE:
			GameGlobals.taskManager.addPoint(uid, itemNum);
			return;
		case POWER:
			GameGlobals.roleManager.addPower(EPowerChannel.OTHER, uid, itemNum);
			return;
		default:
			return;
		}
	}

	private void addItemComplete(long uid, ItemVo item, String channel, String id, String... param) {
		if (item.getNum() <= 0) {
			return;
		}

		if (EItemType.ITEM.is(item.getType()) && EItemId.COIN.is(item.getId())) {
			GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_COLLEC_COIN, item.getNum());
		}
		if (EItemType.ITEM.is(item.getType()) && (EItemId.RECHARGE_CASH.is(item.getId()) || EItemId.REWARD_CASH.is(item.getId()))) {
			GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_COLLEC_CASH, item.getNum());
		}
		// 系统开启检测
		GameGlobals.functionOpenManager.check(uid, ISystemId.ITEM_TYPE, String.valueOf(item.getType()), String.valueOf(item.getId()));

		// 红点检测
		if (EItemType.GIFT.is(item.getType())) {
			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.BAG_ALL);
		}

		// 获得物品打点
		UserBean userbean = Globals.getUserManager().getUserBean(uid);
		String itemStr = item.getType() + "," + item.getId() + "," + item.getNum();
		LogfPrinter.getInstance().push(new ItemLog(LogfType.ITEM_ADD, userbean, channel, itemStr, id, param));
	}

	private void packItemToMessage(long uid, GCGetBagListProto.Builder backMsg) {
		List<ItemEntity> backItemList = GameGlobals.itemManager.getAllItemList(uid);
		for (ItemEntity temp : backItemList) {
			if (temp.getNum() > 0) {
				GCGetBagListMsg.UnitProtoGCGetBagList.Builder unitBuilder = GCGetBagListMsg.UnitProtoGCGetBagList.newBuilder();
				unitBuilder.setId(temp.getItemId());
				unitBuilder.setNum(temp.getNum());
				unitBuilder.setUnitId(temp.getItemId());
				unitBuilder.setUnitType(EItemType.ITEM.getType());
				backMsg.addUnits(unitBuilder);
			}
		}
	}

	private void packGiftToMessage(long uid, Builder backMsg) {
		List<GiftEntity> backGiftList = GameGlobals.giftManager.getAll(uid);
		for (GiftEntity temp : backGiftList) {
			GCGetBagListMsg.UnitProtoGCGetBagList.Builder unitBuilder = GCGetBagListMsg.UnitProtoGCGetBagList.newBuilder();
			unitBuilder.setId(temp.getItemId());
			unitBuilder.setNum(temp.getNum());
			unitBuilder.setUnitId((int) temp.getItemId());
			unitBuilder.setUnitType(EItemType.GIFT.getType());
			backMsg.addUnits(unitBuilder);
		}
	}
}
