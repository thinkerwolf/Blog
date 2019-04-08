package com.blog.modal;

import java.util.UUID;

/**
 * 抽象modal
 * @author Bruce Wu
 *
 */
public abstract class AbstractModal {

	private String id;

	public AbstractModal() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
