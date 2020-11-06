package com.game.myapp.rpc.impl;

import org.apache.dubbo.config.annotation.DubboService;

import com.game.global.Globals;
import com.game.rpc.service.IOnlineService;

@DubboService
public class OnlineServiceImpl implements IOnlineService {
	@Override
	public int getOnlineCount() {
		// 在线人数
		return Globals.getUserManager().getOnlinePlayerNum();
	}
}
