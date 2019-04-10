package com.thinkerwolf.blogmg.user.model;

import java.util.Date;

public class User {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.id
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.user_name
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private String userName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.password
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.role_id
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private Integer roleId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.address
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private String address;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.phone
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private String phone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.create_time
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.update_time
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private Date updateTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.last_login_time
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private Date lastLoginTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.status
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private Integer status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.true_name
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private String trueName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_user.picture
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	private String picture;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.id
	 * @return  the value of t_user.id
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.id
	 * @param id  the value for t_user.id
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.user_name
	 * @return  the value of t_user.user_name
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.user_name
	 * @param userName  the value for t_user.user_name
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.password
	 * @return  the value of t_user.password
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.password
	 * @param password  the value for t_user.password
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.role_id
	 * @return  the value of t_user.role_id
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.role_id
	 * @param roleId  the value for t_user.role_id
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.address
	 * @return  the value of t_user.address
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.address
	 * @param address  the value for t_user.address
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.phone
	 * @return  the value of t_user.phone
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.phone
	 * @param phone  the value for t_user.phone
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.create_time
	 * @return  the value of t_user.create_time
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.create_time
	 * @param createTime  the value for t_user.create_time
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.update_time
	 * @return  the value of t_user.update_time
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.update_time
	 * @param updateTime  the value for t_user.update_time
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.last_login_time
	 * @return  the value of t_user.last_login_time
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.last_login_time
	 * @param lastLoginTime  the value for t_user.last_login_time
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.status
	 * @return  the value of t_user.status
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.status
	 * @param status  the value for t_user.status
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.true_name
	 * @return  the value of t_user.true_name
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.true_name
	 * @param trueName  the value for t_user.true_name
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_user.picture
	 * @return  the value of t_user.picture
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_user.picture
	 * @param picture  the value for t_user.picture
	 * @mbg.generated  Mon Apr 08 17:41:02 CST 2019
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
}