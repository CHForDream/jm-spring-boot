package com.game.myapp.module.function;

import java.util.Hashtable;
import java.util.Map;

import com.game.myapp.module.function.contoller.IFunctionCheck;
import com.game.myapp.module.function.contoller.ItemFunctionCheck;
import com.game.myapp.module.function.contoller.PveFunctionCheck;
import com.game.myapp.module.function.contoller.RoleLevelFunctionCheck;

public class FunctionCheckFactory {

	private Map<Integer, IFunctionCheck> controllers = new Hashtable<Integer, IFunctionCheck>();

	public FunctionCheckFactory() {
		register();
	}

	private void register() {
		controllers.put(ISystemId.ROLE_LEVEL_TYPE, new RoleLevelFunctionCheck());
		controllers.put(ISystemId.ITEM_TYPE, new ItemFunctionCheck());
		controllers.put(ISystemId.PVE_TYPE, new PveFunctionCheck());
	}

	public IFunctionCheck getFunctionCheckHandler(int type) {
		return controllers.get(type);
	}
}
