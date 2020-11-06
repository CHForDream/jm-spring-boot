package com.game.myapp.module.lamp;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.game.common.session.GameSession;
import com.game.global.Globals;
import com.google.common.collect.Sets;

import buffer.GCLampMsg;

/**
 * 走马灯
 * 
 */
public class HorseLampManager {

	private Map<Long, LampBean> lampMap = new ConcurrentHashMap<Long, LampBean>();
	private Set<Long> removedIdSet = Sets.newConcurrentHashSet();

	/**
	 * 添加跑马灯
	 */
	public void addLamp(LampBean lampBean) {
		if (lampMap.size() > 100) {
			return;
		}
		lampMap.put(lampBean.getId(), lampBean);
	}

	public void execute() {
		for (Map.Entry<Long, LampBean> entry : lampMap.entrySet()) {
			onRun(entry.getValue());
		}

		for (Long temp : removedIdSet) {
			lampMap.remove(temp);
		}
	}

	public void onRun(LampBean lampBean) {
		long now = System.currentTimeMillis();
		if (now < lampBean.getStartTime()) {
			// 未开始
			return;
		}

		if (now >= lampBean.getEndTime()) {
			// 已结束
			removedIdSet.add(lampBean.getId());
			return;
		}

		if (now <= lampBean.getLastTime() + lampBean.getInterval()) {
			// CD中
			return;
		}

		lampBean.setLastTime(lampBean.getLastTime() + lampBean.getInterval());

		// 发送跑马灯
		GCLampMsg.GCLampMsgProto.Builder bakMsg = GCLampMsg.GCLampMsgProto.newBuilder();
		bakMsg.setContent(lampBean.getContent());
		bakMsg.setSort(1);
		Map<Long, GameSession> session = Globals.getChatSessionManager().getSessionMap();
		for (GameSession temp : session.values()) {
			temp.sendMsg(bakMsg);
		}
	}
}
