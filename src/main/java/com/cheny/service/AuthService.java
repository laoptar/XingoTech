package com.cheny.service;

import com.cheny.entity.Msg;

public interface AuthService {
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @param platform 平台标识（web, mobile, etc.）
	 * @return Msg，成功时包含 token 和用户信息
	 */
	Msg login(String username, String password, String platform);

	/**
	 * 用户注销
	 * @param token 当前令牌
	 * @return Msg
	 */
	Msg logout(String token);
}