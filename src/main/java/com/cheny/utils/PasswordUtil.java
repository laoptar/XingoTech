package com.cheny.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

	/**
	 * 对明文密码进行 BCrypt 哈希
	 */
	public static String hash(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	}

	/**
	 * 校验明文密码是否匹配哈希值
	 */
	public static boolean verify(String plainPassword, String hashed) {
		return BCrypt.checkpw(plainPassword, hashed);
	}
}