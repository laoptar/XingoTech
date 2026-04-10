package com.cheny.service;

import java.util.List;

import com.cheny.entity.User;

public interface UserService {
	public User selectUser(long userID);
	
	public List<User> findUserByPage();
	
	public int addedUser(User user);
	
	public int modifyUser(User user);
	
	public int removeUser(long userID);
}
