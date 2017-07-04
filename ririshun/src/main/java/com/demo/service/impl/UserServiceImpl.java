package com.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.service.UserService;


@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao  userDao;
	
	public User load(Long id) {
		// TODO Auto-generated method stub
		return userDao.load(id);
	}
	
	public boolean login(String userName, String paw) {
		// TODO Auto-generated method stub
		  return userDao.login(userName,paw);
	}
	
	public boolean save(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

}
