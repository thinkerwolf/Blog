package com.thinkerwolf.blogmg.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.thinkerwolf.blog.common.log.InternalLoggerFactory;
import com.thinkerwolf.blog.common.log.Logger;
import com.thinkerwolf.blogmg.user.mapper.PermissionMapper;
import com.thinkerwolf.blogmg.user.mapper.UserMapper;
import com.thinkerwolf.blogmg.user.model.Permission;
import com.thinkerwolf.blogmg.user.model.User;

/**
 * UserDetail 实现自定义的 用户权限查询
 * 
 * @author Bruce Wu
 *
 */
public class AcegiUserDetailServiceImpl implements UserDetailsService {

	private static final Logger logger = InternalLoggerFactory.getLogger(AcegiUserDetailServiceImpl.class);

	public static final int ACCOUNT_STOP = 0;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Get UserDetails start ...");
		}
		Map<String, String> queryParameters = new HashMap<String, String>();
		queryParameters.put("username", username);
		User user = userMapper.selectByPrimaryKey(username);
		if (user == null) {
			throw new AuthenticationServiceException("暂无此用户");
		}
		if (user.getStatus() == ACCOUNT_STOP) {
			throw new AuthenticationServiceException("此用户已停用");
		}
		List<Permission> permissios = permissionMapper.selectUserPermissions(username);

		// List<Permission> permissios =
		// session.selectList("com.blog.login.getUserPermission",
		// queryParameters);
		if (permissios == null || permissios.size() <= 0) {
			throw new AuthenticationServiceException("此用户暂无权限");
		}
		// session.close();

		GrantedAuthority[] gas = new GrantedAuthorityImpl[permissios.size()];
		for (int i = 0; i < permissios.size(); i++) {
			gas[i] = new GrantedAuthorityImpl(permissios.get(i).getName());
		}
		org.acegisecurity.userdetails.User userDetails = new org.acegisecurity.userdetails.User(user.getUserName(),
				user.getPassword(), true, true, true, true, gas);
		return userDetails;
	}

}
