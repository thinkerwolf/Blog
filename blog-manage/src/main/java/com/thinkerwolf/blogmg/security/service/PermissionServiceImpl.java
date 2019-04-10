package com.thinkerwolf.blogmg.security.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.thinkerwolf.blog.common.security.service.PermissionService;
import com.thinkerwolf.blogmg.user.mapper.PermissionMapper;
import com.thinkerwolf.blogmg.user.model.Permission;

/**
 * 根据url获取用户许可
 * 
 * @author Bruce Wu
 * 
 */
public class PermissionServiceImpl implements PermissionService {

	private List<Permission> permissionCaches;

	@Autowired
	private PermissionMapper permissionMapper;
	
	private PathMatcher pathMatcher = new AntPathMatcher();
	
	private boolean isUseAntPath = true;
	
	public boolean isUseAntPath() {
		return isUseAntPath;
	}

	public void setUseAntPath(boolean isUseAntPath) {
		this.isUseAntPath = isUseAntPath;
	}
	
	/**
	 * 获取所有的Permission并放入缓存中
	 */
	public void initAllPermission() {
		synchronized (this) {
			if (permissionCaches == null) {
				permissionCaches = permissionMapper.selectByExample(null);
			}
		}
	}

	public List<Permission> findAllPermissions() {
		if (permissionCaches == null) {
			initAllPermission();
		}
		return permissionCaches;
	}

	/**
	 * 从缓存中获取所有的权限
	 * 
	 * @param resString
	 * @return
	 */
	public List<String> getAuthorityFromCache(String url) {
		List<String> authorities = null;
		List<Permission> list = findAllPermissions();
		Iterator<Permission> iterator = list.iterator();
		String resString;
		while (iterator.hasNext()) {
			resString = iterator.next().getRules();
			boolean matched = false;
			if (isUseAntPath()) {
				matched = pathMatcher.match(resString, url);
			}
			if (matched) {
				authorities = new ArrayList<String>();
				for (Permission permission : permissionCaches) {
					if (permission.getRules().equals(resString)) {
						authorities.add(permission.getName());
					}
				}
				break;
			}
		}

		return authorities;
	}

}
