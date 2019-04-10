package com.thinkerwolf.blog.common.json;

public enum State {
	SUCCESS("success"), 
	FAIL("failure"),

	;

	private String key;

	private State(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
