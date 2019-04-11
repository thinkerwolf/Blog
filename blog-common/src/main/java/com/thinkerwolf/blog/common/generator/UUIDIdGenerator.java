package com.thinkerwolf.blog.common.generator;

import java.util.UUID;

import com.thinkerwolf.blog.common.util.SLI;

/**
 * uuid 主键生成器
 * 
 * @author wukai
 *
 */
@SLI("uuid")
public class UUIDIdGenerator extends StringIdGenerator {

	@Override
	public String nextId() {
		return UUID.randomUUID().toString();
	}

}
