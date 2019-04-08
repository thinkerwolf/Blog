package com.blog.service.usermgmt;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.security.modal.TUser;
/**
 * 用户操作Service
 * 1.用户修改后台用户信息 或者新增后台用户
 * @author Bruce Wu
 *
 */
@Service
@Transactional
public class UserMgmtService {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public List<TUser> findUser(Map<String, Object> queryParameters){
		SqlSession session = sqlSessionFactory.openSession();
		List<TUser> users  = session.selectList("com.blog.login.getUser", queryParameters);
		session.close();
		return users;
	}
	
	
	
}
