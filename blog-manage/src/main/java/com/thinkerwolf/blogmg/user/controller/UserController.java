package com.thinkerwolf.blogmg.user.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkerwolf.blog.common.json.JsonBuilder;
import com.thinkerwolf.blogmg.user.model.User;
import com.thinkerwolf.blogmg.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/findUser.json")
	@ResponseBody
	public Map<String, Object> findUsers(HttpSession httpSession, String username) {
		if (!StringUtils.hasLength(username)) {
			username = httpSession.getAttribute("j_username") + "";
		}
		User user = userService.findUser(username);
		if (user == null) {
			return JsonBuilder.getFailJson("未发现用户");
		} else {
			return JsonBuilder.getSucJson(user);
		}
	}
	
}
