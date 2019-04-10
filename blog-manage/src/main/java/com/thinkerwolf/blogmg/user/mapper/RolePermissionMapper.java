package com.thinkerwolf.blogmg.user.mapper;

import com.thinkerwolf.blogmg.user.model.RolePermission;
import com.thinkerwolf.blogmg.user.model.RolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	long countByExample(RolePermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	int deleteByExample(RolePermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	int insert(RolePermission record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	int insertSelective(RolePermission record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	List<RolePermission> selectByExample(RolePermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	RolePermission selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	int updateByExampleSelective(@Param("record") RolePermission record,
			@Param("example") RolePermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	int updateByPrimaryKeySelective(RolePermission record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table role_permission
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	int updateByPrimaryKey(RolePermission record);
}