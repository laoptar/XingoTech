package com.cheny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cheny.entity.User;
import com.cheny.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/select")
	public ModelAndView selectUser() throws Exception {
		ModelAndView mv = new ModelAndView();
		User user = userService.selectUser(1);
		mv.addObject("user", user);
		mv.setViewName("user");
		return mv;
	}
}
