package com.cheny.controller;

import java.util.List;

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
	public ModelAndView selectUser(@RequestParam("id") Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		User user = userService.selectUser(id);
		mv.addObject("user", user);
		mv.setViewName("user");
		return mv;
	}
	
	@RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
	@ResponseBody
	public Msg findUserByPage(@RequestParam(value = "pageindex", defaultValue = "1") Integer pageindex, @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
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
	
	@RequestMapping(value = {"/delUser/{id}"}, method = {RequestMethod.POST})
	@ResponseBody
	public Msg deleteUser(@PathVariable("id") Long id) throws Exception {
		int rows = userService.removeUser(id);
		return Msg.success().add("rows", rows);
	}
}
