package com.cheny.service.impl;

import com.cheny.dao.UserDao;
import com.cheny.entity.User;
import com.cheny.service.UserService;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public User selectUser(long userId) {
		return userDao.selectUser(userId);
	}

}
