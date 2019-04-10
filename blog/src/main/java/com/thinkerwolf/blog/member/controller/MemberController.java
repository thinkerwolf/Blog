package com.thinkerwolf.blog.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkerwolf.blog.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/register")
	@ResponseBody
	public Map<String, Object> register(String username, String password) {
		return memberService.register(username, password);
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(String username, String password) {
		return memberService.login(username, password);
	}
	
}
