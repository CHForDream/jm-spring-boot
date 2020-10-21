package org.dubbo.client;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.dubbo.service.UserService;

import com.alibaba.fastjson.JSON;

public class DubboClient {
	public static void main(String[] args) {
		ApplicationConfig application = new ApplicationConfig("sample-app");
		ReferenceConfig<UserService> reference = new ReferenceConfig<UserService>();
		reference.setApplication(application);
		reference.setUrl("dubbo://192.168.10.111:20881/org.dubbo.service.UserService");
		reference.setInterface(UserService.class);
		UserService user = reference.get();
		System.out.println(JSON.toJSONString(user.getUser(3)));
	}
}
