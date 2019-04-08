package com.blog.framework;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Bruce Wu
 *
 */
public interface BaseDao {

	<T> List<T> queryForList(String statment, Class<T> clazz);

	<T> List<T> queryForList(String statment, Object parameter, Class<T> clazz);

	<T> T queryForUnique(String statment, Class<T> clazz);

	<T> T queryForUnique(String statment, Object parameter, Class<T> clazz);

	<K, V> Map<K, V> queryForMap(String statment);

	<K, V> Map<K, V> queryForMap(String statement, String mapKey);

	<K, V> Map<K, V> queryForMap(String statement, Object parameter, String mapKey);

	int insert(String statement, Object parameter);

	int update(String statement);

	int update(String statement, Object parameter);

	int delete(String statement);

	int delete(String statement, Object parameter);
}
