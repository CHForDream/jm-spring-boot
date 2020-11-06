package com.game.myapp.module.role;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.game.common.logf.LogfPrinter;
import com.game.common.logf.impl.ItemCurrencyCostLog;
import com.game.common.user.UserBean;
import com.game.constants.DayTaskConstants;
import com.game.constants.Loggers;
import com.game.db.entity.RoleEntity;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.myapp.module.item.EItemId;
import com.game.myapp.module.item.EItemType;

public class UnitManager {
	private static Logger log = Loggers.serverLogger;

	/**
	 * 消耗资源
	 * 
	 * @param uid
	 * @param itemType
	 * @param itemId
	 * @param num
	 * @param channelType
	 * @param id
	 * @return
	 */
	public boolean cost(long uid, int itemType, int itemId, int num, String channel, String id, String... param) {
		// 这里只处理金币钻石和副本星星的消耗
		if (num <= 0 || !EItemType.ITEM.is(itemType)
				|| (!EItemId.COIN.is(itemId) && !EItemId.REWARD_CASH.is(itemId) && !EItemId.RECHARGE_CASH.is(itemId) && !EItemId.DUPSTAR.is(itemId))) {
			return false;
		}

		// 货币消耗
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);
		if (!EItemId.DUPSTAR.is(itemId)) {
			// 货币消耗日志
			addCostCurrencyLog(role, num, itemId, channel, id, param);

			// 将资源更改的属性设置到UserBean
			UserBean user = Globals.getUserManager().getUserBean(role.getUid());
			if (user != null) {
				user.setResourceChanged(true);
			}
		}
		return cost(role, itemId, num);
	}

	public boolean costCoinAndCash(long uid, int itemType, int itemId, int num, String channel, String id, String... param) {
		if (num <= 0 || !EItemType.ITEM.is(itemType)) {
			return false;
		}
		RoleEntity role = GameGlobals.roleManager.getRoleEntity(uid);

		int costOther = this.isEnoughCoinAndCash(role, itemId, num);
		if (costOther == -1) {
			return false;
		}
		int total = 0;
		int otherId = 0;
		if (EItemId.COIN.is(itemId)) {
			total = role.getCoin();
			otherId = EItemId.RECHARGE_CASH.getType();
		} else if (EItemId.RECHARGE_CASH.is(itemId) || EItemId.REWARD_CASH.is(itemId)) {
			total = role.getRewardBuleCash() + role.getChargeCash();
			otherId = EItemId.COIN.getType();
		}
		if (total >= num) {
			return cost(uid, itemType, itemId, num, channel, id, param);
		} else {
			cost(uid, itemType, itemId, total, channel, id, param);
			return cost(uid, itemType, otherId, costOther, channel, id, param);
		}
	}

	public void add(long uid, int itemId, int num) {
		ReentrantLock lock = Globals.getLockManager().getLock(uid);
		lock.lock();
		try {
			UserBean user = Globals.getUserManager().getUserBean(uid);
			RoleEntity roleEntity = GameGlobals.roleManager.getRoleEntity(uid);
			EItemId eItemId = EItemId.valueOf(itemId);
			if (eItemId == null) {
				return;
			}
			switch (eItemId) {
			case COIN:
				roleEntity.setCoin(roleEntity.getCoin() + num);
				log.info("ADD TYPE_CASH UID:" + roleEntity.getUid() + " add:" + num + " remind:" + roleEntity.getCoin());
				break;
			case RECHARGE_CASH:
				roleEntity.setChargeCash(roleEntity.getChargeCash() + num);
				log.info("ADD TYPE_CHARTE_CASH UID:" + roleEntity.getUid() + " add:" + num + " remind:" + roleEntity.getChargeCash());
				break;
			case REWARD_CASH:
				roleEntity.setRewardBuleCash(roleEntity.getRewardBuleCash() + num);
				log.info("ADD TYPE_CHARTE_CASH UID:" + roleEntity.getUid() + " add:" + num + " remind:" + roleEntity.getRewardBuleCash());
				break;
			case DUPSTAR:
				roleEntity.setDupStar(roleEntity.getDupStar() + num);
				log.info("ADD TYPE_DUP_STAR UID:" + roleEntity.getUid() + " add:" + num + " remind:" + roleEntity.getDupStar());
				if (GameGlobals.heroManager.addHeroByDupstar(uid)) {
					if (user != null) {
						user.setHeroChanged(true);
					}
				}
				break;
			default:
				return;
			}
			Globals.getEntityProxy().updateAsync(roleEntity);
			// 将资源更改的属性设置到UserBean
			if (user != null) {
				user.setResourceChanged(true);
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 资源检测
	 * 
	 * @param role
	 * @param itemId
	 * @param sumCost
	 * @return
	 */
	public boolean isEnough(RoleEntity role, int itemId, int sumCost) {
		EItemId eItemId = EItemId.valueOf(itemId);
		if (eItemId == null) {
			return false;
		}
		int total = 0;
		switch (eItemId) {
		case COIN:
			total = role.getCoin();
			break;
		case REWARD_CASH:
		case RECHARGE_CASH:
			total = role.getRewardBuleCash() + role.getChargeCash();
			break;
		case DUPSTAR:
			total = role.getDupStar();
			break;
		default:
			total = 0;
			break;
		}
		return sumCost <= total;
	}

	public int isEnoughCoinAndCash(RoleEntity role, int itemId, int sumCost) {
		int costOther = 0;
		if (EItemId.COIN.is(itemId)) {
			if (role.getCoin() < sumCost) {
				costOther = (int) Math.ceil((sumCost - role.getCoin()) / GameGlobals.configManager.getExchageGoldRatio());
				if (role.getRewardBuleCash() + role.getChargeCash() < costOther) {
					return -1;
				}
			}
			return costOther;
		} else if (EItemId.RECHARGE_CASH.is(itemId) || EItemId.REWARD_CASH.is(itemId)) {
			int total = role.getRewardBuleCash() + role.getChargeCash();
			if (total < sumCost) {
				costOther = (sumCost - total) * (int) (GameGlobals.configManager.getExchageGoldRatio());
				if (role.getCoin() < costOther) {
					return -1;
				}
			}
		}
		return costOther;
	}

	/**
	 * 根据类型获取资源数量
	 */
	public int getRoleResourceNum(int itemId, RoleEntity role) {
		if (EItemId.COIN.is(itemId)) {
			return role.getCoin();
		}
		if (EItemId.RECHARGE_CASH.is(itemId) || EItemId.REWARD_CASH.is(itemId)) {
			return role.getChargeCash() + role.getRewardBuleCash();
		}
		if (EItemId.DUPSTAR.is(itemId)) {
			return role.getDupStar();
		}
		return 0;
	}

	private boolean cost(RoleEntity role, int itemId, int num) {
		if (num < 0) {
			return false;
		}

		EItemId eItemId = EItemId.valueOf(itemId);
		if (eItemId == null) {
			return false;
		}

		ReentrantLock lock = Globals.getLockManager().getLock(role.getUid());
		lock.lock();
		try {
			switch (eItemId) {
			case COIN: {
				int totalGold = role.getCoin();
				if (totalGold >= num) {
					role.setCoin(role.getCoin() - num);
					log.info("COST SUCCESS TYPE_CASH UID:" + role.getUid() + " cost:" + num + " remind:" + role.getCoin());
					Globals.getEntityProxy().updateAsync(role);
					GameGlobals.taskManager.onTask(role.getUid(), DayTaskConstants.TYPE_COST_COIN, num);
					return true;
				} else {
					log.info("COST FAIL TYPE_CASH UID:" + role.getUid() + " cost:" + num + " remind:" + role.getCoin());
					return true;
				}
			}
			case REWARD_CASH:
			case RECHARGE_CASH: {
				int chargeCash = role.getChargeCash();// 充值钻石
				int rewardCash = role.getRewardBuleCash();// 产出钻石
				if (chargeCash + rewardCash >= num) {
					// 优先扣除充值钻石
					if (role.getChargeCash() >= num) {
						role.setChargeCash(role.getChargeCash() - num);
						log.info("Cost ChargeCash. uid = " + role.getUid() + ", cost = " + num + ", remind = " + role.getChargeCash());
					} else if (role.getChargeCash() > 0) {
						role.setChargeCash(0);
						int costRewardBuleCash = num - chargeCash;
						role.setRewardBuleCash(role.getRewardBuleCash() - costRewardBuleCash);
						log.info("Cost ChargeCash. uid = " + role.getUid() + ", cost = " + chargeCash + ", remind = " + role.getChargeCash());
						log.info("Cost RewardBuleCash. uid = " + role.getUid() + ", cost = " + costRewardBuleCash + ", remind = " + role.getRewardBuleCash());
					} else {
						role.setRewardBuleCash(role.getRewardBuleCash() - num);
						log.info("Cost RewardBuleCash. uid = " + role.getUid() + ", cost = " + num + ", remind = " + role.getRewardBuleCash());
					}
					return true;
				} else {
					log.info("COST FAIL TYPE_CASH UID:" + role.getUid() + " cost:" + num + " remind:" + role.getCoin());
					return false;
				}
			}
			case DUPSTAR: {
				// 副本星星只判断是否足够不必消耗
				return role.getDupStar() >= num;
			}
			default:
				return false;
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 货币打点日志
	 * 
	 * param 追加字段 日志中最后的表示
	 */
	private void addCostCurrencyLog(RoleEntity role, int num, int itemId, String channel, String id, String... param) {
		long uid = role.getUid();
		UserBean user = Globals.getUserManager().getUserBean(uid);
		if (user == null) {
			return;
		}

		int costCashCharge = 0;
		int costRewardBlueCash = 0;
		int costCoin = 0;
		EItemId eItemId = EItemId.valueOf(itemId);
		if (eItemId == null) {
			return;
		}
		switch (eItemId) {
		case REWARD_CASH:
		case RECHARGE_CASH:
			int rewardBlueCash = role.getRewardBuleCash();
			if (rewardBlueCash >= num) {
				costRewardBlueCash = num;
			} else {
				if (rewardBlueCash > 0) {
					costRewardBlueCash = rewardBlueCash;
				}
				costCashCharge = num - rewardBlueCash;
			}
			break;
		case COIN:
			costCoin = num;
			break;
		default:
			break;
		}

		// 货币消耗数据打点
		LogfPrinter.getInstance().push(new ItemCurrencyCostLog(user, costCashCharge, costRewardBlueCash, costCoin, channel, id, param));
	}
}
