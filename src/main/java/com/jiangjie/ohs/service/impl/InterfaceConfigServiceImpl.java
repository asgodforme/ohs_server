package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.jiangjie.ohs.dto.Interface;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.entity.OhsEnvironmentConfig;
import com.jiangjie.ohs.entity.OhsInterfaceSingleRecords;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.autoTest.OhsInterfaceConfig;
import com.jiangjie.ohs.entity.common.RelationUserInfo;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsEnvironmentConfigRepository;
import com.jiangjie.ohs.repository.OhsInterfaceConfigRepository;
import com.jiangjie.ohs.repository.OhsInterfaceSingleRecordsRepository;
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
	
	@Autowired
	private OhsInterfaceSingleRecordsRepository ohsInterfaceSingleRecordsRepository;
	
	private static final Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
	
	@Autowired
	private OhsEnvironmentConfigRepository ohsEnvironmentConfigRepository;
	

	@Override
	public PageResponse<Interface> getAllInterface(Interface interfaceObj) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(interfaceObj.getSysAlias()) ? null : interfaceObj.getSysAlias());
		ohsSysConfig.setSysChineseNme(
				StringUtils.isEmpty(interfaceObj.getSysChineseNme()) ? null : interfaceObj.getSysChineseNme());
		ohsSysConfig.setCreateUser(interfaceObj.getCreateUser());
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
		RelationUserInfo relationUserInfo = new RelationUserInfo();
		relationUserInfo.setCreateUser(interfaceObj.getCreateUser());
		ohsModuleConfig.setRelationUserInfo(relationUserInfo);
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
		ohsInterfaceConfig.setCreateUser(interfaceObj.getCreateUser());
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
			
			// 如果是接口测试，则返回对应实际的请求响应数据
			if ("Y".equals(interfaceObj.getIsTest())) {
				List<String> parameters = new ArrayList<>();
				Matcher matcher = pattern.matcher(ohsIter.getRequestTemplate());
				while (matcher.find()) {
					String keyName = matcher.group(1);
					parameters.add(keyName);
				}
				interfaceRetObj.setParameters(parameters);
				
				OhsInterfaceSingleRecords ohsInterfaceSingleRecords = new OhsInterfaceSingleRecords();
				ohsInterfaceSingleRecords.setInterfaceId(ohsIter.getId());
				ohsInterfaceSingleRecords.setCreateUser(interfaceObj.getCreateUser());
				List<OhsInterfaceSingleRecords> ohsInterfaceSingleRecordsLst = ohsInterfaceSingleRecordsRepository.findAll(Example.of(ohsInterfaceSingleRecords));
				if (!CollectionUtils.isEmpty(ohsInterfaceSingleRecordsLst)) {
					ohsInterfaceSingleRecordsLst.sort(Comparator.comparing(OhsInterfaceSingleRecords::getId));
					interfaceRetObj.setRequestTemplate(ohsInterfaceSingleRecordsLst.get(ohsInterfaceSingleRecordsLst.size()-1).getRequestData());
					interfaceRetObj.setResponseTemplate(ohsInterfaceSingleRecordsLst.get(ohsInterfaceSingleRecordsLst.size()-1).getResponseData());
				} else {
					interfaceRetObj.setRequestTemplate(null);
					interfaceRetObj.setResponseTemplate(null);
				}
			}
			
			
			OhsEnvironmentConfig ohsEnvironmentConfig = new OhsEnvironmentConfig();
			ohsEnvironmentConfig.setSysId(ohsSysConfig.getId());
			ohsEnvironmentConfig.setRelationUserInfo(relationUserInfo);
			List<OhsEnvironmentConfig> ohsEnvironmentConfigLst = ohsEnvironmentConfigRepository.findAll(Example.of(ohsEnvironmentConfig));
			List<Interface.EnvironmentInfo> environmentInfoLst = new ArrayList<>();
			ohsEnvironmentConfigLst.stream().forEach(env -> {
				Interface.EnvironmentInfo environmentInfo = new Interface().new EnvironmentInfo();
				environmentInfo.setId(env.getId());
				environmentInfo.setEvnName(env.getEvnName());
				environmentInfoLst.add(environmentInfo);
			});
			interfaceRetObj.setEnvironmentInfos(environmentInfoLst);
			
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
		ohsSysConfig.setCreateUser(interfaceObj.getCreateUser());
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
		RelationUserInfo relationUserInfo = new RelationUserInfo();
		relationUserInfo.setCreateUser(interfaceObj.getCreateUser());
		ohsModuleConfig.setRelationUserInfo(relationUserInfo);
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
		ohsInterfaceConfig.setCreateUser(interfaceObj.getCreateUser());
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
	public Interface deleteById(int id, String tokenName) throws OhsException {
		Optional<OhsInterfaceConfig> ohsInterfaceConfigOpt = ohsInterfaceConfigRepository.findById(id);
		if (!ohsInterfaceConfigOpt.isPresent()) {
			throw new OhsException("该接口信息已被删除！请重新查询！");
		}
		if (!ohsInterfaceConfigOpt.get().getCreateUser().equals(tokenName)) {
			throw new OhsException("禁止删除非当前用户数据！");
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
		ohsSysConfig.setCreateUser(interfaceObj.getCreateUser());
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
		RelationUserInfo relationUserInfo = new RelationUserInfo();
		relationUserInfo.setCreateUser(interfaceObj.getCreateUser());
		ohsModuleConfig.setRelationUserInfo(relationUserInfo);
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
		ohsInterfaceConfig.setUpdateUser(interfaceObj.getCreateUser());
		ohsInterfaceConfig.setInterfaceType(interfaceObj.getInterfaceType());
		ohsInterfaceConfig.setInterfaceAlias(interfaceObj.getInterfaceAlias());
		ohsInterfaceConfig.setInterfaceName(interfaceObj.getInterfaceName());
		ohsInterfaceConfig.setRequestTemplate(interfaceObj.getRequestTemplate());
		ohsInterfaceConfig.setResponseTemplate(interfaceObj.getResponseTemplate());
		
		
		ohsInterfaceConfig = ohsInterfaceConfigRepository.save(ohsInterfaceConfig);
		
		interfaceObj.setId("" + ohsInterfaceConfig.getId());
		interfaceObj.setUpdateDate(new Timestamp(new Date().getTime()));
		interfaceObj.setUpdateUser(interfaceObj.getCreateUser());
		interfaceObj.setCreateUser(ohsInterfaceConfig.getCreateUser());
		interfaceObj.setCreateDate(ohsInterfaceConfig.getCreateDate());
		return interfaceObj;
	}
	
	@Autowired
	private RestTemplate restTemplate;

	public Interface restfulRequest(Interface interfaceObj) throws OhsException {
		Optional<OhsEnvironmentConfig> ohsEnv = ohsEnvironmentConfigRepository.findById(Integer.parseInt(interfaceObj.getTargetServerId()));
		if (!ohsEnv.isPresent()) {
			throw new OhsException("目标服务器不存在！");
		}
		Optional<OhsInterfaceConfig> ohsInterface = ohsInterfaceConfigRepository.findById(Integer.parseInt(interfaceObj.getId()));
		if (!ohsInterface.isPresent()) {
			throw new OhsException("接口配置信息不存在！");
		}
		StringBuffer reqUrlSb = new StringBuffer();
		reqUrlSb.append("http://").append(ohsEnv.get().getEvnIp()).append(":").append(ohsEnv.get().getEvnPort()).append("/").append(ohsInterface.get().getUrlPath());
		if ("GET".equals(ohsInterface.get().getMethod().toUpperCase())) {
			String msg = restTemplate.getForObject(reqUrlSb.toString(), String.class);
			if (!StringUtils.isEmpty(interfaceObj.getSingleRecordsId())) {
				Optional<OhsInterfaceSingleRecords> ohsSingleRecords = ohsInterfaceSingleRecordsRepository.findById(interfaceObj.getSingleRecordsId());
				if (ohsSingleRecords.isPresent()) {
					ohsSingleRecords.get().setResponseData(msg);
					ohsInterfaceSingleRecordsRepository.save(ohsSingleRecords.get());
				}
			} else {
				OhsInterfaceSingleRecords ohsInterfaceSingleRecords = new OhsInterfaceSingleRecords();
				ohsInterfaceSingleRecords.setInterfaceId(Integer.parseInt(interfaceObj.getId()));
				List<OhsInterfaceSingleRecords> ohsInterfaceSingleRecordsLst = ohsInterfaceSingleRecordsRepository.findAll(Example.of(ohsInterfaceSingleRecords));
				if (!CollectionUtils.isEmpty(ohsInterfaceSingleRecordsLst)) {
					ohsInterfaceSingleRecordsLst.sort(Comparator.comparing(OhsInterfaceSingleRecords::getId));
					ohsInterfaceSingleRecords = ohsInterfaceSingleRecordsLst.get(ohsInterfaceSingleRecordsLst.size() - 1);
					ohsInterfaceSingleRecords.setResponseData(msg);
				} else {
					ohsInterfaceSingleRecords.setResponseData(msg);
					ohsInterfaceSingleRecords.setCreateUser(interfaceObj.getCreateUser());
					ohsInterfaceSingleRecords.setCreateDate(new Timestamp(new Date().getTime()));
				}
				ohsInterfaceSingleRecordsRepository.save(ohsInterfaceSingleRecords);
				interfaceObj.setResponseTemplate(msg);
			}
		} else if ("POST".equals(ohsInterface.get().getMethod().toUpperCase())) {
//			restTemplate.postForObject(url, request, responseType);
		}
		
		return interfaceObj;

	}
}
