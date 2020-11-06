package com.game.myapp.module.activity;

import java.util.List;

import com.game.db.entity.ActivityEntity;
import com.game.utils.JsonUtil;
import com.game.utils.StringUtils;
import com.game.vo.ActivityRewardVo;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class RoleActivityInfo {
	/** 活动Id */
	private int activityId;
	/** 版本 */
	private long version;
	/** 已完成目标 */
	private int myTarget;
	/** 重置时间 */
	private long resetTime;
	/** 活动奖励信息 */
	private List<RoleActivityRewardInfo> rewardList = Lists.newArrayList();

	public void init(ActivityEntity activity) {
		this.activityId = activity.getId();
		this.myTarget = 0;
		this.version = activity.getVersion();
		this.resetTime = System.currentTimeMillis();
		this.rewardList.clear();
		String rewardsStr = activity.getRewards();
		if (!StringUtils.isEmpty(rewardsStr)) {
			List<ActivityRewardVo> rewardList = JsonUtil.toList(rewardsStr, ActivityRewardVo.class);
			for (ActivityRewardVo activityRewardVo : rewardList) {
				RoleActivityRewardInfo roleActivityRewardInfo = new RoleActivityRewardInfo();
				roleActivityRewardInfo.setTarget(activityRewardVo.getTarget());
				roleActivityRewardInfo.setGot(false);
				this.rewardList.add(roleActivityRewardInfo);
			}
		}
	}
}
