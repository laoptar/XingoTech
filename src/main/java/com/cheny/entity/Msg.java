package com.cheny.entity;

import java.util.HashMap;
import java.util.Map;

public class Msg {
	// 状态码 200-成功,400-失败
	private int code;
	// 信息-说明
	private String msg;
	// 返回给客户端/浏览器的数据-Map集合
	private Map<String, Object> extendInfo = new HashMap<>();
	
	public static Msg success() {
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("success");
		return result;
	}
	
	public static Msg failure() {
		Msg result = new Msg();
		result.setCode(400);
		result.setMsg("failure");
		return result;
	}
	
	public Msg add(String key, Object value) {
		this.getExtendInfo().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Map<String, Object> getExtendInfo() {
		return extendInfo;
	}

	public void setExtendInfo(Map<String, Object> extendInfo) {
		this.extendInfo = extendInfo;
	}
}
