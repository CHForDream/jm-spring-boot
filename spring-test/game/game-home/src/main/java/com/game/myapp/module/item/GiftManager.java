package com.game.myapp.module.item;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.core.handler.MsgBack;
import com.game.core.hibernate.key.LongLongPrimaryKey;
import com.game.db.entity.GiftEntity;
import com.game.generate.bean.GiftBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.redhat.RedHatBehavior;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

import buffer.GCSellBagMsg;

public class GiftManager {
	public List<GiftEntity> getAll(long uid) {
		List<GiftEntity> list = Globals.getEntityProxy().findAllByUid(GiftEntity.class, uid);
		return list;
	}

	public GiftEntity getDBId(long uid, long dbId) {
		GiftEntity gift = Globals.getEntityProxy().get(GiftEntity.class, new LongLongPrimaryKey(uid, dbId));
		return gift;
	}

	public long addGift(long uid, int itemId, int itemNum) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			GiftEntity gift = this.getDBId(uid, itemId);
			if (gift != null) {
				gift.setNum(gift.getNum() + itemNum);
				Globals.getEntityProxy().updateAsync(gift);
				return gift.getItemId();
			}

			GiftEntity db = new GiftEntity();
			db.setItemId(itemId);
			db.setNum(itemNum);
			db.setUid(uid);
			Globals.getEntityProxy().insert(db);
			return db.getItemId();
		} finally {
			lock.unlock();
		}
	}

	public OpenGiftInfo useGift(long uid, int giftId, int num) {
		OpenGiftInfo result = new OpenGiftInfo();
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			GiftBean giftBean = Datas.get(GiftBean.class, giftId);
			if (giftBean == null) {
				return result;
			}

			GiftEntity db = getDBId(uid, giftId);
			if (db.getNum() < num) {
				num = db.getNum();
			}
			db.setNum(db.getNum() - num);
			if (db.getNum() <= 0) {
				Globals.getEntityProxy().delete(db);
				result.setRemind(db.getNum());
			} else {
				Globals.getEntityProxy().updateAsync(db);
				result.setRemind(db.getNum());
			}

			List<ItemVo> rewardList = ItemUtil.merge(giftBean.getRewardList());
			for (ItemVo itemVo : rewardList) {
				itemVo.setNum(itemVo.getNum() * num);
				GameGlobals.bagManager.addItem(uid, itemVo.getType(), itemVo.getId(), itemVo.getNum(), LogfConstants.CHANNEL_GIFT,
						String.valueOf(db.getItemId()));
			}
			result.getResult().addAll(rewardList);
		} finally {
			lock.unlock();
		}

		// 检测小红点
		GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.BAG_ALL);
		return result;
	}

	public void sellGift(MsgBack msgBack, long uid, int itemId, int itemNum) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			GiftEntity db = this.getDBId(uid, itemId);
			GCSellBagMsg.GCSellBagProto.Builder gcMsg = GCSellBagMsg.GCSellBagProto.newBuilder();
			gcMsg.setUnitType(EItemType.GIFT.getType());
			gcMsg.setUnitNum(itemNum);
			msgBack.addBuilder(gcMsg);
			GiftBean giftBean = Datas.get(GiftBean.class, db.getItemId());
			if (db.getNum() < itemNum) {
				gcMsg.setStatus(1);
				return;
			}
			if (giftBean.getIssale() != 1) {
				gcMsg.setStatus(1);
				return;
			}
			db.setNum(db.getNum() - itemNum);
			if (db.getNum() > 0) {
				Globals.getEntityProxy().updateAsync(db);
			} else {
				Globals.getEntityProxy().delete(db);
			}

			int addGold = giftBean.getCost() * itemNum;
			GameGlobals.unitManager.add(uid, giftBean.getMoneyType(), addGold);
			gcMsg.setUnitNum(db.getNum());
			gcMsg.setDbId(db.getItemId());
			gcMsg.setAddGold(addGold);

			// 检测小红点
			GameGlobals.redHatManager.checkBehaviorRedHat(uid, RedHatBehavior.BAG_ALL);

			GameGlobals.itemManager.costItemComplete(uid, EItemType.GIFT.getType(), itemId, itemNum, LogfConstants.CHANNEL_SELL, "-1");
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 领取每日任务宝箱直接换成东西发送给玩家
	 * 
	 * @param giftId
	 * @param num
	 */
	public void addGiftAwards(long uid, int giftId, int num, String channel) {
		GiftBean giftBean = Datas.get(GiftBean.class, giftId);
		if (giftBean.getItemNum1() > 0) {
			GameGlobals.bagManager.addItem(uid, giftBean.getItemType1(), giftBean.getItemId1(), giftBean.getItemNum1() * num, channel, String.valueOf(giftId));
		}
		if (giftBean.getItemNum2() > 0) {
			GameGlobals.bagManager.addItem(uid, giftBean.getItemType2(), giftBean.getItemId2(), giftBean.getItemNum2() * num, channel, String.valueOf(giftId));
		}
		if (giftBean.getItemNum3() > 0) {
			GameGlobals.bagManager.addItem(uid, giftBean.getItemType3(), giftBean.getItemId3(), giftBean.getItemNum3() * num, channel, String.valueOf(giftId));
		}
		if (giftBean.getItemNum4() > 0) {
			GameGlobals.bagManager.addItem(uid, giftBean.getItemType4(), giftBean.getItemId4(), giftBean.getItemNum4() * num, channel, String.valueOf(giftId));
		}
		if (giftBean.getItemNum5() > 0) {
			GameGlobals.bagManager.addItem(uid, giftBean.getItemType5(), giftBean.getItemId5(), giftBean.getItemNum5() * num, channel, String.valueOf(giftId));
		}
	}
}
