package com.blog.service.sysmgmt;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.modal.MemMember;
import com.blog.utils.StringUtils;

@Service
@Transactional
public class MemMemberService {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	/**
	 * 前台用户登陆
	 * @param username
	 * @param password
	 * @return
	 */
	public Map<String, Object> doFrontLogin(String username, String password){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		SqlSession session = sqlSessionFactory.openSession();
		
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		
		if( StringUtils.isMail(username)) { //邮箱
			queryParameters.put("mail", username);
		} else if( StringUtils.isPhone(username)) { //手机
			queryParameters.put("phone", username);
		} else {
			//非手机或邮箱
			result.put("result", false);
			result.put("message", "非手机或邮箱，请重新输入");
			return result;
			
		}
		MemMember member = session.selectOne("com.blog.mem.getMemMember", queryParameters);
		
		if(member == null) {
			//无此用户
			result.put("result", false);
			result.put("message", "无此用户，请重新确认输入");
			
		} else {
			//存在用户
			if(member.getPassword().equals(password)) {
				//密码正确
				result.put("result", true);
				result.put("message", "登陆成功");
				result.put("memMember", member);
			} else {
				//密码不正确
				result.put("result", true);
				result.put("message", "密码错误，请重新输入");
			}
		}
		return result;
		
	}
	
	
}
