package com.game.gm.vo;

import java.util.List;

import com.game.utils.JsonUtil;
import com.game.vo.ActivityRewardVo;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class ActivityVo {
	private int type;// 活动id
	private long id;// 活动批次id，时间戳，每次必增长
	private long show_start_time;// 活动显示开始时间
	private long show_end_time;// 活动显示结束时间
	private long start_time;// 活动开始时间
	private long end_time;// 活动结束时间
	private int open;// 活动是否开启
	private int pri;// 排序优先级：大到小
	private String name;// 活动名称
	private String dec;// 活动描述
	List<ActivityRewardVo> rewards = Lists.newArrayList();// 活动对应奖励内容

	public static void main(String[] args) {
		ActivityVo activityVo = new ActivityVo();
		activityVo.setType(1);
		activityVo.setId(System.currentTimeMillis());
		activityVo.setShow_start_time(0);
		activityVo.setShow_end_time(0);
		activityVo.setStart_time(0);
		activityVo.setEnd_time(0);
		activityVo.setOpen(1);
		activityVo.setPri(1);
		activityVo.setName("活动名");
		activityVo.setDec("活动描述");
		ActivityRewardVo itemVo1 = new ActivityRewardVo();
		itemVo1.setTarget(1);
		itemVo1.setItems("3,3,1;3,4,1");
		ActivityRewardVo itemVo2 = new ActivityRewardVo();
		itemVo2.setTarget(2);
		itemVo2.setItems("3,3,2;3,4,2");
		activityVo.getRewards().add(itemVo1);
		activityVo.getRewards().add(itemVo2);
		String json = JsonUtil.toJson(activityVo);
		System.out.println(activityVo);
		System.out.println(json);

		ActivityVo activityVoResult = JsonUtil.toObj(json, ActivityVo.class);
		System.out.println(activityVoResult);
	}
}
