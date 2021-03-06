package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.User;
import com.jiangjie.ohs.exception.OhsException;

/**
 * 人员配置服务
 * 
 * @author Administrator
 *
 */
public interface UserConfigService {

	PageResponse<User> getAllUser(User user) throws OhsException;

	User saveUserConfig(User user);

	User deleteById(int parseInt, String tokenName) throws OhsException;

	User updateById(User user);
	
	List<User> getUserByIPAddr(String ip);

}
