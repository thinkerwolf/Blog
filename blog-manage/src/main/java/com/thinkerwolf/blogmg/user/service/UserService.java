package com.thinkerwolf.blogmg.user.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkerwolf.blogmg.user.mapper.UserMapper;
import com.thinkerwolf.blogmg.user.model.User;

/**
 * 用户操作Service 1.用户修改后台用户信息 或者新增后台用户
 * 
 * @author Bruce Wu
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User findUser(String userId) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		return userMapper.selectByPrimaryKey(userId);
	}
	
	
}
