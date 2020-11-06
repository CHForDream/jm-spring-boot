package com.game.myapp.module.pvecity.handler.impl;

import com.game.core.handler.MsgBack;
import com.game.myapp.module.pvecity.handler.IPveCityStatusHandler;

public class LockStatusHandler implements IPveCityStatusHandler {
	private static LockStatusHandler _Instance = new LockStatusHandler();

	public static LockStatusHandler getInstance() {
		return _Instance;
	}

	@Override
	public void process(MsgBack msgBack, long uid) {
	}

}
