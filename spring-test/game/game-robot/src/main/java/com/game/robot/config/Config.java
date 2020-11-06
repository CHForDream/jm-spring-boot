package com.game.robot.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

import com.game.constants.Loggers;

public class Config {
	/** test */
	public static String SERVER_HTTP = "http://192.168.10.109";
	public static String SERVER_HTTP_URL = "http://192.168.10.109/process";

	// netty
	public static String SERVER_BATTLE_IP = "192.168.10.109";
	public static int SERVER_BATTLE_PORT = 8088;
	// websocket
	public static String SERVER_BATTLE_WEBSOCKET_URL = "ws://192.168.10.109/ws/websocket";

	public static String SERVER_CHAT_IP = "192.168.10.109";
	public static int SERVER_CHAT_PORT = 8089;

	public static String USER_NAME = "test";// 机器人账号名
	public static String PASSWORD = "1";// 机器人账号密码
	public static int ROBOT_START_ID = 1;// 机器人起始Id值
	public static int ROBOT_COUNT = 1;// 机器人数量
	public static int CASE_TYPE = 0;// 测试case运行类型

	public static boolean IS_PRINT_LOG = true;// 是否打印日志

	public static void initConfig() {
		try {
//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//			URL url = classLoader.getResource("config.properties");
			String path = System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.properties";
			Loggers.robotLogger.info("config path = " + path);
			InputStream is = new FileInputStream(URLDecoder.decode(path, "UTF-8"));
			Properties properties = new Properties();
			properties.load(is);

			// 测试机器人配置
			initRobotConfig(properties);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private static void initRobotConfig(Properties properties) {
		// server
		SERVER_HTTP = properties.getProperty("test.server.http");// 游戏服
		SERVER_HTTP_URL = SERVER_HTTP + "/process";

		SERVER_BATTLE_IP = properties.getProperty("test.server.battle");// 战斗服IP
		SERVER_BATTLE_PORT = Integer.parseInt(properties.getProperty("test.server.battle.port"));// 战斗服端口
		SERVER_BATTLE_WEBSOCKET_URL = "ws://" + SERVER_BATTLE_IP + "/ws/websocket";

		SERVER_CHAT_IP = properties.getProperty("test.server.chat");// 聊天服
		SERVER_CHAT_PORT = Integer.parseInt(properties.getProperty("test.server.chat.port"));// 聊天服端口

		// robot
		USER_NAME = properties.getProperty("test.robot.name");// 机器人账号名
		PASSWORD = properties.getProperty("test.robot.password");// 机器人账号密码
		ROBOT_START_ID = Integer.parseInt(properties.getProperty("test.robot.startId"));// 机器人起始Id值
		ROBOT_COUNT = Integer.parseInt(properties.getProperty("test.robot.count"));// 机器人数量

		// case
		CASE_TYPE = Integer.parseInt(properties.getProperty("test.case.type"));// 测试case运行类型

		// log
		IS_PRINT_LOG = Boolean.parseBoolean(properties.getProperty("test.log.print"));

		// 打印配置信息
		Loggers.robotLogger.info("SERVER_HTTP = " + SERVER_HTTP);
		Loggers.robotLogger.info("SERVER_BATTLE = " + SERVER_BATTLE_IP + ", SERVER_BATTLE_PORT = " + SERVER_BATTLE_PORT);
		Loggers.robotLogger.info("SERVER_CHAT = " + SERVER_CHAT_IP + ", SERVER_CHAT_PORT = " + SERVER_CHAT_PORT);
		Loggers.robotLogger.info("USER_NAME = " + USER_NAME + ", PASSWORD = " + PASSWORD + ", ROBOT_START_ID = " + ROBOT_START_ID + ", ROBOT_COUNT = "
				+ ROBOT_COUNT + ", CASE_TYPE = " + CASE_TYPE);
		Loggers.robotLogger.info("CASE_TYPE = " + CASE_TYPE + ", IS_PRINT_LOG = " + IS_PRINT_LOG);
	}
}
