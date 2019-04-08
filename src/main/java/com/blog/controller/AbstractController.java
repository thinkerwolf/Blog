package com.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractController {
	
	protected String SUCCESS = "success";
	
	protected String FAILURE = "failure";
	
	protected String RESULT = "result";
	
	protected String DATA = "data";
	
	protected String MESSAGE = "msg";
	
	/**
	 * 请求成功
	 * @param list :list数据
	 * @return
	 */
	public <T> Map<String, Object> renderSuccess(List<T> listData) {
		Map<String, Object> uiObject = new HashMap<String, Object>();
		uiObject.put(RESULT, SUCCESS);
		uiObject.put(DATA, listData);
		return uiObject;
	}
	
	/**
	 * 请求成功
	 * @param list :list数据
	 * @return
	 */
	public <T> Map<String, Object> renderSuccess(Object listData) {
		Map<String, Object> uiObject = new HashMap<String, Object>();
		uiObject.put(RESULT, SUCCESS);
		uiObject.put(DATA, listData);
		return uiObject;
	}
	
	/**
	 * 请求成功
	 * @param mapData : Map数据
	 * @return
	 */
	public <T> Map<String, Object> renderSuccess(Map<String, Object> mapData) {
		Map<String, Object> uiObject = new HashMap<String, Object>();
		uiObject.put(RESULT, SUCCESS);
		uiObject.put(DATA, mapData);
		return uiObject;
	}
	
	/**
	 * 请求失败
	 * @param message : 失败信息
	 * @return
	 */
	public <T> Map<String, Object> renderFail(String message) {
		Map<String, Object> uiObject = new HashMap<String, Object>();
		uiObject.put(RESULT, FAILURE);
		uiObject.put(MESSAGE, message);
		return uiObject;
	}
	
}
