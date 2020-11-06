package com.game.myapp.module.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.game.myapp.GameGlobals;
import com.game.myapp.module.event.ActivityChangedEvent;

@Component
@Async("gameExecutor")
public class ActivityChangedListener implements ApplicationListener<ActivityChangedEvent> {

	@Override
	public void onApplicationEvent(ActivityChangedEvent event) {
		// 通知当服玩家活动数据变化
		GameGlobals.activityManager.notifyOnlineUserActivityChanged();
	}
}
