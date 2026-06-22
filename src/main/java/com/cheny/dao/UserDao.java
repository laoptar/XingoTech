package com.cheny.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cheny.entity.User;

public interface UserDao {
	User selectUser(String userId);

	List<User> selectUserByPage();

	User selectUserByUserName(String userName);

	int insertUser(User user);

	int updateUser(User user);

	int updateEnabled(@Param("userId") String userId, @Param("enabled") Integer enabled);
}