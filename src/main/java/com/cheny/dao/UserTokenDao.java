package com.cheny.dao;

import java.util.List;

import com.cheny.entity.UserToken;

public interface UserTokenDao {
	UserToken selectById(Long id);

	UserToken selectByToken(String token);

	List<UserToken> selectActiveByUserId(String userId);

	int insert(UserToken userToken);

	int updateStatus(UserToken userToken);

	int deactivateByUserId(String userId);
}