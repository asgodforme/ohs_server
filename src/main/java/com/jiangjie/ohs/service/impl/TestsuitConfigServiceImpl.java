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

import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.Testsuit;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.autoTest.OhsTestsuitConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsModuleConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.repository.OhsTestsuitConfigRepository;
import com.jiangjie.ohs.service.TestsuitConfigService;

@Service
public class TestsuitConfigServiceImpl implements TestsuitConfigService {
	
	@Autowired
	private OhsTestsuitConfigRepository ohsTestsuitConfigRepository;

	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;

	@Autowired
	private OhsModuleConfigRepository ohsModuleConfigRepository;

	@Override
	public PageResponse<Testsuit> getAllTestsuit(Testsuit testsuitObj) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(testsuitObj.getSysAlias()) ? null : testsuitObj.getSysAlias());
		ohsSysConfig.setSysChineseNme(
				StringUtils.isEmpty(testsuitObj.getSysChineseNme()) ? null : testsuitObj.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("当前系统不存在对应系统配置信息，请先添加系统配置信息后再查询或新增修改模块！");
		}

		// 只允许系统中存在唯一系统码和唯一系统名的系统
		ohsSysConfig = ohsSysConfigLst.get(0);

		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(
				StringUtils.isEmpty(testsuitObj.getModuleAlias()) ? null : testsuitObj.getModuleAlias());
		ohsModuleConfig
				.setModuleName(StringUtils.isEmpty(testsuitObj.getModuleName()) ? null : testsuitObj.getModuleName());
		// 如果送了系统码或者系统名，表示不是查询全部的模块
		if (!StringUtils.isEmpty(testsuitObj.getSysAlias()) || !StringUtils.isEmpty(testsuitObj.getSysChineseNme())) {
			ohsModuleConfig.setSysId(ohsSysConfig.getId());
		}

		List<OhsModuleConfig> ohsModuleConfigList = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigList)) {
			throw new OhsException("当前系统不存在对应模块配置信息，请自行添加模块！");
		}

		ohsModuleConfig = ohsModuleConfigList.get(0);

		Pageable pageable = PageRequest.of(testsuitObj.getCurrent() - 1 < 0 ? 0 : testsuitObj.getCurrent() - 1,
				testsuitObj.getPageSize());

		OhsTestsuitConfig ohsTestsuitConfig = new OhsTestsuitConfig();
		ohsTestsuitConfig.setSysId(ohsSysConfig.getId());
		ohsTestsuitConfig.setModuleId(ohsModuleConfig.getId());

		Page<OhsTestsuitConfig> ohsTestsuitConfigListPage = ohsTestsuitConfigRepository
				.findAll(Example.of(ohsTestsuitConfig), pageable);
		List<OhsTestsuitConfig> ohsTestsuitConfigList = ohsTestsuitConfigListPage.getContent();
		if (CollectionUtils.isEmpty(ohsTestsuitConfigList)) {
			throw new OhsException("当前系统不存在对应接口配置信息，请自行添加接口！");
		}

		List<Testsuit> interfaceLst = new ArrayList<>();
		for (OhsTestsuitConfig ohsIter : ohsTestsuitConfigList) {
			Testsuit interfaceRetObj = new Testsuit();
			interfaceRetObj.setId(ohsIter.getId() + "");
			interfaceRetObj.setSysAlias(ohsSysConfig.getSysAlias());
			interfaceRetObj.setSysChineseNme(ohsSysConfig.getSysChineseNme());

			interfaceRetObj.setModuleAlias(ohsModuleConfig.getModuleAlias());
			interfaceRetObj.setModuleName(ohsModuleConfig.getModuleName());

			interfaceRetObj.setCreateDate(ohsIter.getCreateDate());
			interfaceRetObj.setCreateUser(ohsIter.getCreateUser());
			interfaceRetObj.setUpdateDate(ohsIter.getUpdateDate());
			interfaceRetObj.setUpdateUser(ohsIter.getUpdateUser());
			
			interfaceLst.add(interfaceRetObj);
		}

		PageResponse<Testsuit> modulePageRsp = new PageResponse<Testsuit>(interfaceLst,
				ohsTestsuitConfigListPage.getNumber(), ohsTestsuitConfigListPage.getSize(),
				ohsTestsuitConfigListPage.getTotalElements(), ohsTestsuitConfigListPage.getTotalPages());
		return modulePageRsp;
	}

	@Override
	public Testsuit saveTestsuitConfig(Testsuit testsuitObj) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(testsuitObj.getSysAlias()) ? null : testsuitObj.getSysAlias());
		ohsSysConfig.setSysChineseNme(
				StringUtils.isEmpty(testsuitObj.getSysChineseNme()) ? null : testsuitObj.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("当前系统不存在对应系统配置信息，请先添加系统配置信息后再查询或新增修改模块！");
		}

		// 只允许系统中存在唯一系统码和唯一系统名的系统
		ohsSysConfig = ohsSysConfigLst.get(0);

		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(
				StringUtils.isEmpty(testsuitObj.getModuleAlias()) ? null : testsuitObj.getModuleAlias());
		ohsModuleConfig
				.setModuleName(StringUtils.isEmpty(testsuitObj.getModuleName()) ? null : testsuitObj.getModuleName());
		// 如果送了系统码或者系统名，表示不是查询全部的模块
		if (!StringUtils.isEmpty(testsuitObj.getSysAlias()) || !StringUtils.isEmpty(testsuitObj.getSysChineseNme())) {
			ohsModuleConfig.setSysId(ohsSysConfig.getId());
		}

		List<OhsModuleConfig> ohsModuleConfigList = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigList)) {
			throw new OhsException("当前系统不存在对应模块配置信息，请自行添加模块！");
		}

		ohsModuleConfig = ohsModuleConfigList.get(0);
		
		OhsTestsuitConfig ohsTestsuitConfig = new OhsTestsuitConfig();
		ohsTestsuitConfig.setSysId(ohsSysConfig.getId());
		ohsTestsuitConfig.setModuleId(ohsModuleConfig.getId());
		ohsTestsuitConfig.setCreateDate(new Timestamp(new Date().getTime()));
		ohsTestsuitConfig.setCreateUser("admin");
		
		ohsTestsuitConfig = ohsTestsuitConfigRepository.save(ohsTestsuitConfig);
		
		testsuitObj.setId("" + ohsTestsuitConfig.getId());
		testsuitObj.setCreateUser(ohsTestsuitConfig.getCreateUser());
		testsuitObj.setCreateDate(ohsTestsuitConfig.getCreateDate());
		return testsuitObj;
	}

	@Override
	public Testsuit deleteById(int id) throws OhsException {
		Optional<OhsTestsuitConfig> ohsTestsuitConfigOpt = ohsTestsuitConfigRepository.findById(id);
		if (!ohsTestsuitConfigOpt.isPresent()) {
			throw new OhsException("该接口信息已被删除！请重新查询！");
		}
		ohsTestsuitConfigRepository.deleteById(id);
		Testsuit testsuitObj = new Testsuit();
		testsuitObj.setId(id+ "");
		return testsuitObj;
	}

	@Override
	public Testsuit updateById(Testsuit testsuitObj) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(testsuitObj.getSysAlias()) ? null : testsuitObj.getSysAlias());
		ohsSysConfig.setSysChineseNme(
				StringUtils.isEmpty(testsuitObj.getSysChineseNme()) ? null : testsuitObj.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("当前系统不存在对应系统配置信息，请先添加系统配置信息后再查询或新增修改模块！");
		}

		// 只允许系统中存在唯一系统码和唯一系统名的系统
		ohsSysConfig = ohsSysConfigLst.get(0);

		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(
				StringUtils.isEmpty(testsuitObj.getModuleAlias()) ? null : testsuitObj.getModuleAlias());
		ohsModuleConfig
				.setModuleName(StringUtils.isEmpty(testsuitObj.getModuleName()) ? null : testsuitObj.getModuleName());
		// 如果送了系统码或者系统名，表示不是查询全部的模块
		if (!StringUtils.isEmpty(testsuitObj.getSysAlias()) || !StringUtils.isEmpty(testsuitObj.getSysChineseNme())) {
			ohsModuleConfig.setSysId(ohsSysConfig.getId());
		}

		List<OhsModuleConfig> ohsModuleConfigList = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigList)) {
			throw new OhsException("当前系统不存在对应模块配置信息，请自行添加模块！");
		}

		ohsModuleConfig = ohsModuleConfigList.get(0);
		
		Optional<OhsTestsuitConfig> ohsTestsuitConfigOpt = ohsTestsuitConfigRepository.findById(Integer.parseInt(testsuitObj.getId()));
		if (!ohsTestsuitConfigOpt.isPresent()) {
			throw new OhsException("该接口信息不存在！");
		}
		
		OhsTestsuitConfig ohsTestsuitConfig = new OhsTestsuitConfig();
		ohsTestsuitConfig.setId(Integer.parseInt(testsuitObj.getId()));
		ohsTestsuitConfig.setSysId(ohsSysConfig.getId());
		ohsTestsuitConfig.setModuleId(ohsModuleConfig.getId());
		ohsTestsuitConfig.setCreateDate(ohsTestsuitConfigOpt.get().getCreateDate());
		ohsTestsuitConfig.setCreateUser(ohsTestsuitConfigOpt.get().getCreateUser());
		ohsTestsuitConfig.setUpdateDate(new Timestamp(new Date().getTime()));
		ohsTestsuitConfig.setUpdateUser("admin");
		
		ohsTestsuitConfig = ohsTestsuitConfigRepository.save(ohsTestsuitConfig);
		
		testsuitObj.setId("" + ohsTestsuitConfig.getId());
		testsuitObj.setUpdateDate(new Timestamp(new Date().getTime()));
		testsuitObj.setUpdateUser("admin");
		testsuitObj.setCreateUser(ohsTestsuitConfig.getCreateUser());
		testsuitObj.setCreateDate(ohsTestsuitConfig.getCreateDate());
		return testsuitObj;
	}

}
