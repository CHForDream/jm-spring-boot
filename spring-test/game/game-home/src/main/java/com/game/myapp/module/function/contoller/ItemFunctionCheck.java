package com.game.myapp.module.function.contoller;

import com.game.myapp.module.function.ISystemId;

public class ItemFunctionCheck extends AbsFunctionCheck {

	@Override
	public int getType() {
		return ISystemId.ITEM_TYPE;
	}

	@Override
	boolean check(int config, int param) {
		return config != param;
	}

}
