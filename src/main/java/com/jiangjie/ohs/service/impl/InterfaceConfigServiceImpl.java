package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jiangjie.ohs.dto.Interface;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.autoTest.OhsInterfaceConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsInterfaceConfigRepository;
import com.jiangjie.ohs.repository.OhsModuleConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.service.InterfaceConfigService;

@Service
public class InterfaceConfigServiceImpl implements InterfaceConfigService {

	@Autowired
	private OhsInterfaceConfigRepository ohsInterfaceConfigRepository;

	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;

	@Autowired
	private OhsModuleConfigRepository ohsModuleConfigRepository;

	@Override
	public PageResponse<Interface> getAllInterface(Interface interfaceObj) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(interfaceObj.getSysAlias()) ? null : interfaceObj.getSysAlias());
		ohsSysConfig.setSysChineseNme(
				StringUtils.isEmpty(interfaceObj.getSysChineseNme()) ? null : interfaceObj.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("当前系统不存在对应系统配置信息，请先添加系统配置信息后再查询或新增修改模块！");
		}

		// 只允许系统中存在唯一系统码和唯一系统名的系统
		ohsSysConfig = ohsSysConfigLst.get(0);

		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(
				StringUtils.isEmpty(interfaceObj.getModuleAlias()) ? null : interfaceObj.getModuleAlias());
		ohsModuleConfig
				.setModuleName(StringUtils.isEmpty(interfaceObj.getModuleName()) ? null : interfaceObj.getModuleName());
		// 如果送了系统码或者系统名，表示不是查询全部的模块
		if (!StringUtils.isEmpty(interfaceObj.getSysAlias()) || !StringUtils.isEmpty(interfaceObj.getSysChineseNme())) {
			ohsModuleConfig.setSysId(ohsSysConfig.getId());
		}

		List<OhsModuleConfig> ohsModuleConfigList = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigList)) {
			throw new OhsException("当前系统不存在对应模块配置信息，请自行添加模块！");
		}

		ohsModuleConfig = ohsModuleConfigList.get(0);

		Pageable pageable = PageRequest.of(interfaceObj.getCurrent() - 1 < 0 ? 0 : interfaceObj.getCurrent() - 1,
				interfaceObj.getPageSize());

		OhsInterfaceConfig ohsInterfaceConfig = new OhsInterfaceConfig();
		ohsInterfaceConfig.setSysId(ohsSysConfig.getId());
		ohsInterfaceConfig.setModuleId(ohsModuleConfig.getId());
		if (!StringUtils.isEmpty(interfaceObj.getUrlPath())) {
			ohsInterfaceConfig.setUrlPath(interfaceObj.getUrlPath());
		}
		if (!StringUtils.isEmpty(interfaceObj.getMethod())) {
			ohsInterfaceConfig.setMethod(interfaceObj.getMethod());
		}

		Page<OhsInterfaceConfig> ohsInterfaceConfigListPage = ohsInterfaceConfigRepository
				.findAll(Example.of(ohsInterfaceConfig), pageable);
		List<OhsInterfaceConfig> ohsInterfaceConfigList = ohsInterfaceConfigListPage.getContent();
		if (CollectionUtils.isEmpty(ohsInterfaceConfigList)) {
			throw new OhsException("当前系统不存在对应接口配置信息，请自行添加接口！");
		}

		List<Interface> interfaceLst = new ArrayList<>();
		for (OhsInterfaceConfig ohsIter : ohsInterfaceConfigList) {
			Interface interfaceRetObj = new Interface();
			interfaceRetObj.setId(ohsIter.getId() + "");
			interfaceRetObj.setSysAlias(ohsSysConfig.getSysAlias());
			interfaceRetObj.setSysChineseNme(ohsSysConfig.getSysChineseNme());

			interfaceRetObj.setModuleAlias(ohsModuleConfig.getModuleAlias());
			interfaceRetObj.setModuleName(ohsModuleConfig.getModuleName());

			interfaceRetObj.setUrlPath(ohsIter.getUrlPath());
			interfaceRetObj.setMethod(ohsIter.getMethod());
			interfaceRetObj.setCreateDate(ohsIter.getCreateDate());
			interfaceRetObj.setCreateUser(ohsIter.getCreateUser());
			interfaceRetObj.setUpdateDate(ohsIter.getUpdateDate());
			interfaceRetObj.setUpdateUser(ohsIter.getUpdateUser());
			interfaceRetObj.setInterfaceType(ohsIter.getInterfaceType());
			interfaceRetObj.setInterfaceAlias(ohsIter.getInterfaceAlias());
			interfaceRetObj.setInterfaceName(ohsIter.getInterfaceName());
			interfaceRetObj.setRequestTemplate(ohsIter.getRequestTemplate());
			interfaceRetObj.setResponseTemplate(ohsIter.getResponseTemplate());
			
			interfaceLst.add(interfaceRetObj);
		}

		PageResponse<Interface> modulePageRsp = new PageResponse<Interface>(interfaceLst,
				ohsInterfaceConfigListPage.getNumber(), ohsInterfaceConfigListPage.getSize(),
				ohsInterfaceConfigListPage.getTotalElements(), ohsInterfaceConfigListPage.getTotalPages());
		return modulePageRsp;
	}

	@Override
	public Interface saveInterfaceConfig(Interface interfaceObj) throws OhsException {

		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(interfaceObj.getSysAlias()) ? null : interfaceObj.getSysAlias());
		ohsSysConfig.setSysChineseNme(
				StringUtils.isEmpty(interfaceObj.getSysChineseNme()) ? null : interfaceObj.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("当前系统不存在对应系统配置信息，请先添加系统配置信息后再查询或新增修改模块！");
		}

		// 只允许系统中存在唯一系统码和唯一系统名的系统
		ohsSysConfig = ohsSysConfigLst.get(0);

		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(
				StringUtils.isEmpty(interfaceObj.getModuleAlias()) ? null : interfaceObj.getModuleAlias());
		ohsModuleConfig
				.setModuleName(StringUtils.isEmpty(interfaceObj.getModuleName()) ? null : interfaceObj.getModuleName());
		// 如果送了系统码或者系统名，表示不是查询全部的模块
		if (!StringUtils.isEmpty(interfaceObj.getSysAlias()) || !StringUtils.isEmpty(interfaceObj.getSysChineseNme())) {
			ohsModuleConfig.setSysId(ohsSysConfig.getId());
		}

		List<OhsModuleConfig> ohsModuleConfigList = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigList)) {
			throw new OhsException("当前系统不存在对应模块配置信息，请自行添加模块！");
		}

		ohsModuleConfig = ohsModuleConfigList.get(0);
		
		OhsInterfaceConfig ohsInterfaceConfig = new OhsInterfaceConfig();
		ohsInterfaceConfig.setSysId(ohsSysConfig.getId());
		ohsInterfaceConfig.setModuleId(ohsModuleConfig.getId());
		ohsInterfaceConfig.setUrlPath(interfaceObj.getUrlPath());
		ohsInterfaceConfig.setMethod(interfaceObj.getMethod());
		ohsInterfaceConfig.setCreateDate(new Timestamp(new Date().getTime()));
		ohsInterfaceConfig.setCreateUser("admin");
		ohsInterfaceConfig.setInterfaceAlias(interfaceObj.getInterfaceAlias());
		ohsInterfaceConfig.setInterfaceName(interfaceObj.getInterfaceName());
		ohsInterfaceConfig.setInterfaceType(interfaceObj.getInterfaceType());
		ohsInterfaceConfig.setRequestTemplate(interfaceObj.getRequestTemplate());
		ohsInterfaceConfig.setResponseTemplate(interfaceObj.getResponseTemplate());
		
		ohsInterfaceConfig = ohsInterfaceConfigRepository.save(ohsInterfaceConfig);
		
		interfaceObj.setId("" + ohsInterfaceConfig.getId());
		interfaceObj.setCreateUser(ohsInterfaceConfig.getCreateUser());
		interfaceObj.setCreateDate(ohsInterfaceConfig.getCreateDate());
		return interfaceObj;
	}

	@Override
	public Interface deleteById(int id) throws OhsException {
		Optional<OhsInterfaceConfig> ohsInterfaceConfigOpt = ohsInterfaceConfigRepository.findById(id);
		if (!ohsInterfaceConfigOpt.isPresent()) {
			throw new OhsException("该接口信息已被删除！请重新查询！");
		}
		ohsInterfaceConfigRepository.deleteById(id);
		Interface interfaceObj = new Interface();
		interfaceObj.setId(id+ "");
		return interfaceObj;
	}

	@Override
	public Interface updateById(Interface interfaceObj) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(interfaceObj.getSysAlias()) ? null : interfaceObj.getSysAlias());
		ohsSysConfig.setSysChineseNme(
				StringUtils.isEmpty(interfaceObj.getSysChineseNme()) ? null : interfaceObj.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("当前系统不存在对应系统配置信息，请先添加系统配置信息后再查询或新增修改模块！");
		}

		// 只允许系统中存在唯一系统码和唯一系统名的系统
		ohsSysConfig = ohsSysConfigLst.get(0);

		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(
				StringUtils.isEmpty(interfaceObj.getModuleAlias()) ? null : interfaceObj.getModuleAlias());
		ohsModuleConfig
				.setModuleName(StringUtils.isEmpty(interfaceObj.getModuleName()) ? null : interfaceObj.getModuleName());
		// 如果送了系统码或者系统名，表示不是查询全部的模块
		if (!StringUtils.isEmpty(interfaceObj.getSysAlias()) || !StringUtils.isEmpty(interfaceObj.getSysChineseNme())) {
			ohsModuleConfig.setSysId(ohsSysConfig.getId());
		}

		List<OhsModuleConfig> ohsModuleConfigList = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigList)) {
			throw new OhsException("当前系统不存在对应模块配置信息，请自行添加模块！");
		}

		ohsModuleConfig = ohsModuleConfigList.get(0);
		
		Optional<OhsInterfaceConfig> ohsInterfaceConfigOpt = ohsInterfaceConfigRepository.findById(Integer.parseInt(interfaceObj.getId()));
		if (!ohsInterfaceConfigOpt.isPresent()) {
			throw new OhsException("该接口信息不存在！");
		}
		
		OhsInterfaceConfig ohsInterfaceConfig = new OhsInterfaceConfig();
		ohsInterfaceConfig.setId(Integer.parseInt(interfaceObj.getId()));
		ohsInterfaceConfig.setSysId(ohsSysConfig.getId());
		ohsInterfaceConfig.setModuleId(ohsModuleConfig.getId());
		ohsInterfaceConfig.setUrlPath(interfaceObj.getUrlPath());
		ohsInterfaceConfig.setMethod(interfaceObj.getMethod());
		ohsInterfaceConfig.setCreateDate(ohsInterfaceConfigOpt.get().getCreateDate());
		ohsInterfaceConfig.setCreateUser(ohsInterfaceConfigOpt.get().getCreateUser());
		ohsInterfaceConfig.setUpdateDate(new Timestamp(new Date().getTime()));
		ohsInterfaceConfig.setUpdateUser("admin");
		ohsInterfaceConfig.setInterfaceType(interfaceObj.getInterfaceType());
		ohsInterfaceConfig.setInterfaceAlias(interfaceObj.getInterfaceAlias());
		ohsInterfaceConfig.setInterfaceName(interfaceObj.getInterfaceName());
		ohsInterfaceConfig.setRequestTemplate(interfaceObj.getRequestTemplate());
		ohsInterfaceConfig.setResponseTemplate(interfaceObj.getResponseTemplate());
		
		
		ohsInterfaceConfig = ohsInterfaceConfigRepository.save(ohsInterfaceConfig);
		
		interfaceObj.setId("" + ohsInterfaceConfig.getId());
		interfaceObj.setUpdateDate(new Timestamp(new Date().getTime()));
		interfaceObj.setUpdateUser("admin");
		interfaceObj.setCreateUser(ohsInterfaceConfig.getCreateUser());
		interfaceObj.setCreateDate(ohsInterfaceConfig.getCreateDate());
		return interfaceObj;
	}
}
