package com.thinkerwolf.blog.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.thinkerwolf.blog.member.model.Member;

public class MemberUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Member member;

	public MemberUser(Member member, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

}
