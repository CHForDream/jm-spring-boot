package org.spring.jpa.test.service;

import java.util.List;

import org.spring.jpa.test.pojo.UserEntity;

public interface IService {
	public void save(UserEntity obj);
	
	public List<UserEntity> listAll();
	
	public UserEntity findById(int id);
}
