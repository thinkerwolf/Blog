package com.thinkerwolf.blog.common.generator;

/**
 * ID 生成器接口
 * 
 * @author wukai
 *
 * @param <T>
 *            生成的ID的类型
 */
public interface IdGenerator<T> {

	T nextId();
	
	Class<T> idType();

}
