package com.blog.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.acegisecurity.ConfigAttributeDefinition;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.blog.security.modal.TPermission;

/**
 * 根据url获取用户许可
 * 
 * @author Bruce Wu
 * 
 */
public class TPermissionService {

	private SqlSessionFactory sqlSessionFactory;
	
	private List<TPermission> permissionCaches;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	/**
	 * 获取所有的Permission并放入缓存中
	 */
	public void initAllPermission() {
		synchronized(this){
			if(permissionCaches == null) {
				permissionCaches = new Vector<TPermission>();
				SqlSession session = sqlSessionFactory.openSession();
				permissionCaches = session.selectList("com.blog.login.getAllPermission");
			}
		}
	}

	public List<TPermission> findAllPermissions() {
		if(permissionCaches == null) {
			initAllPermission();
		}
		return permissionCaches;
	}
	
	/**
	 * 从缓存中获取所有的权限
	 * @param resString
	 * @return
	 */
	public List<String> getAuthorityFromCache(String resString) {
		List<String> authorities = new ArrayList<String>();
		for(TPermission permission : permissionCaches) {
			if(permission.getRules().equals(resString)) {
				authorities.add(permission.getPermissionname());
			}
		}
		return authorities;
	}

}
