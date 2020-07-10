package org.spring.jpa.test.controller;

import java.util.List;

import org.spring.jpa.test.pojo.UserEntity;
import org.spring.jpa.test.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService ;
	
	@RequestMapping("/find/{id}")
	public UserEntity findByUserId(@PathVariable("id") int id) {
		return userService.findById(id);
	}
	
	@RequestMapping("/list")
	public List<UserEntity> listAll() {
		return userService.listAll();
	}
	
	@RequestMapping("/save")
	public String save() {
		for (int i = 0; i < 20; i++) {
			UserEntity entity = new UserEntity();
			entity.setUserId(i);
			entity.setUserAge(i + 13);
			entity.setUserName("user" + i);
			userService.save(entity);
		}
		return "success";
	}
}
