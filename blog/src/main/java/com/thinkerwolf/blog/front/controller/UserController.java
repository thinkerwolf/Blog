package com.thinkerwolf.blog.front.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.thinkerwolf.blog.common.AbstractController;
import com.thinkerwolf.blog.front.modal.MemMember;
import com.thinkerwolf.blog.front.service.MemMemberService;

/**
 * 前台用户登陆，注册，管理类
 * @author wukai
 *
 */
@Controller
@RequestMapping(value="/user")
public class UserController extends AbstractController {
	
	@Autowired
	MemMemberService memMemberService;
	
	@RequestMapping(value="/init.json")
	@ResponseBody
	public Map<String, Object> pageUserInit() {
		
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		
		HttpSession httpSession = servletRequestAttributes.getRequest().getSession();
		
		Object obj =  httpSession.getAttribute("memMember");
		
		if(obj == null) {
			return this.renderFail("无用户登陆");
		} 
		
		
		return this.renderSuccess((MemMember)obj);
	}
	
	
	@RequestMapping(value="/login.json")
	@ResponseBody
	public Map<String, Object> pageUserLogin(String username, String password) {
		
		//password = Md5Crypt.md5Crypt(password.getBytes(), "$1$");
		Map<String, Object> result = memMemberService.doFrontLogin(username, password);
		
		if((boolean)result.get("result")) {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			
			HttpSession httpSession = servletRequestAttributes.getRequest().getSession();
			
			httpSession.setAttribute("memMember", result.get("memMember"));
		}
		
		int i = 1, j = 1;
		Integer i1 = 1; Integer j1 = 1;
		System.out.println(i == j);
		System.out.println(i1 == j1);
		
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
}
