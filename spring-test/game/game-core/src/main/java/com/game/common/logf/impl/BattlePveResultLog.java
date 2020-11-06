package com.game.common.logf.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;
import com.game.vo.BuyStepsVo;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BattlePveResultLog extends AbstractLog {
	private int zero;// 占位参数
	private int targetId;
	private int result;
	private List<ItemVo> rewards = new ArrayList<ItemVo>(); // 结算奖励
	private int stepCost; // 使用步数
	private int leftStep; // 剩余步数
	private int totalStep; // 总步数
	private int skillNum; // 使用技能数量
	private Map<Integer, Integer> leftTargets = new HashMap<Integer, Integer>(); // 剩余目标数量
	private Map<Integer, Integer> totalTargets = new HashMap<Integer, Integer>(); // 目标数量
	private List<Integer> preItems = new ArrayList<Integer>(); // 战前道具
	private List<Integer> battleItems = new ArrayList<Integer>(); // 战斗中使用的道具
	private List<BuyStepsVo> buyStepInfo = new ArrayList<BuyStepsVo>(); // 失败后购买步数的记录
	private int heroId;// 英雄id
	private int power;// 玩家体力值

	public BattlePveResultLog(UserBean userbean) {
		super(LogfType.BATTLE_PVE_COMPLETE_APP, userbean);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(zero).append("|").append(targetId).append("|").append(result).append("|")
				.append(ItemUtil.toInfoString(rewards)).append("|").append(stepCost).append("|").append(leftStep).append("|").append(totalStep).append("|")
				.append(skillNum).append("|").append(getLogForTarget(leftTargets)).append("|").append(getLogForTarget(totalTargets)).append("|")
				.append(getLogForListItem(preItems)).append("|").append(getLogForListItem(battleItems)).append("|").append(getContinueInfo()).append("|")
				.append(heroId).append("|").append(power);
		return sb.toString();
	}

	private String getContinueInfo() {
		String str = "";
		if (this.buyStepInfo == null || buyStepInfo.isEmpty()) {
			return str;
		}
		for (BuyStepsVo buyStepsVo : this.buyStepInfo) {
			str = str + buyStepsVo.getTime() + ",";
			str = str + buyStepsVo.getSteps() + ",";
			ItemVo costItem = buyStepsVo.getCostItem();
			str = str + costItem.getType() + "," + costItem.getId() + "," + costItem.getNum() + ";";
		}
		return str;
	}

	private String getLogForTarget(Map<Integer, Integer> targetMap) {
		String str = "";
		if (targetMap == null || targetMap.isEmpty()) {
			return str;
		}
		for (int chessId : targetMap.keySet()) {
			str = str + chessId + ":" + targetMap.get(chessId) + ";";
		}
		return str;
	}

	private String getLogForListItem(List<Integer> items) {
		String str = "";
		if (items == null || items.isEmpty()) {
			return str;
		}
		for (int itemId : items) {
			str = str + itemId + ",";
		}
//		List<ItemVo> itemList = new ArrayList<ItemVo>();
//		for (int itemId : items) {
//			ItemVo item = new ItemVo();
//			item.setType(3);
//			item.setId(itemId);
//			item.setNum(1);
//			itemList.add(item);
//		}
//		return ItemUtil.toInfoString(itemList);
		return str;
	}
}
