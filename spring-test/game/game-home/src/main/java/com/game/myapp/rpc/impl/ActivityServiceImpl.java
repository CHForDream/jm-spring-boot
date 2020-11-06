package com.game.myapp.rpc.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.scheduling.annotation.Async;

import com.game.myapp.GameGlobals;
import com.game.rpc.service.IActivityService;

@DubboService
@Async("gameExecutor")
public class ActivityServiceImpl implements IActivityService {
	@Override
	public void onActivityChanged() {
		GameGlobals.activityManager.onActivityChanged();
	}
}
