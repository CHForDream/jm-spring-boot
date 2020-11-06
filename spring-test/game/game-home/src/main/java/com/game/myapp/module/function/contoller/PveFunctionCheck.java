/**
 * 
 */
package com.game.myapp.module.function.contoller;

import com.game.myapp.module.function.ISystemId;

/**
 * @author pky
 *
 */
public class PveFunctionCheck extends AbsFunctionCheck {

	@Override
	public int getType() {
		return ISystemId.PVE_TYPE;
	}

	@Override
	boolean check(int config, int param) {
		return config > param;
	}

}
