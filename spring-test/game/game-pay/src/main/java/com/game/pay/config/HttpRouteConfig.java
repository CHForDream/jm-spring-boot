package com.game.pay.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.game.pay.PayHttpHandler;

@Configuration
@EnableCaching
public class HttpRouteConfig {
	/**
	 * Http消息处理映射配置: 支付回调
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<PayHttpHandler> getGmServiceBean() {
		ServletRegistrationBean<PayHttpHandler> bean = new ServletRegistrationBean<PayHttpHandler>(new PayHttpHandler()); // 放入自己的Servlet对象实例
		bean.addUrlMappings("/pay"); // 访问路路由
		return bean;
	}
}