package com.game.myapp.module.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleActivityRewardInfo {
	/** 奖励目标 */
	private int target;
	/** 是否已领取 */
	private boolean isGot;
}
