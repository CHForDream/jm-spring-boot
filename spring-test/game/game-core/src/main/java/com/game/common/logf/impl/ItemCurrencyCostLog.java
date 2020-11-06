package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemCurrencyCostLog extends AbstractLog {
	private int costCashCharge = 0;// 钻石消耗
	private int costRewardBlueCash = 0;// 奖励钻石消耗
	private int costCoin = 0;// 金币消耗
	private String itemChannel;// 消耗渠道
	private String reason;// 消耗原因
	private String[] paramArray;// 其他参数

	public ItemCurrencyCostLog(UserBean userbean, int costCashCharge, int costRewardBlueCash, int costCoin, String itemChannel, String reason,
			String[] paramArray) {
		super(LogfType.CURRENCY_COST, userbean);

		this.costCashCharge = costCashCharge;
		this.costRewardBlueCash = costRewardBlueCash;
		this.costCoin = costCoin;
		this.itemChannel = itemChannel;
		this.reason = reason;
		this.paramArray = paramArray;
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(costCashCharge).append("|").append(costRewardBlueCash)//
				.append("|").append(0)// 停用数据
				.append("|").append(costCoin).append("|").append(itemChannel).append("|").append(reason);
		if (paramArray != null && paramArray.length > 0) {
			for (String param : paramArray) {
				sb.append("|").append(param);
			}
		}
		return sb.toString();
	}
}
