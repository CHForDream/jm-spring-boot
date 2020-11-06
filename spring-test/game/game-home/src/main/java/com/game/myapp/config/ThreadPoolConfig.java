package com.game.myapp.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class ThreadPoolConfig {
	/**
	 * 游戏线程池，Dubbo和Spring Event使用
	 * 
	 * @return
	 */
	@Bean("gameExecutor")
	public ExecutorService gameExecutor() {
		ExecutorService executor = Executors.newCachedThreadPool();
		return executor;
	}
}