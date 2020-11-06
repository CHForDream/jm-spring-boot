package com.game;

import java.io.File;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.game.annotation.ProcessorDefine;
import com.game.common.data.config.build.DataDefine;
import com.game.common.logf.LogfPrinter;
import com.game.constants.Loggers;
import com.game.core.netty.ChatServer;
import com.game.core.redis.RedisConnFactory;
import com.game.core.server.GameServiceManager;
import com.game.db.cache.CacheMapper;
import com.game.global.Globals;
import com.game.myapp.GameGlobals;
import com.game.spring.MySpringContext;

@SpringBootApplication
@ComponentScan(basePackages = { "com" })
@DubboComponentScan(basePackages = { "com.game.myapp.rpc" })
@EnableAsync
@EnableScheduling
@EnableCaching
public class GameMain {
	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir") + File.separator + "config" + File.separator + "log4j.properties";
		PropertyConfigurator.configure(filePath);
//		Loggers.serverLogger.info("log4j config... filePath = " + filePath);

		SpringApplication springApplication = new SpringApplication(GameMain.class);
		springApplication.setApplicationContextClass(MySpringContext.class);
		Globals.applicationContext = springApplication.run(args);

		// 初始化系统配置
		Loggers.serverLogger.info(Globals.getAppConfigBean());

		Loggers.serverLogger.info("Loading redis config...");
		RedisConnFactory.init();

		holdServerStatus();

		startGame(args);

//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			@Override
//			public void run() {
//				shutdowmGame();
//			}
//		});
	}

	public static void holdServerStatus() {
		if (Globals.SREVER_FORCE_ON)
			return;

		GameServiceManager serverStatus = new GameServiceManager();
		serverStatus.start();
		while (true) {
			try {
				Loggers.serverLogger.info("game main...");
				if (Globals.isServerStartStatus())
					break;
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void startGame(String[] args) {
		try {
			initServer();

			Globals.startSever();

			// 同步服务器信息
//			GameGlobals.serverListManager.syncServerInfo();

			for (String temp : args) {
				Loggers.serverLogger.info(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private static void initServer() {
		try {
			// 加载基础数据配置
			Loggers.serverLogger.info("Loading App's data config...");
			Globals.getDataConfigManager().loadDataConfig(Globals.applicationContext.getBeansWithAnnotation(DataDefine.class));
			// 加载基础数据, 在此代码之前, 不要处理任何游戏业务逻辑
			Loggers.serverLogger.info("Loading App's data...");
			Globals.getDataConfigManager().loadData();

			// 初始化redis数据
			GameGlobals.redisManager.init();

			Loggers.serverLogger.info("init msg...");
			Globals.getMessageManager().registMsg(Globals.applicationContext.getBeansWithAnnotation(ProcessorDefine.class));

			GameGlobals.chatManager.start();

			LogfPrinter.getInstance().start();

			// 初始化活动数据
			GameGlobals.activityManager.loadData();

			// 初始化Entity和CacheType的映射关系
			CacheMapper.init();

//			if (Globals.getAppConfigBean().isBattleServer()) {
//				BattleServer.startNetty();
//			}
//			if (Globals.getAppConfigBean().isChatServer()) {
			ChatServer.startNetty();
//			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		Loggers.serverLogger.info("Game Server running...");
	}
}
