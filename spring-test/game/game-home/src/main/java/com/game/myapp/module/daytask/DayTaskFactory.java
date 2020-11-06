package com.game.myapp.module.daytask;

import com.game.myapp.module.daytask.impl.CommTaskImpl;

public class DayTaskFactory {
	public static IDayTask getImpl(int type) {
		return new CommTaskImpl();
	}
}
