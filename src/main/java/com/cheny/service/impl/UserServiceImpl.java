package com.cheny.service.impl;

import com.cheny.dao.UserDao;
import com.cheny.entity.User;
import com.cheny.service.UserService;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	@Override
	public User selectUser(long userID) {
		return userDao.selectUser(userID);
	}

	@Override
	public List<User> findUserByPage() {
		return userDao.selectUserByPage();
	}

	@Override
	public int addedUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public int modifyUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int removeUser(long userID) {
		return userDao.deleteUserById(userID);
	}

}
