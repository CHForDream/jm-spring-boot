package com.game.robot.strategy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EStrategyType {

	EStrategy strategy();
}
