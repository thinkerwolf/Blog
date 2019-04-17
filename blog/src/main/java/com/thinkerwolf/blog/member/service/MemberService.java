package com.thinkerwolf.blog.member.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thinkerwolf.blog.common.json.JsonBuilder;
import com.thinkerwolf.blog.member.mapper.MemberMapper;
import com.thinkerwolf.blog.member.model.Member;
import com.thinkerwolf.blog.member.model.MemberCondition;
import com.thinkerwolf.blog.security.MemberUser;

@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;

	private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	public Map<String, Object> register(String username, String password, String confirmpassword) {
		// 校验
		Map<String, Object> errorCode = new HashMap<>();
		if (StringUtils.isBlank(username)) {
			errorCode.put("username", "请输入用户名");
			return JsonBuilder.getFailJson(errorCode);
		}

		if (StringUtils.isBlank(password)) {
			errorCode.put("password", "请输入密码");
			return JsonBuilder.getFailJson(errorCode);
		}

		if (StringUtils.isBlank(confirmpassword)) {
			errorCode.put("confirmpassword", "请输入确认密码");
			return JsonBuilder.getFailJson(errorCode);
		}

		if (!StringUtils.equals(password, confirmpassword)) {
			errorCode.put("passwordconfirmpassword", "密码和确认密码不相同");
			return JsonBuilder.getFailJson(errorCode);
		}

		MemberCondition codi = new MemberCondition();
		codi.or().andUsernameEqualTo(username);
		List<Member> members = memberMapper.selectByCondition(codi);
		if (members.size() > 0) {
			errorCode.put("usernameregisted", "此用户名已被注册");
			return JsonBuilder.getFailJson(errorCode);
		}
		String encodedPw = passwordEncoder.encode(password);
		Member member = new Member();
		member.setCreateTime(new Date());
		member.setUsername(username);
		member.setPassword(encodedPw);
		member.setHeadPic("user");
		member.setId(username);
		memberMapper.insertSelective(member);
		return JsonBuilder.getSucJson();
	}

	public Map<String, Object> login(String username, String password) {
		// 校验
		if (StringUtils.isBlank(username)) {
			return JsonBuilder.getFailJson("用户名不能为空");
		}

		if (StringUtils.isBlank(password)) {
			return JsonBuilder.getFailJson("密码不能为空");
		}
		MemberCondition codi = new MemberCondition();
		codi.or().andUsernameEqualTo(username);
		List<Member> members = memberMapper.selectByCondition(codi);
		if (members.size() <= 0) {
			return JsonBuilder.getFailJson("此用户不存在");
		}
		return JsonBuilder.getSucJson(members.get(0));
	}

	public Map<String, Object> currentMember() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || auth.getPrincipal() == null
				|| !UserDetails.class.isAssignableFrom(auth.getPrincipal().getClass())) {
			return JsonBuilder.getFailJson("No user");
		}
		if (auth instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken upauth = (UsernamePasswordAuthenticationToken) auth;
			MemberUser memberUser = (MemberUser) upauth.getPrincipal();
			return JsonBuilder.getSucJson(memberUser.getMember());
		}
		return JsonBuilder.getFailJson("No user");
	}

}
