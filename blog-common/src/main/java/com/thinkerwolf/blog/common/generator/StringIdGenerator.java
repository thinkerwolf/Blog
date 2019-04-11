package com.thinkerwolf.blog.common.generator;

public abstract class StringIdGenerator implements IdGenerator<String> {

	@Override
	public Class<String> idType() {
		return String.class;
	}

}
