package com.demo.service;

import com.demo.entity.User;


public interface UserService {
	public User load(Long id);
	public boolean save(User  user);
	public  boolean login(String userName,String paw);
}
