package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemLog extends AbstractLog {
	private String itemChannel;// 获得渠道
	private String itemStr;// 道具信息
	private String arg;// 公用参数
	private String[] paramArray;// 其他参数

	public ItemLog(LogfType logfType, UserBean userbean, String itemChannel, String itemStr, String arg, String[] paramArray) {
		super(logfType, userbean);

		this.itemChannel = itemChannel;
		this.itemStr = itemStr;
		this.arg = arg;
		this.paramArray = paramArray;
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(itemChannel).append("|").append(itemStr).append("|").append(arg)
				// 角色等级(已弃用)
				.append("|").append(1);
		if (paramArray != null && paramArray.length > 0) {
			for (String param : paramArray) {
				sb.append("|").append(param);
			}
		}
		return sb.toString();
	}
}
