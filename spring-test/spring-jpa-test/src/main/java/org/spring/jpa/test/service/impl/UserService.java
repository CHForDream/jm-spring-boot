package org.spring.jpa.test.service.impl;

import java.util.List;

import org.spring.jpa.test.dao.UserDao;
import org.spring.jpa.test.pojo.UserEntity;
import org.spring.jpa.test.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IService {

	@Autowired
	private UserDao userdao;
	
	@Override
	public void save(UserEntity obj) {
		userdao.save(obj);
	}

	@Override
	public List<UserEntity> listAll() {
		return userdao.findAll();
	}

	@Override
	public UserEntity findById(int id) {
		return userdao.findByUserId(id);
	}

}
