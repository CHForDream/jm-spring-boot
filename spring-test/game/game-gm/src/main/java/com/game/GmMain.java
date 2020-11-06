package com.game;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.game.constants.Loggers;
import com.game.core.redis.RedisConnFactory;
import com.game.global.Globals;

@SpringBootApplication
public class GmMain {
	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir") + File.separator + "config" + File.separator + "log4j.properties";
		PropertyConfigurator.configure(filePath);
		Loggers.gmLogger.info("log4j config... filePath = " + filePath);

		Globals.applicationContext = SpringApplication.run(GmMain.class, args);
		Loggers.gmLogger.info(Globals.getAppConfigBean());
		RedisConnFactory.init();

		Loggers.serverLogger.info("GM Server running...");
	}
}
