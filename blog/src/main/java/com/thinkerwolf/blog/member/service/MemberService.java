package com.thinkerwolf.blog.member.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkerwolf.blog.common.json.JsonBuilder;
import com.thinkerwolf.blog.member.mapper.MemberMapper;
import com.thinkerwolf.blog.member.model.Member;
import com.thinkerwolf.blog.member.model.MemberCondition;

@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;

	public Map<String, Object> register(String username, String password) {
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
		if (members.size() > 0) {
			return JsonBuilder.getFailJson("此用户名已被注册");
		}
		
		Member member = new Member();
		member.setCreateTime(new Date());
		member.setUsername(username);
		member.setPassword(password);
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
	
}
