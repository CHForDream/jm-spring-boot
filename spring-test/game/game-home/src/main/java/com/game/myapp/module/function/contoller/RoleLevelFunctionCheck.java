package com.game.myapp.module.function.contoller;

import com.game.myapp.module.function.ISystemId;

public class RoleLevelFunctionCheck extends AbsFunctionCheck {

	@Override
	boolean check(int config, int param) {
		return config > param;
	}

	@Override
	public int getType() {
		return ISystemId.ROLE_LEVEL_TYPE;
	}

}
