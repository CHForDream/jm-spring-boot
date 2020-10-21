package org.dubbo.server;

import java.io.IOException;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.dubbo.service.UserService;
import org.dubbo.service.impl.UserServiceImpl;

public class DubboServer {
	public static void main(String[] args) {
		// 暴露service
		// application
		// 协议 protocol  -dubbo 协议 一般
		// register 注册中心
		// service 

		ApplicationConfig application = new ApplicationConfig("sample-app");

		ProtocolConfig provider = new ProtocolConfig();
		provider.setName("dubbo"); // 协议
		provider.setPort(-1); //20880

		RegistryConfig registryConfig = new RegistryConfig(RegistryConfig.NO_AVAILABLE);

		ServiceConfig<UserService> service = new ServiceConfig<UserService>();
		service.setInterface(UserService.class);
		service.setRef(new UserServiceImpl());
		service.setApplication(application);
		service.setRegistry(registryConfig);
		service.setProtocol(provider);
		service.export();

		System.out.println("dubbo service started");
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
