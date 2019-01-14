package com.jiangjie.ohs.utils;

import org.springframework.util.StringUtils;

/**
 * 工具类
 * @author Administrator
 *
 */
public class OhsUtils {

	/**
	 * 如果是空格，返回null
	 * @param value
	 * @return
	 */
	public static final String putIfNotBlank(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		} 
		return value;
	}
}
