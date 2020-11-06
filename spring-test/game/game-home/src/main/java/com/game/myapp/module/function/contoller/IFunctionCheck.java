package com.game.myapp.module.function.contoller;

import java.util.List;

public interface IFunctionCheck {

	List<Integer> checkFunctionOpen(long uid, String... args);

	int getType();
}
