package com.cheny.service.impl;

import com.cheny.dao.UserDao;
import com.cheny.entity.User;
import com.cheny.service.UserService;
import com.cheny.utils.IdUtil;
import com.cheny.utils.PasswordUtil;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public User selectUser(String userId) {
		return userDao.selectUser(userId);
	}

	@Override
	public List<User> findUserByPage() {
		return userDao.selectUserByPage();
	}

	@Override
	public User findUserByUserName(String userName) {
		return userDao.selectUserByUserName(userName);
	}

	@Override
	public int addedUser(User user) {
		// 自动生成 24 位随机主键
		user.setUserId(IdUtil.generateUserId());
		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			user.setPassword(PasswordUtil.hash(user.getPassword()));
		}
		// 新增用户默认启用
		if (user.getEnabled() == null) {
			user.setEnabled(1);
		}
		return userDao.insertUser(user);
	}

	@Override
	public int modifyUser(User user) {
		// 校验用户主键必须存在
		if (user.getUserId() == null || user.getUserId().isEmpty()) {
			throw new IllegalArgumentException("用户主键不能为空");
		}
		// 校验数据库中必须存在该用户
		User oldUser = userDao.selectUser(user.getUserId());
		if (oldUser == null) {
			throw new IllegalArgumentException("用户不存在，无法修改");
		}

		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			user.setPassword(PasswordUtil.hash(user.getPassword()));
		} else {
			user.setPassword(oldUser.getPassword());
		}
		return userDao.updateUser(user);
	}

	@Override
	public int enableUser(String userId) {
		return userDao.updateEnabled(userId, 1);
	}

	@Override
	public int disableUser(String userId) {
		return userDao.updateEnabled(userId, 0);
	}

	@Override
	public List<User> exportUsers() {
		return userDao.selectUserByPage();
	}

}