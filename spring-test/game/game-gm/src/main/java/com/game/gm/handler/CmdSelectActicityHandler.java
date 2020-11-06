package com.game.gm.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.constants.CMDConstants;
import com.game.db.entity.ActivityEntity;
import com.game.global.Globals;
import com.game.gm.AbsGmContoller;
import com.game.utils.JsonUtil;
import com.game.utils.StringUtils;
import com.game.vo.ActivityRewardVo;
import com.game.vo.ResponseData;

/**
 * 查看活动信息
 * 
 */
@SuppressWarnings("rawtypes")
public class CmdSelectActicityHandler extends AbsGmContoller {

	@Override
	public Object handler(HttpServletRequest request, HttpServletResponse response) {
		ResponseData<String, Object> resultData = ResponseData.newBuild();
		List<ResponseData> activitys = new ArrayList<ResponseData>();
		List<ActivityEntity> activityEntityList = Globals.getEntityProxy().findAllNotCache(ActivityEntity.class);
		for (ActivityEntity entity : activityEntityList) {
			ResponseData<String, Object> activityData = ResponseData.newBuild();
			activityData.put("type", entity.getId());
			activityData.put("name", entity.getName());
			activityData.put("dec", entity.getDescription());
			activityData.put("open", entity.isOpen() ? 1 : 0);
			activityData.put("show_start_time", entity.getShowTimeStart().getTime());
			activityData.put("show_end_time", entity.getShowTimeEnd().getTime());
			activityData.put("start_time", entity.getTimeStart().getTime());
			activityData.put("end_time", entity.getTimeEnd().getTime());
			activityData.put("id", entity.getVersion());
			activityData.put("pri", entity.getPriority());
			List<ResponseData> rewards = new ArrayList<ResponseData>();
			String rewardsStr = entity.getRewards();
			if (!StringUtils.isEmpty(rewardsStr)) {
				List<ActivityRewardVo> rewardList = JsonUtil.toList(rewardsStr, ActivityRewardVo.class);
				for (ActivityRewardVo activityRewardVo : rewardList) {
					ResponseData<String, Object> reward = ResponseData.newBuild();
					reward.put("target", activityRewardVo.getTarget());
					reward.put("items", activityRewardVo.getItems());
					rewards.add(reward);
				}
			}
			activityData.put("rewards", rewards);
			activitys.add(activityData);
		}

		resultData.put(CMDConstants.KEY_STATE, CMDConstants.STATE_SUCCESS);
		resultData.put("activitys", activitys);
		return resultData;
	}
}
