package com.game.myapp.module.item;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import com.game.common.data.Datas;
import com.game.common.logf.LogfConstants;
import com.game.common.logf.LogfPrinter;
import com.game.common.logf.LogfType;
import com.game.common.logf.impl.ItemLog;
import com.game.common.user.UserBean;
import com.game.constants.DayTaskConstants;
import com.game.constants.ErrorCodeConst;
import com.game.constants.Loggers;
import com.game.core.handler.MsgBack;
import com.game.core.hibernate.key.LongIntPrimaryKey;
import com.game.db.entity.ItemEntity;
import com.game.generate.bean.HeroBean;
import com.game.generate.bean.ItemBean;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.achieve.EAchieveBehavior;
import com.game.myapp.module.role.EPowerChannel;
import com.game.utils.TimeUtils;
import com.game.vo.item.ItemVo;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import buffer.CGUserItemMsg;
import buffer.GCSellBagMsg;
import buffer.GCUseItemMsg;
import buffer.GCUseItemMsg.GCUseItemProto;

public class ItemManager {

	/** pve 战斗前道具Id集合 */
	private Set<Integer> prePveBattleItemIdSet = Sets.newHashSet();
	/** 副本 战斗前道具Id集合 */
	private Set<Integer> preDupBattleItemIdSet = Sets.newHashSet();
	/** 精英副本 战斗前道具Id集合 */
	private Set<Integer> preEliteBattleItemIdSet = Sets.newHashSet();
	/** 战斗中道具Id集合 */
	private Set<Integer> inBattleItemIdSet = Sets.newHashSet();

	/** 战斗前无限道具Id -》 真实道具Id */
	private Map<Integer, Integer> infiniteItemIdMap = Maps.newTreeMap();
	/** 战斗中免费道具Id -》 真实道具Id */
	private Map<Integer, Integer> freeItemIdMap = Maps.newTreeMap();

	public ItemManager() {
		for (ItemBean itemBean : Datas.getDataMap(ItemBean.class).values()) {
			if (itemBean.getArena() == ItemConstants.TYPE_INFINITE) {
				if (itemBean.getDup() == 1) {
					preDupBattleItemIdSet.add(itemBean.getId());
				}
				if (itemBean.getPve() == 1) {
					prePveBattleItemIdSet.add(itemBean.getId());
				}
				if (itemBean.getElite() == 1) {
					preEliteBattleItemIdSet.add(itemBean.getId());
				}
				if (itemBean.getInfinite() != 0) {
					infiniteItemIdMap.put(itemBean.getInfinite(), itemBean.getId());
				}
			} else if (itemBean.getArena() == ItemConstants.TYPE_BATTLE) {
				inBattleItemIdSet.add(itemBean.getId());
				if (itemBean.getInfinite() != 0) {
					freeItemIdMap.put(itemBean.getInfinite(), itemBean.getId());
				}
			}
		}
	}

	public void CGUserItemMsg(MsgBack msgBack, UserBean userBean, CGUserItemMsg.CGUserItemProto cgMsg) {
		int itemType = cgMsg.getItemType();
		int itemId = cgMsg.getItemId();
		int itemNum = cgMsg.getItemNum();
		GCUseItemMsg.GCUseItemProto.Builder builder = GCUseItemMsg.GCUseItemProto.newBuilder();
		msgBack.addBuilder(builder);
		int code = ErrorCodeConst.SUCCESS;

		EItemType eItemType = EItemType.valueOf(itemType);
		if (eItemType == null) {
			return;
		}

		switch (eItemType) {
		case GIFT:
			useGift(userBean.getUid(), itemId, itemNum, builder);
			break;

		case ITEM:
			code = useItem(userBean.getUid(), itemId, itemNum, builder);
			break;

		default:
			break;
		}

		builder.setStatus(code);
		builder.setItemType(itemType);
		builder.setItemId(itemId);
	}

	public List<ItemEntity> getAllItemList(long uid) {
		List<ItemEntity> list = Globals.getEntityProxy().findAllByUid(ItemEntity.class, uid);
		return list;
	}

	public void addItem(long uid, int itemId, int itemNum) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			// 是否是限时无限道具
			boolean isInfiniteItem = isInfiniteItemId(itemId);
			boolean isfreeItem = isFreeItemId(itemId);
			if (isInfiniteItem) {
				// 无限道具id转换成对应的道具Id
				itemId = infiniteItemIdMap.get(itemId);
			} else if (isfreeItem) {
				// 免费道具id转换成对应的道具Id
				itemId = freeItemIdMap.get(itemId);
			}

			ItemBean itemBean = Datas.get(ItemBean.class, itemId);
			if (itemBean == null) {
				Loggers.serverLogger.error("No ItemBean! itemId = " + itemId);
				return;
			}

			long now = System.currentTimeMillis();
			boolean isDataChanged = false;
			boolean isNew = false;
			ItemEntity itemEntity = getItemEntity(uid, itemId);
			if (itemEntity == null) {
				isNew = true;
				isDataChanged = true;
				itemEntity = new ItemEntity();
				itemEntity.setNum(0);
				itemEntity.setUid(uid);
				itemEntity.setItemId(itemId);
				itemEntity.setInfiniteTime(0L);
				itemEntity.setFreeNum(0);
				itemEntity.setFreeResetTime(0);
			}

			if (isInfiniteItem) {
				// 限时无限道具的数量是有效时间，单位是分钟，额外加10秒逻辑超时时间。
				long infiniteTime = itemNum * TimeUtils.MIN + ItemConstants.INFINITE_ITEM_ADJUST_TIME;
				if (itemEntity.getInfiniteTime() > now) {
					// 无限时间还有剩余，直接叠加
					itemEntity.setInfiniteTime(itemEntity.getInfiniteTime() + infiniteTime);
				} else {
					// 没有无限时间，或无限时间已超时，从当前时间开始计时
					itemEntity.setInfiniteTime(now + infiniteTime);
				}

				isDataChanged = true;
			} else if (isfreeItem) {
				// 重置每日免费道具, 当天已被重置过, 不需要再重置
				if (!TimeUtils.isSameDay(itemEntity.getFreeResetTime(), now)) {
					// 每日免费道具, 免费数量不能超过每日数量
					int freeNum = Math.min(itemEntity.getFreeNum() + itemNum, itemBean.getInfiniteNum());
					itemEntity.setFreeNum(freeNum);
					itemEntity.setFreeResetTime(now);

					isDataChanged = true;
				}
			} else {
				// 非无限道具， 直接加数量
				itemEntity.setNum(itemEntity.getNum() + itemNum);

				isDataChanged = true;
			}

			if (isDataChanged) {
				if (isNew) {
					Globals.getEntityProxy().insert(itemEntity);
				} else {
					Globals.getEntityProxy().updateAsync(itemEntity);
				}
			}
		} finally {
			lock.unlock();
		}
	}

	public boolean costItem(long uid, int itemId, int itemNum) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			ItemEntity itemEntity = getItemEntity(uid, itemId);
			if (itemEntity == null) {
				return false;
			}

			ItemBean itemBean = getItemBean(itemId);
			if (itemBean == null) {
				return false;
			}

			UserBean userBean = Globals.getUserManager().getUserBean(uid);

			long now = System.currentTimeMillis();
			if (itemEntity.getInfiniteTime() > now) {
				if (userBean != null) {
					// 记录战斗前使用道具(无限道具)
					userBean.getUserInfoVo().getPreItemIdList().add(itemBean.getInfinite());
				}

				// 道具处于无限时间内，不做消耗
				// 消耗打点数据使用ItemBean中的无限道具Id值
				costItemComplete(uid, EItemType.ITEM.getType(), itemBean.getInfinite(), itemNum, LogfConstants.CHANNEL_USE, "-1");
				return true;
			}

			if (itemEntity.getNum() + itemEntity.getFreeNum() < itemNum) {
				// 道具数量+免费数量总和, 小于需要消耗的数量, 背包数量不足
				return false;
			}

			// 战斗前使用道具
			boolean isPreBattleItem = itemBean.getArena() == 2;
			if (isPreBattleItem) {
				// 默认每次使用数量只能是1个
				if (userBean != null) {
					userBean.getUserInfoVo().getPreItemIdList().add(itemId);
				}
			}

			// 战斗中使用道具
			boolean isBattleItem = itemBean.getBattleuse() == 1;
			if (isBattleItem) {
				// 默认每次使用数量只能是1个
				if (userBean != null) {
					userBean.getUserInfoVo().getBattleItemIdList().add(itemId);
				}
			}

			if (itemEntity.getFreeNum() > 0) {
				// 有免费数量, 消耗免费道具
				int freeCostNum = Math.min(itemEntity.getFreeNum(), itemNum);
				itemNum = itemNum - freeCostNum;
				itemEntity.setFreeNum(itemEntity.getFreeNum() - freeCostNum);
				costItemComplete(uid, EItemType.ITEM.getType(), itemBean.getInfinite(), -freeCostNum, LogfConstants.CHANNEL_USE, "-1");
			}

			if (itemNum > 0) {
				int costNum = Math.min(itemEntity.getNum(), itemNum);
				itemEntity.setNum(itemEntity.getNum() - costNum);
				costItemComplete(uid, EItemType.ITEM.getType(), itemId, -costNum, LogfConstants.CHANNEL_USE, "-1");
			}

			// 无限的战前道具在前面就直接返回了, 不会执行到这里
			if (itemEntity.getNum() + itemEntity.getFreeNum() > 0) {
				Globals.getEntityProxy().updateAsync(itemEntity);
			} else {
				Globals.getEntityProxy().delete(itemEntity);
			}

			return true;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 使用体验卡
	 * 
	 * @param uid
	 * @param dbId
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public int useExperienceCard(long uid, int num) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			ItemEntity item = getItemEntity(uid, EItemId.MONTH_EXPERIENCE_CARD.getType());
			if (item == null) {
				return ErrorCodeConst.ITEM_NOT_EXIST;
			}
			if (num > item.getNum()) {
				return ErrorCodeConst.RESOURCE_NOT_ENOUGH;
			}

			GameGlobals.monthCardManager.updateRoleMonthCardA(uid, GameGlobals.monthCardManager.experienceCardAddTime * num);
			item.setNum(item.getNum() - num);

			costItemComplete(uid, EItemType.ITEM.getType(), EItemId.MONTH_EXPERIENCE_CARD.getType(), num, LogfConstants.CHANNEL_USE, "-1");
			if (item.getNum() > 0) {
				Globals.getEntityProxy().updateAsync(item);
			} else {
				Globals.getEntityProxy().delete(item);
			}
		} finally {
			lock.unlock();
		}
		return ErrorCodeConst.SUCCESS;
	}

	public ItemEntity getItemEntity(long uid, int itemId) {
		ItemEntity result = Globals.getEntityProxy().get(ItemEntity.class, new LongIntPrimaryKey(uid, itemId));
		return result;
	}

	public int getItemNumByItemId(long uid, int itemId) {
		ItemEntity item = getItemEntity(uid, itemId);
		if (item == null) {
			return 0;
		}
		return item.getNum();
	}

	public void sellItem(MsgBack msgBack, long uid, int itemId, int itemNum) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			ItemEntity db = getItemEntity(uid, itemId);
			GCSellBagMsg.GCSellBagProto.Builder gcMsg = GCSellBagMsg.GCSellBagProto.newBuilder();
			gcMsg.setUnitType(EItemType.ITEM.getType());
			gcMsg.setUnitNum(itemNum);
			msgBack.addBuilder(gcMsg);
			ItemBean itemBean = Datas.get(ItemBean.class, itemId);
			if (db.getNum() < itemNum) {
				gcMsg.setStatus(1);
				return;
			}
			if (itemBean.getIssale() != 1) {
				gcMsg.setStatus(1);
				return;
			}
			db.setNum(db.getNum() - itemNum);
			costItemComplete(uid, EItemType.ITEM.getType(), itemId, itemNum, LogfConstants.CHANNEL_SELL, "-1");
			if (db.getNum() > 0) {
				Globals.getEntityProxy().updateAsync(db);
			} else {
				Globals.getEntityProxy().delete(db);
			}

			int addGold = itemBean.getCost() * itemNum;
			gcMsg.setUnitNum(db.getNum());
			gcMsg.setDbId(db.getItemId());
			GameGlobals.unitManager.add(uid, itemBean.getMoneyType(), addGold);
			gcMsg.setAddGold(addGold);
		} finally {
			lock.unlock();
		}
	}

	public ItemBean getItemBean(int itemId) {
		return Datas.get(ItemBean.class, itemId);
	}

	public ItemVo packItemInfo(long uid, int itemType, int itemId, int itemNum) {
		try {
			ItemVo item = new ItemVo(itemType, itemId, itemNum);
			// 处理英雄
			if (EItemType.HERO.is(itemType) && GameGlobals.heroManager.hasHero(uid, itemId)) {
				HeroBean heroBean = Datas.get(HeroBean.class, itemId);
				item.setType(heroBean.getItem().getType());
				item.setId(heroBean.getItem().getId());
				item.setNum(heroBean.getItem().getNum());
			}
			return item;
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 是否有体力药
	 * 
	 * @param uid
	 * @return
	 */
	public boolean checkHavePowerDrug(long uid) {
		try {
			ItemEntity entity = getItemEntity(uid, EItemId.POWER_SMALL.getType());
			if (entity != null && entity.getNum() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取某个道具战斗携带的数量上限
	 */
	public int getItemNumLimitByItemId(int itemId) {
		ItemBean itemBean = Datas.get(ItemBean.class, itemId);
		if (itemBean != null) {
			return itemBean.getLimit();
		}
		return 0;
	}

	public boolean isInBattleItemId(int itemId) {
		return inBattleItemIdSet.contains(itemId);
	}

	public boolean isInfiniteItemId(int itemId) {
		return infiniteItemIdMap.containsKey(itemId);
	}

	public boolean isFreeItemId(int itemId) {
		return freeItemIdMap.containsKey(itemId);
	}

	public void resetFreeItem(long uid) {
		for (int freeItemId : freeItemIdMap.keySet()) {
			ItemBean itemBean = Datas.get(ItemBean.class, freeItemIdMap.get(freeItemId));
			if (itemBean == null) {
				continue;
			}

			// 免费道具重置数量
			GameGlobals.bagManager.addItem(uid, EItemType.ITEM.getType(), freeItemId, itemBean.getInfiniteNum(), LogfConstants.CHANNEL_DAILY_RESET, "", "");
		}
	}

	public void gmClearAllItem(long uid) {
		List<ItemEntity> allItemList = getAllItemList(uid);
		for (ItemEntity itemEntity : allItemList) {
			Globals.getEntityProxy().delete(itemEntity);
		}
	}

	private void useGift(long uid, int itemId, int itemNum, GCUseItemProto.Builder builder) {
		OpenGiftInfo result = GameGlobals.giftManager.useGift(uid, itemId, itemNum);
		List<ItemVo> voList = result.getResult();
		for (ItemVo tempVO : voList) {
			GCUseItemMsg.GiftItem.Builder tempMsg = GCUseItemMsg.GiftItem.newBuilder();
			tempMsg.setItemType(tempVO.getType());
			tempMsg.setItemId(tempVO.getId());
			tempMsg.setItemNum(tempVO.getNum());
			builder.addUnits(tempMsg);
		}
	}

	private int useItem(long uid, int itemId, int itemNum, GCUseItemProto.Builder builder) {
		ItemEntity itemsEntity = this.getItemEntity(uid, itemId);
		if (itemsEntity == null) {
			return ErrorCodeConst.ERROR_PARAM;
		}

		ItemBean bean = Datas.get(ItemBean.class, itemId);
		if (bean == null) {
			Loggers.itemLogger.error("No ItemBean! itemId = " + itemId);
			return ErrorCodeConst.ERROR_PARAM;
		}

		if (itemNum > (itemsEntity.getNum() + itemsEntity.getFreeNum()) && itemsEntity.getInfiniteTime() == 0L) {
			return ErrorCodeConst.RESOURCE_NOT_ENOUGH;
		}

		int result = ErrorCodeConst.SUCCESS;
		EItemId eItemId = EItemId.valueOf(itemId);
		if (eItemId == null) {
			if (!costItem(uid, itemId, itemNum)) {
				result = ErrorCodeConst.RESOURCE_NOT_ENOUGH;
			} else if (bean.getArena() == ItemConstants.TYPE_BATTLE) {
				// 检查道具消耗任务
				GameGlobals.taskManager.onTask(uid, DayTaskConstants.TYPE_BATTLE_IN_ITEM, itemNum);
				// 检查道具消耗成就
				GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_BATTLE_IN_ITEM, itemNum);
			}
			return result;
		}

		switch (eItemId) {
		case MONTH_EXPERIENCE_CARD:// 月卡体验卡
			result = GameGlobals.itemManager.useExperienceCard(uid, itemNum);
			break;

		case POWER_SMALL: // 小体力药
			if (costItem(uid, itemId, itemNum)) {
				int addVal = bean.getAddAttr() * itemNum;
				GameGlobals.roleManager.addPower(EPowerChannel.OTHER, uid, addVal);
				int power = GameGlobals.roleManager.getRoleEntity(uid).getPower();
				builder.setNewVal(power);
			} else {
				result = ErrorCodeConst.RESOURCE_NOT_ENOUGH;
			}
			break;

		case POWER_NO_LIMIT: // 无限体力卡
			if (costItem(uid, itemId, itemNum)) {
				int addVal = bean.getAddAttr() * 60;// 无限体力时间
				long newVal = GameGlobals.roleManager.updateRoleNoLimitPowerTime(uid, addVal);
				builder.setNewVal((int) newVal);
			} else {
				result = ErrorCodeConst.RESOURCE_NOT_ENOUGH;
			}
			break;

		default:
			if (!costItem(uid, itemId, itemNum)) {
				result = ErrorCodeConst.RESOURCE_NOT_ENOUGH;
			} else if (bean.getArena() == ItemConstants.TYPE_BATTLE) {
				// 检查道具消耗任务
				GameGlobals.taskManager.onTask(uid, DayTaskConstants.TYPE_BATTLE_IN_ITEM, itemNum);
				// 检查道具消耗成就
				GameGlobals.achieveManager.onTask(uid, EAchieveBehavior.TYPE_BATTLE_IN_ITEM, itemNum);
			}
			break;
		}
		return result;
	}

	public void costItemComplete(long uid, int itemType, int itemId, int itemNum, String channel, String id, String... param) {
		UserBean userbean = Globals.getUserManager().getUserBean(uid);
		String itemStr = itemType + "," + itemId + "," + -itemNum;
		LogfPrinter.getInstance().push(new ItemLog(LogfType.ITEM_COST, userbean, channel, itemStr, id, param));
	}

	public Set<Integer> getPrePveBattleItemIdSet() {
		return prePveBattleItemIdSet;
	}

	public Set<Integer> getPreDupBattleItemIdSet() {
		return preDupBattleItemIdSet;
	}

	public Set<Integer> getPreEliteBattleItemIdSet() {
		return preEliteBattleItemIdSet;
	}

	public Set<Integer> getInBattleItemIdSet() {
		return inBattleItemIdSet;
	}

	public Map<Integer, Integer> getInfiniteItemIdMap() {
		return infiniteItemIdMap;
	}

	public Map<Integer, Integer> getFreeItemIdMap() {
		return freeItemIdMap;
	}
}
