package com.jiangjie.ohs.utils;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.jiangjie.ohs.exception.OhsException;

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
	
	/**
	 * 如果是空，抛对应的错
	 * @param data
	 * @param key
	 * @param errMsg
	 * @throws OhsException
	 */
	public static final void throwIfEmpty(Map<String, Object> data, String key, String errMsg) throws OhsException {
		if (StringUtils.isEmpty(data.get(key))) {
			throw new OhsException(errMsg);
		}
	}
}
