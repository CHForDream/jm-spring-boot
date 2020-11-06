package com.game.robot;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.PropertyConfigurator;

import com.game.annotation.ProcessorDefine;
import com.game.common.msg.MessageManager;
import com.game.robot.config.Config;
import com.game.robot.strategy.EStrategy;
import com.game.robot.test.ITest;
import com.game.robot.test.impl.PveStressTest;
import com.game.robot.test.impl.StressTest;
import com.game.robot.test.impl.UnitTest;
import com.game.utils.AnnotationUtils;

public class RobotMain {
	public static AtomicLong TOTAL_RUNNING_TIME = new AtomicLong();
	public static AtomicInteger STRATEGY_COUNT = new AtomicInteger();

	public static ITest robotTest = null;

	public static void main(String[] args) {
		// 初始化参数
		init();

		switch (Config.CASE_TYPE) {
		case 0:// 功能单元测试
			robotTest = new UnitTest();
			break;
		case 1: // 功能压力测试
			robotTest = new StressTest();
			break;
		case 2: // PVE压力测试
			robotTest = new PveStressTest();
			break;
		default:
			robotTest = new UnitTest();
			break;
		}
		robotTest.testRobot();
	}

	public static void strategyCount(EStrategy eStrategy, long costTime) {
		robotTest.strategyCount(eStrategy, costTime);
	}

	public static void messageCount(long costTime) {
		robotTest.messageCount(costTime);
	}

	private static void init() {
		// log4j配置
		PropertyConfigurator.configure(System.getProperty("user.dir") + File.separator + "config" + File.separator + "log4j.properties");

		// 初始化配置文件
		Config.initConfig();

		// 加载消息文件映射关系
		try {
			MessageManager.getInstance().registClientMsg(AnnotationUtils.findBeanClasses("com.game.robot.msg.processor", ProcessorDefine.class));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
