package com.cheny.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheny.entity.Msg;
import com.cheny.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Resource
	private AuthService authService;

	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	@ResponseBody
	public Msg login(@RequestParam("username") String username,
					 @RequestParam("password") String password,
					 @RequestParam(value = "platform", defaultValue = "web") String platform) {
		return authService.login(username, password, platform);
	}

	@RequestMapping(value = "/logout", method = {RequestMethod.POST})
	@ResponseBody
	public Msg logout(@RequestHeader("Authorization") String authHeader) {
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return Msg.failure().add("error", "无效的 Authorization 头");
		}
		String token = authHeader.substring(7);
		return authService.logout(token);
	}
}