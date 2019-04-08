package com.blog.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.blog.security.modal.TPermission;
import com.blog.security.modal.TUser;
/**
 * UserDetail
 * 实现自定义的 用户权限查询
 * @author Bruce Wu
 *
 */
public class AcegiUserDetailServiceImpl implements UserDetailsService {
	
	public static final int ACCOUNT_STOP = 0 ;
	
	private SqlSessionFactory sqlSessionFactory;
	
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	/**
	 * 打印
	 */
	private Logger logger = Logger.getLogger(AcegiUserDetailServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		if(logger.isDebugEnabled()) {
			logger.debug("Get UserDetails start ...");
		}
		Map<String, String> queryParameters = new HashMap<String, String>();
		queryParameters.put("username", username);
		SqlSession session = sqlSessionFactory.openSession();
		TUser user = session.selectOne("com.blog.login.getUser", queryParameters);
		if(user == null) {
			throw new AuthenticationServiceException("暂无此用户");
		}
		if(user.getStatus() == ACCOUNT_STOP) {
			throw new AuthenticationServiceException("此用户已停用");
		}
	    List<TPermission> tList = session.selectList("com.blog.login.getUserPermission", queryParameters);
		if(tList == null || tList.size() <= 0){
			throw new AuthenticationServiceException("此用户暂无权限");
		}
		GrantedAuthority[] gas = new GrantedAuthorityImpl[tList.size()];
	    for(int i = 0; i < tList.size(); i ++) {
	    	gas[i] = new GrantedAuthorityImpl(tList.get(i).getPermissionname());
	    }
	    user.setAuthorities(gas);
	    user.setAccountNonExpired(true);
	    user.setAccountNonLocked(true);
	    user.setCredentialsNonExpired(true);
	    user.setEnable(true);
	    session.close();
	    //user.setPassword();
		return user;
	}

}
