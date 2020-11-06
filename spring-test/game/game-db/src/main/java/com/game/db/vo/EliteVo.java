package com.game.db.vo;

import com.game.constants.EliteConstants;

import lombok.Data;

@Data
public class EliteVo {
	private int eliteId;// 精英副本Id
	private int freeTimes;// 免费挑战次数（每日重置,初始化）
	private int challengeTimes;// 购买的挑战次数
	private int challengeBuyTimes;// 挑战门票剩余购买次数（每日重置,初始化）
	private int state;// -1为解锁；0未开始；1-3挑战进度
	private int steps;// 剩余步数（每次挑战开始,初始化）
	private int stepBuyTimes;// 步数购买次数（每次挑战开始,初始化）

	public void resetState() {
		state = EliteConstants.STATE_NOT_START;
		steps = 0;
		stepBuyTimes = 0;
	}
}
