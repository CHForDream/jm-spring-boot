package com.game.common.logf.impl;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleLogoutLog extends AbstractLog {
	private long loginTime; // 登录时间
	private int lv = 1; // 玩家等级
	private int blueCash; // 充值钻石
	private int rewardBlueCash; // 钻石奖励
	private int coin; // 金币
	private int gold; // 暂时放弃
	private int rank; // 暂时放弃

	public RoleLogoutLog(UserBean userbean) {
		super(LogfType.ROLE_LOGOUT, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(loginTime).append("|").append(lv).append("|").append(blueCash).append("|").append(rewardBlueCash)
				.append("|").append(coin).append("|").append(gold).append("|").append(rank);
		return sb.toString();
	}
}
