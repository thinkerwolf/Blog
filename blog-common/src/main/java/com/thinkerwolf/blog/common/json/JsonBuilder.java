package com.thinkerwolf.blog.common.json;

import java.util.HashMap;
import java.util.Map;

public class JsonBuilder {

	public static final String DATA = "data";
	
	public static final String STATE = "state";
	
	public static final Map<String, Object> DEFAULT_SUCJSON;
	
	static {
		DEFAULT_SUCJSON = new HashMap<>();
		getJson(State.SUCCESS, "");
	}
	
	public static Map<String, Object> getSucJson() {
		return DEFAULT_SUCJSON;
	}
	
	/**
	 * 获取成功的json
	 * 
	 * @param result
	 * @return
	 */
	public static Map<String, Object> getSucJson(Object result) {
		return getJson(State.SUCCESS, result);
	}

	/**
	 * 获取失败的json
	 * 
	 * @param result
	 * @return
	 */
	public static Map<String, Object> getFailJson(String msg) {
		return getJson(State.FAIL, msg);
	}

	public static Map<String, Object> getJson(State state, Object result) {
		Map<String, Object> map = new HashMap<>();
		map.put(STATE, state.getKey());
		map.put(DATA, result);
		return map;
	}

}
