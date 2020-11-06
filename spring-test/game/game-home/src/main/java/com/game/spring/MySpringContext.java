package com.game.spring;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

import com.game.constants.Loggers;
import com.game.core.server.GameShutdownProcessor;

public class MySpringContext extends AnnotationConfigServletWebServerApplicationContext {

	@Override
	protected void doClose() {
		Loggers.serverLogger.info("Start Server shutdown!");
		// 在回收bean之前处理关服逻辑
		GameShutdownProcessor.shutdown();

		super.doClose();
	}

}
