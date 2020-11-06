package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemBuyLog extends AbstractLog {
	private String costInfo;// 消耗信息
	private String buyInfo;// 购买道具信息

	public ItemBuyLog(UserBean userbean, String costInfo, String buyInfo) {
		super(LogfType.ITEM_BUY, userbean);

		this.costInfo = costInfo;
		this.buyInfo = buyInfo;
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(costInfo).append("|").append(buyInfo);
		return sb.toString();
	}
}
