package com.game.myapp.module.config;

import java.util.ArrayList;
import java.util.List;

import com.game.common.data.Datas;
import com.game.generate.bean.ConfigBean;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

public class ConfigManager {
	public int getFriendSize() {
		ConfigBean bean = Datas.get(ConfigBean.class, 9);
		return (int) bean.getNum1();
	}

	public int getSendFriendShipLimit() {
		ConfigBean bean = Datas.get(ConfigBean.class, 11);
		return (int) bean.getNum1();
	}

	public int getInitGold() {
		ConfigBean bean = Datas.get(ConfigBean.class, 15);
		return (int) bean.getNum1();
	}

	public int getInitCash() {
		ConfigBean bean = Datas.get(ConfigBean.class, 16);
		return (int) bean.getNum1();
	}

	public List<Integer> getInitHeroList() {
		ConfigBean bean = Datas.get(ConfigBean.class, 17);
		List<Integer> list = new ArrayList<Integer>();
		String str = bean.getNum2();
		String[] strArray = str.split(",");
		for (String temp : strArray) {
			if (temp == null || temp.length() == 0) {
				continue;
			}
			list.add(Integer.parseInt(temp));
		}
		return list;
	}

	public int getLuckMaxNum() {
		ConfigBean bean = Datas.get(ConfigBean.class, 39);
		return (int) bean.getNum1();
	}

	/** 月卡奖励 */
	private List<ItemVo> monthCardRewardItemList;

	/**
	 * 月卡奖励
	 */
	public List<ItemVo> getMonthCardRewardItemList() {
		if (monthCardRewardItemList != null) {
			return monthCardRewardItemList;
		}

		ConfigBean bean = Datas.get(ConfigBean.class, 52);
		monthCardRewardItemList = ItemUtil.fromItemArrayString(bean.getNum2());
		return monthCardRewardItemList;
	}

	/**
	 * 月卡邮件标题
	 * 
	 * @return
	 */
	public String getMonthCardMailTitle() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 60);
			return bean.getNum2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 月卡邮件内容
	 * 
	 * @return
	 */
	public String getMonthCardMailContent() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 61);
			return bean.getNum2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	int addStepCost = 0;// 加步数消耗钻石数
	int addStepNum = 0;// 加的步数

	public int getAddStepCost() {
		if (addStepCost == 0) {
			getPveCostBlueCashAndAddStepArr();
		}
		return addStepCost;
	}

	public int getAddStepNum() {
		if (addStepNum == 0) {
			getPveCostBlueCashAndAddStepArr();
		}
		return addStepNum;
	}

	/**
	 * 獲取PVE戰鬥花費鑽石增長步數
	 */
	private int[] getPveCostBlueCashAndAddStepArr() {
		int[] result = new int[2];
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 72);
			addStepCost = result[0] = Integer.parseInt(bean.getNum2().split(",")[0]);
			addStepNum = result[1] = Integer.parseInt(bean.getNum2().split(",")[1]);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 体力上限
	 */
	public int getPowerUpperLimit() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 74);
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 回复1点体力的时间（分钟）
	 */
	public int getPowerRecoveryTime() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 75);
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 天梯默认开区
	 */
	public String getLadderDefaultOpenArea() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 76);
			return bean.getNum2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 玩家默认每天打天梯次数
	 */
	public int getLadderPoint() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 77);
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 多少玩家开启天梯的区
	 */
	public int getLadderOpenAreaPersonNum() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 78);
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 购买天梯次数消耗
	 */
	public int getLadderBuyPointCost() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 79);
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 天梯匹配机器人时间
	 */
	public int getLadderMatchRobotTime() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 80);
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取天梯邮件标题
	 */
	public String getLadderMailTitle() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 82);
			return bean.getNum2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取天梯邮件内容
	 */
	public String getLadderMailContent() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 81);
			return bean.getNum2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 天梯结束时间
	 */
	public int getLadderEndTime() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 83);
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 天梯每天的开放时间 4,5
	 */
	public String getLadderDailyOpenTime() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 84);
			return bean.getNum2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// 购买PVE 1点体力花费
	private ItemVo onePowerCostBagInfo;
	// 购买PVE 满体力花费
	private ItemVo fullPowerCostBagInfo;

	/**
	 * 购买PVE 1点体力花费
	 */
	public ItemVo getPveBuyCostPowerOne() {
		if (onePowerCostBagInfo != null) {
			return onePowerCostBagInfo;
		}

		ConfigBean bean = Datas.get(ConfigBean.class, 85);
		onePowerCostBagInfo = ItemUtil.fromItemString(bean.getNum2());
		return onePowerCostBagInfo;
	}

	/**
	 * 购买PVE 满体力花费
	 */
	public ItemVo getPveBuyCostPowerFull() {
		if (fullPowerCostBagInfo != null) {
			return fullPowerCostBagInfo;
		}

		ConfigBean bean = Datas.get(ConfigBean.class, 106);
		fullPowerCostBagInfo = ItemUtil.fromItemString(bean.getNum2());
		return fullPowerCostBagInfo;
	}

	/**
	 * 每次分享获得的奖励内容
	 */
	public String getShareReward() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 87);
			return bean.getNum2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 战斗结算看广告额外获得奖励倍数。2倍结算填1；3倍结算填2
	 */
	public int getBattleExtraReward() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 88);
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * 评论上限
	 */
	public int getCommentUpLimit() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 98);
			if (bean == null) {
				return 10;
			}
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/***
	 * 月卡B无限体力时间
	 */
	public String getMonthCardBNoLimitPowerTime() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 90);
			return bean.getNum2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 月卡Bpve次数
	 */
	public int getMonthCardBPveNumAdd() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 94);
			if (bean == null) {
				return 1;
			}
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/***
	 * 月卡B体力回复时间加成
	 */
	public float getMonthCardBPowerTimeAdd() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 92);
			if (bean == null) {
				return 1.0f;
			}
			return bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1.0f;
	}

	/**
	 * 每天抽奖次数
	 */
	public int getDaiLyLuckDrawNum() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 98);
			if (bean == null) {
				return 10;
			}
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 10;
	}

	/**
	 * 获得修改昵称花费
	 * 
	 * @return
	 */
	public String getChangeNameCost() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 89);
			return bean.getNum2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 月卡额外增加精英副本每日购买次数
	 */
	public int getMonthCardEliteDupBuyTimes() {
		ConfigBean bean = Datas.get(ConfigBean.class, 107);
		if (bean == null) {
			return 1;
		}
		return (int) bean.getNum1();
	}

	/**
	 * 精英副本每日购买次数上限
	 */
	public int getEliteDupBuyTimesLimit() {
		ConfigBean bean = Datas.get(ConfigBean.class, 108);
		if (bean == null) {
			return 2;
		}
		return (int) bean.getNum1();
	}

	/**
	 * 精英副本购买一次挑战次数消耗钻石数
	 */
	public int getEliteDupBuyCost() {
		ConfigBean bean = Datas.get(ConfigBean.class, 109);
		if (bean == null) {
			return 50;
		}
		return (int) bean.getNum1();
	}

	/**
	 * 精英副本每日免费挑战次数
	 */
	public int getEliteDupDialyFreeTimes() {
		ConfigBean bean = Datas.get(ConfigBean.class, 110);
		if (bean == null) {
			return 50;
		}
		return (int) bean.getNum1();
	}

	/**
	 * 精英副本每次购买步数消耗钻石数
	 */
	public int getEliteDupAddStepsCost() {
		ConfigBean bean = Datas.get(ConfigBean.class, 111);
		if (bean == null) {
			return 50;
		}
		return (int) bean.getNum1();
	}

	/**
	 * 精英副本每次购买步数增加的步数
	 */
	public int getEliteDupAddStepsCount() {
		ConfigBean bean = Datas.get(ConfigBean.class, 112);
		if (bean == null) {
			return 20;
		}
		return (int) bean.getNum1();
	}

	/**
	 * 精英副本挑战购买步数次数限制（每张门票的限制）
	 */
	public int getEliteDupStepsAddTimesLimit() {
		ConfigBean bean = Datas.get(ConfigBean.class, 113);
		if (bean == null) {
			return 2;
		}
		return (int) bean.getNum1();
	}

	/**
	 * 钻石兑换金币的比例
	 */
	public float getExchageGoldRatio() {
		ConfigBean bean = Datas.get(ConfigBean.class, 114);
		if (bean == null) {
			return 1;
		}
		return bean.getNum1();
	}

	/**
	 * 获得活动的活跃值
	 * 
	 * @return
	 */
	public int getAchieveDegree() {
		try {
			ConfigBean bean = Datas.get(ConfigBean.class, 111111);
			if (bean == null) {
				return 100;
			}
			return (int) bean.getNum1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
