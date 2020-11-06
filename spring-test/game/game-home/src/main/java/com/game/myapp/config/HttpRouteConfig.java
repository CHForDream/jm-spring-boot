package com.game.myapp.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.game.core.handler.HttpHandler;

@Configuration
@EnableCaching
public class HttpRouteConfig {

	/**
	 * Http消息处理映射配置: 游戏逻辑
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<HttpHandler> getServletRegistrationBean() { // 一定要返回ServletRegistrationBean
		ServletRegistrationBean<HttpHandler> bean = new ServletRegistrationBean<HttpHandler>(new HttpHandler()); // 放入自己的Servlet对象实例
		bean.addUrlMappings("/process"); // 访问路径值
		return bean;
	}
}