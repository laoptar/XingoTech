package com.cheny.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtUtil {
	// 生产环境应将密钥配置在外部配置文件中，不可硬编码
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000; // 24小时

	/**
	 * 生成 JWT 令牌
	 */
	public static String generateToken(String userId, String username, String platform) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", userId);
		claims.put("username", username);
		claims.put("platform", platform);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
				.signWith(SECRET_KEY)
				.compact();
	}

	/**
	 * 验证令牌是否有效
	 */
	public static boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 从令牌中解析 Claims
	 */
	public static Claims parseToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET_KEY)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	/**
	 * 从令牌中获取用户ID
	 */
	public static String getUserId(String token) {
		Claims claims = parseToken(token);
		return claims.get("userId", String.class);
	}

	/**
	 * 从令牌中获取用户名
	 */
	public static String getUsername(String token) {
		Claims claims = parseToken(token);
		return claims.getSubject();
	}

	/**
	 * 从令牌中获取平台
	 */
	public static String getPlatform(String token) {
		Claims claims = parseToken(token);
		return claims.get("platform", String.class);
	}

	/**
	 * 获取令牌过期时间（毫秒）
	 */
	public static long getExpirationMs() {
		return EXPIRATION_MS;
	}
}