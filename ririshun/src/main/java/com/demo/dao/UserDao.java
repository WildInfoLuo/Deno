package com.demo.dao;

import com.demo.entity.User;

public interface UserDao {
	public User load(Long id);
	public boolean save(User user);
	public boolean login(String userName, String paw);
	
}
