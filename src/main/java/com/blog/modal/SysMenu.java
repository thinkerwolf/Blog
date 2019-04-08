package com.blog.modal;

import java.util.Date;
import java.util.List;

public class SysMenu extends AbstractModal{
	
	private String code;
	
	private String parentId;
	
	private String name;
	
	private Date createtime;
	
	private String url;
	
	private int seq;
	
	private List<SysMenu> childMenus;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SysMenu> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<SysMenu> childMenus) {
		this.childMenus = childMenus;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
	
	
}
