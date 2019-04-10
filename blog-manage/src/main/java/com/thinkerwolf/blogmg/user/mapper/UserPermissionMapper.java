package com.thinkerwolf.blogmg.user.mapper;

import java.util.List;

import com.thinkerwolf.blogmg.user.model.Permission;

public interface UserPermissionMapper {

	List<Permission> getUserPermissions(String userId);

}
