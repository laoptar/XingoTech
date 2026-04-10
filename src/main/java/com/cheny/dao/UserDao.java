package com.cheny.dao;

import java.util.List;

import com.cheny.entity.User;

public interface UserDao {
	User selectUser(long id);
	
	List<User> selectUserByPage();
	
	int insertUser(User user);
	
	int updateUser(User user);
	
	int deleteUserById(Long id);
}
