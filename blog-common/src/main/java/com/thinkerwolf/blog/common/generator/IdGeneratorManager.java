package com.thinkerwolf.blog.common.generator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库ID生成器管理器
 * 
 * @author wukai
 *
 */
public class IdGeneratorManager {

	private static final IdGeneratorManager INSTANCE = new IdGeneratorManager();

	public static IdGeneratorManager getInstance() {
		return INSTANCE;
	}

	private IdGeneratorManager() {
	}

	private Map<Class<?>, IdGenerator<?>> class2IdGeneratorMap = new ConcurrentHashMap<>();

	public void addIdGenerator(IdGenerator<?> idGenerator) {
		class2IdGeneratorMap.put(idGenerator.idType(), idGenerator);
	}

	@SuppressWarnings("unchecked")
	public <T> IdGenerator<T> getIdGenerator(Class<T> clazz) {
		IdGenerator<T> ig = (IdGenerator<T>) class2IdGeneratorMap.get(clazz);
		if (ig == null) {
			throw new RuntimeException("Type {" + clazz + "} IdGenerator is null");
		}
		return ig;
	}

	public static String generateStringId() {
		return getInstance().getIdGenerator(String.class).nextId();
	}

}
