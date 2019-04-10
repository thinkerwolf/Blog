package com.thinkerwolf.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author Bruce Wu
 *
 */
public class TimeUtils {
	
	/**
	 * 获取系统当前时间 str
	 * @return
	 */
	public static String getCurrentDateStr(String format){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	
}
