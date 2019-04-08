package com.blog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author wukai
 *
 */
public class StringUtils {

	/**
	 * 判断字符串是否为邮箱
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMail(String str) {
		str = str.trim();
		String regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
		return match(str, regex);
	}

	/**
	 * 判断字符串是否为手机号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		str = str.trim();
		String regex = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
		return match(str, regex);
	}

	public static boolean match(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();

	}

}
