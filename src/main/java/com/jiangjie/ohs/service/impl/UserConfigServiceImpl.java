package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.User;
import com.jiangjie.ohs.entity.OhsUserConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsUserConfigRepository;
import com.jiangjie.ohs.service.UserConfigService;

@Service
public class UserConfigServiceImpl implements UserConfigService {

	@Autowired
	private OhsUserConfigRepository ohsUserConfigRepository;

	@Override
	public PageResponse<User> getAllUser(User user) throws OhsException {
		OhsUserConfig ohsUserConfig = new OhsUserConfig();
		ohsUserConfig.setName(StringUtils.isEmpty(user.getName()) ? null : user.getName());
		ohsUserConfig.setLoginAccount(StringUtils.isEmpty(user.getLoginAccount()) ? null : user.getLoginAccount());
		ohsUserConfig.setRole(StringUtils.isEmpty(user.getRole()) ? null : user.getRole());
		
		Pageable pageable = PageRequest.of(user.getCurrent() - 1 < 0 ? 0 : user.getCurrent() - 1, user.getPageSize());
		
		Page<OhsUserConfig> ohsUserConfigPage = ohsUserConfigRepository.findAll(Example.of(ohsUserConfig), pageable);
		List<OhsUserConfig> ohsUserConfigLst = ohsUserConfigPage.getContent();
		if (CollectionUtils.isEmpty(ohsUserConfigLst)) {
			throw new OhsException("不存在符合对应查询条件的人员信息！");
		}
		
		Function<OhsUserConfig, User> toUserFunc = ohsUser -> {
			User newUser = new User();
			newUser.setId(ohsUser.getId());
			newUser.setLoginAccount(ohsUser.getLoginAccount());
			newUser.setName(ohsUser.getName());
			newUser.setPassword(ohsUser.getPassword());
			newUser.setRole(ohsUser.getRole());
			newUser.setUpdateDate(ohsUser.getUpdateDate());
			newUser.setUpdateUser(ohsUser.getUpdateUser());
			newUser.setCreateDate(ohsUser.getCreateDate());
			newUser.setCreateUser(ohsUser.getCreateUser());
			return newUser;
		};
		
		List<User> userLst = new ArrayList<>();
		
		ohsUserConfigLst.stream().forEach(ohsUsr -> {
			User transUser = toUserFunc.apply(ohsUsr);
			userLst.add(transUser);
		});
		
		PageResponse<User> userRsp = new PageResponse<>(userLst, ohsUserConfigPage.getNumber(), ohsUserConfigPage.getSize(),
				ohsUserConfigPage.getTotalElements(), ohsUserConfigPage.getTotalPages());
		
		return userRsp;
	}

	@Override
	public User saveUserConfig(User user) {
		
		Function<User, OhsUserConfig> toOhsUserConfig = usr -> {
			OhsUserConfig newUser = new OhsUserConfig();
			newUser.setLoginAccount(usr.getLoginAccount());
			newUser.setName(usr.getName());
			newUser.setPassword(usr.getPassword());
			newUser.setRole(usr.getRole());
			newUser.setIpAddr(user.getIpAddr());
			newUser.setCreateDate(new Timestamp(new Date().getTime()));
			newUser.setCreateUser(user.getTokenName());
			return newUser;
		};
		
		OhsUserConfig ohsUserConfig = toOhsUserConfig.apply(user);
		
		if ("Y".equals(user.getIsFirstLogin())) {
			OhsUserConfig queryObj = new OhsUserConfig();
			queryObj.setName(user.getName());
			Optional<OhsUserConfig> ohsUserConfigOpt = ohsUserConfigRepository.findOne(Example.of(queryObj));
			if (ohsUserConfigOpt.isPresent()) {
				ohsUserConfig.setId(ohsUserConfigOpt.get().getId());
				ohsUserConfig.setUpdateDate(new Timestamp(new Date().getTime()));
				ohsUserConfig.setUpdateUser(user.getIpAddr());
			}
		}
		ohsUserConfig = ohsUserConfigRepository.save(ohsUserConfig);
		
		user.setId(ohsUserConfig.getId());
		user.setCreateDate(ohsUserConfig.getCreateDate());
		user.setCreateUser(ohsUserConfig.getCreateUser());
		return user;
	}

	@Override
	public User deleteById(int id, String tokenName) throws OhsException {
		Optional<OhsUserConfig> ohsUserConfigOpt = ohsUserConfigRepository.findById(id);
		if (!ohsUserConfigOpt.isPresent()) {
			throw new OhsException("该人员已被删除！");
		}
		if (!ohsUserConfigOpt.get().getCreateUser().equals(tokenName)) {
			throw new OhsException("禁止删除非当前用户数据！");
		}
		ohsUserConfigRepository.deleteById(id);
		User user = new User();
		user.setId(id);
		return user;
	}

	@Override
	public User updateById(User user) {
		Function<User, OhsUserConfig> toOhsUserConfig = usr -> {
			OhsUserConfig newUser = new OhsUserConfig();
			newUser.setId(usr.getId());
			newUser.setLoginAccount(usr.getLoginAccount());
			newUser.setName(usr.getName());
			newUser.setPassword(usr.getPassword());
			newUser.setRole(usr.getRole());
			newUser.setUpdateDate(new Timestamp(new Date().getTime()));
			newUser.setUpdateUser(user.getTokenName());
			return newUser;
		};
		
		OhsUserConfig ohsUserConfig = toOhsUserConfig.apply(user);
		ohsUserConfig = ohsUserConfigRepository.save(ohsUserConfig);
		user.setCreateDate(ohsUserConfig.getCreateDate());
		user.setCreateUser(ohsUserConfig.getCreateUser());
		return user;
	}

	@Override
	public List<User> getUserByIPAddr(String ip) {
		OhsUserConfig ohsUserConfig = new OhsUserConfig();
		ohsUserConfig.setIpAddr(ip);
		List<OhsUserConfig> ohsUserConfigList = ohsUserConfigRepository.findAll(Example.of(ohsUserConfig));
		List<User> userList = new ArrayList<User>();
		if (!CollectionUtils.isEmpty(ohsUserConfigList)) {
			ohsUserConfigList.stream().forEach(usercfg -> {
				User user = new User();
				user.setName(usercfg.getName());
				userList.add(user);
			});
		}
		return userList;
	}

}
