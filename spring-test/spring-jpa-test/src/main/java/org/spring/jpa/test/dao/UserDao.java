package org.spring.jpa.test.dao;

import org.spring.jpa.test.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByUserId(int userId);
}
