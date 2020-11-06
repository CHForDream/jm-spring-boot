package com.game.common.logf.impl;

import java.util.ArrayList;
import java.util.List;

import com.game.common.logf.AbstractLog;
import com.game.common.logf.LogfType;
import com.game.common.user.UserBean;
import com.game.vo.item.ItemUtil;
import com.game.vo.item.ItemVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityReceiveLog extends AbstractLog {
	private int activityType; // 活动类型
	private long activityId; // 活动id
	private int target; // 活动目标
	private List<ItemVo> itemList = new ArrayList<ItemVo>();

	public ActivityReceiveLog(UserBean userbean) {
		super(LogfType.ACTIVITY_RECEIVE, userbean);
	}

	@Override
	public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getContent()).append("|").append(activityType).append("|").append(activityId).append("|").append(target).append("|")
				.append(ItemUtil.toInfoString(itemList));
		return sb.toString();
	}
}
