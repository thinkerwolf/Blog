package com.thinkerwolf.blog.common.security.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public interface PermissionService extends InitializingBean {
	
	List<String> getAuthorityFromCache(String s);
	
	@Override
	default void afterPropertiesSet() throws Exception {

	}
}
