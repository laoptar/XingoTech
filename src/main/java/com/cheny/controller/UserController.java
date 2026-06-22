package com.cheny.controller;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cheny.entity.Msg;
import com.cheny.entity.User;
import com.cheny.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = {"/select"}, method = {RequestMethod.GET})
	public ModelAndView selectUser(@RequestParam("userId") String userId) throws Exception {
		ModelAndView mv = new ModelAndView();
		User user = userService.selectUser(userId);
		mv.addObject("user", user);
		mv.setViewName("user");
		return mv;
	}

	@RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
	@ResponseBody
	public Msg findUserByPage(@RequestParam(value = "pageindex", defaultValue = "1") Integer pageindex,
			@RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
		PageHelper.startPage(pageindex, pagesize);
		List<User> userList = userService.findUserByPage();
		PageInfo<User> pageInfo = new PageInfo<User>(userList, pagesize);
		return Msg.success().add("pageInfo", pageInfo);
	}

	@RequestMapping(value = {"/addUser"}, method = {RequestMethod.POST})
	@ResponseBody
	public Msg addedUser(@RequestBody User user) throws Exception {
		int rows = userService.addedUser(user);
		return Msg.success().add("rows", rows);
	}

	@RequestMapping(value = {"/editUser"}, method = {RequestMethod.POST})
	@ResponseBody
	public Msg editorUser(@RequestBody User user) throws Exception {
		int rows = userService.modifyUser(user);
		return Msg.success().add("rows", rows);
	}

	@RequestMapping(value = {"/enable/{userId}"}, method = {RequestMethod.POST})
	@ResponseBody
	public Msg enableUser(@PathVariable("userId") String userId) throws Exception {
		int rows = userService.enableUser(userId);
		return Msg.success().add("rows", rows);
	}

	@RequestMapping(value = {"/disable/{userId}"}, method = {RequestMethod.POST})
	@ResponseBody
	public Msg disableUser(@PathVariable("userId") String userId) throws Exception {
		int rows = userService.disableUser(userId);
		return Msg.success().add("rows", rows);
	}

	@RequestMapping(value = {"/export"}, method = {RequestMethod.GET})
	public void exportUsers(HttpServletResponse response) throws Exception {
		List<User> userList = userService.exportUsers();

		response.setContentType("text/csv;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=users.csv");

		try (Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8)) {
			// BOM for Excel compatibility
			writer.write('﻿');
			// header
			writer.write("用户主键,用户名,用户类型,手机号,性别,邮箱,员工姓名,员工编号,办公电话,生效日期,失效日期,是否启用,描述\n");
			// rows
			for (User u : userList) {
				writer.write(String.join(",",
						nullToEmpty(u.getUserId()),
						nullToEmpty(u.getUserName()),
						nullToEmpty(u.getUserType()),
						nullToEmpty(u.getMobilePhone()),
						u.getSex() != null ? (u.getSex() == 0 ? "男" : "女") : "",
						nullToEmpty(u.getEmail()),
						nullToEmpty(u.getEmpName()),
						nullToEmpty(u.getEmpCode()),
						nullToEmpty(u.getOfficePhone()),
						u.getActiveDate() != null ? u.getActiveDate().toString() : "",
						u.getDisableDate() != null ? u.getDisableDate().toString() : "",
						u.getEnabled() != null ? (u.getEnabled() == 1 ? "启用" : "停用") : "",
						nullToEmpty(u.getUserDesc())));
				writer.write("\n");
			}
			writer.flush();
		}
	}

	private String nullToEmpty(String s) {
		return s == null ? "" : s;
	}
}