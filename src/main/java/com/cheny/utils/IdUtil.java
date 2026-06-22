package com.cheny.utils;

import java.security.SecureRandom;

public class IdUtil {

	private static final SecureRandom RANDOM = new SecureRandom();

	private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

	/**
	 * 生成 24 位十六进制随机字符串，用作 user_id 主键
	 */
	public static String generateUserId() {
		byte[] bytes = new byte[12];
		RANDOM.nextBytes(bytes);
		char[] hex = new char[24];
		for (int i = 0; i < 12; i++) {
			hex[i * 2] = HEX_CHARS[(bytes[i] & 0xf0) >>> 4];
			hex[i * 2 + 1] = HEX_CHARS[bytes[i] & 0x0f];
		}
		return new String(hex);
	}
}