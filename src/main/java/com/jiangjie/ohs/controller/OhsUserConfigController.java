package com.jiangjie.ohs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.User;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.UserConfigService;

@RestController
@RequestMapping("/api/userConfig")
public class OhsUserConfigController {

	
	@Autowired
	private UserConfigService userConfigService;
	
	@GetMapping("/getUser")
	public User getUser(HttpServletRequest request) {
		return userConfigService.getUserByIPAddr(request.getRemoteAddr());
	}
	
	/**
	 * 根据指定的条件查询所有的用户
	 * @param User
	 * @return
	 * @throws OhsException
	 */
	@GetMapping("/getAllUser")
	public PageResponse<User> getAllUser(User User) throws OhsException {
		return userConfigService.getAllUser(User);
	}
	
	@PostMapping("/saveUserConfig")
	@ResponseBody
	public User saveUserConfig(@RequestBody User user, HttpServletRequest request) throws OhsException {
		if ("Y".equals(user.getIsFirstLogin())) {
			user.setIpAddr(request.getRemoteAddr());
		}
		return userConfigService.saveUserConfig(user);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public User deleteById(@PathVariable("id") String id) throws OhsException {
		return userConfigService.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * TODO PUT传值报错！！！麻蛋
	 * @param ohsSysConfig
	 * @return 
	 * @throws OhsException 
	 */
	@GetMapping("/updateById")
	public User updateById(User user) throws OhsException {
		return userConfigService.updateById(user);
	}

}
