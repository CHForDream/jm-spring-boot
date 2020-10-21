package org.dubbo.service.impl;

import org.dubbo.pojo.User;
import org.dubbo.service.UserService;

public class UserServiceImpl implements UserService {

	public User getUser(int id) {
		return new User(id);
	}
}
