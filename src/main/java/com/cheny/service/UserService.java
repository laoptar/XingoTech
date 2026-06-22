package com.cheny.service;

import java.util.List;

import com.cheny.entity.User;

public interface UserService {
	public User selectUser(String userId);

	public List<User> findUserByPage();

	public User findUserByUserName(String userName);

	public int addedUser(User user);

	public int modifyUser(User user);

	public int enableUser(String userId);

	public int disableUser(String userId);

	public List<User> exportUsers();
}