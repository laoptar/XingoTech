package com.cheny.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheny.dao.UserTokenDao;
import com.cheny.entity.Msg;
import com.cheny.entity.User;
import com.cheny.entity.UserToken;
import com.cheny.service.AuthService;
import com.cheny.service.UserService;
import com.cheny.utils.JwtUtil;
import com.cheny.utils.PasswordUtil;

@Service("authService")
public class AuthServiceImpl implements AuthService {

	@Resource
	private UserService userService;

	@Resource
	private UserTokenDao userTokenDao;

	@Override
	@Transactional
	public Msg login(String username, String password, String platform) {
		// 1. 校验用户名密码
		if (username == null || username.trim().isEmpty()) {
			return Msg.failure().add("error", "用户名不能为空");
		}
		if (password == null || password.trim().isEmpty()) {
			return Msg.failure().add("error", "密码不能为空");
		}

		User user = userService.findUserByUserName(username);
		if (user == null) {
			return Msg.failure().add("error", "用户名或密码错误");
		}

		if (!PasswordUtil.verify(password, user.getPassword())) {
			return Msg.failure().add("error", "用户名或密码错误");
		}

		// 2. 同用户只能单平台登录：踢掉该用户所有旧会话
		userTokenDao.deactivateByUserId(user.getUserId());

		// 3. 生成新 JWT 令牌
		String platformName = (platform != null && !platform.trim().isEmpty()) ? platform : "web";
		String token = JwtUtil.generateToken(user.getUserId(), user.getUserName(), platformName);

		// 4. 保存令牌记录
		UserToken userToken = new UserToken();
		userToken.setUserId(user.getUserId());
		userToken.setToken(token);
		userToken.setPlatform(platformName);
		userToken.setLoginTime(new Date());
		userToken.setExpireTime(new Date(System.currentTimeMillis() + JwtUtil.getExpirationMs()));
		userToken.setStatus(1);
		userTokenDao.insert(userToken);

		// 5. 返回成功（不返回 password）
		user.setPassword(null);
		return Msg.success()
				.add("token", token)
				.add("user", user)
				.add("platform", platformName);
	}

	@Override
	@Transactional
	public Msg logout(String token) {
		if (token == null || token.trim().isEmpty()) {
			return Msg.failure().add("error", "token 不能为空");
		}

		UserToken userToken = userTokenDao.selectByToken(token);
		if (userToken == null) {
			return Msg.failure().add("error", "无效的 token");
		}

		userToken.setStatus(0);
		userTokenDao.updateStatus(userToken);

		return Msg.success();
	}
}