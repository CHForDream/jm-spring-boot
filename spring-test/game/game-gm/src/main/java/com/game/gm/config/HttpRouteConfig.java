package com.game.gm.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.game.gm.GmHttpHandler;

@Configuration
@EnableCaching
public class HttpRouteConfig {
	/**
	 * Http消息处理映射配置: GM
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<GmHttpHandler> getGmServiceBean() {
		ServletRegistrationBean<GmHttpHandler> bean = new ServletRegistrationBean<GmHttpHandler>(new GmHttpHandler()); // 放入自己的Servlet对象实例
		bean.addUrlMappings("/gm"); // 访问路径值
		return bean;
	}
}