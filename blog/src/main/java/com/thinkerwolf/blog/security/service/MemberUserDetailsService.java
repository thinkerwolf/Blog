package com.thinkerwolf.blog.security.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import com.thinkerwolf.blog.common.generator.IdGeneratorManager;
import com.thinkerwolf.blog.member.mapper.MemberMapper;
import com.thinkerwolf.blog.member.model.Member;
import com.thinkerwolf.blog.member.model.MemberCondition;

/**
 * 自定义UserDetailsManager
 * 
 * @author wukai
 *
 */
public class MemberUserDetailsService implements UserDetailsManager {

	@Autowired
	MemberMapper memberMapper;

	private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isBlank(username)) {
			throw new UsernameNotFoundException("Username is empty");
		}
		// TODO 验证帐号是否过期，帐号是否被锁，授权是否过期
		Member member = getMember(username);
		User user = new User(username, passwordEncoder.encode(member.getPassword()), Collections.emptyList());
		return user;
	}

	@Override
	public void createUser(UserDetails user) {
		if (!userExists(user.getUsername())) {
			Member member = new Member();
			member.setId(IdGeneratorManager.generateStringId());
			member.setUsername(user.getUsername());
			member.setPassword(user.getPassword());
			member.setCreateTime(new Date());
			member.setHeadPic("default.jpg");
			memberMapper.insertSelective(member);
		}
	}

	@Override
	public void updateUser(UserDetails user) {

	}

	@Override
	public void deleteUser(String username) {

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(String username) {
		MemberCondition codi = new MemberCondition();
		codi.or().andUsernameEqualTo(username);
		List<Member> members = memberMapper.selectByCondition(codi);
		return members.size() > 0;
	}

	private Member getMember(String username) {
		MemberCondition codi = new MemberCondition();
		codi.or().andUsernameEqualTo(username);
		List<Member> members = memberMapper.selectByCondition(codi);
		if (members.size() == 0) {
			throw new UsernameNotFoundException("User:" + username + " not found");
		}
		if (members.size() > 1) {
			throw new UsernameNotFoundException("Multi users with the same name");
		}
		// TODO 验证帐号是否过期，帐号是否被锁，授权是否过期
		Member member = members.get(0);
		return member;
	}

}
