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

import com.jiangjie.ohs.dto.EnvInfo;
import com.jiangjie.ohs.dto.Module;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.WhereInfo;
import com.jiangjie.ohs.entity.OhsEnvironmentConfig;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.common.RelationUserInfo;
import com.jiangjie.ohs.entity.dataEntity.OhsSingleQueryWhereInfo;
import com.jiangjie.ohs.entity.dataEntity.OhsSingleSqlConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsEnvironmentConfigRepository;
import com.jiangjie.ohs.repository.OhsModuleConfigRepository;
import com.jiangjie.ohs.repository.OhsSingleQueryWhereInfoRepository;
import com.jiangjie.ohs.repository.OhsSingleSqlConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.service.ModuleConfigService;

@Service
public class ModuleConfigServiceImpl implements ModuleConfigService {

	@Autowired
	private OhsModuleConfigRepository ohsModuleConfigRepository;

	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;
	
	@Autowired
	private OhsEnvironmentConfigRepository ohsEnvironmentConfigRepository;
	
	@Override
	public PageResponse<Module> getAllModule(Module module) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(module.getSysAlias()) ? null : module.getSysAlias());
		ohsSysConfig.setSysChineseNme(StringUtils.isEmpty(module.getSysChineseNme()) ? null : module.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("当前系统不存在对应系统配置信息，请先添加系统配置信息后再查询或新增修改模块！");
		}

		// 只允许系统中存在唯一系统码和唯一系统名的系统
		ohsSysConfig = ohsSysConfigLst.get(0);

		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(StringUtils.isEmpty(module.getModuleAlias()) ? null : module.getModuleAlias());
		ohsModuleConfig.setModuleName(StringUtils.isEmpty(module.getModuleName()) ? null : module.getModuleName());
		// 如果送了系统码或者系统名，表示不是查询全部的模块
		if (!StringUtils.isEmpty(module.getSysAlias()) || !StringUtils.isEmpty(module.getSysChineseNme())) {
			ohsModuleConfig.setSysId(ohsSysConfig.getId());
		}

		Pageable pageable = PageRequest.of(module.getCurrent() - 1 < 0 ? 0 : module.getCurrent() - 1, module.getPageSize());
		
		Page<OhsModuleConfig> ohsModuleConfigListPage = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig), pageable);
		List<OhsModuleConfig> ohsModuleConfigList = ohsModuleConfigListPage.getContent();
		if (CollectionUtils.isEmpty(ohsModuleConfigList)) {
			throw new OhsException("当前系统不存在对应模块配置信息，请自行添加模块！");
		}

		List<Module> moduleLst = new ArrayList<>();

		for (OhsModuleConfig ohsModuleCfg : ohsModuleConfigList) {
			Module mde = new Module();
			mde.setId(ohsModuleCfg.getId());
			mde.setModuleAlias(ohsModuleCfg.getModuleAlias());
			mde.setModuleName(ohsModuleCfg.getModuleName());
			if (ohsModuleConfig.getSysId() == null) {
				Optional<OhsSysConfig> ohsSysConfigOpt = ohsSysConfigRepository.findById(ohsModuleCfg.getSysId());
				if (ohsSysConfigOpt.isPresent()) {
					ohsSysConfig = ohsSysConfigOpt.get();
				}
			}
			// 查询了指定系统的模块
			mde.setSysAlias(ohsSysConfig.getSysAlias());
			mde.setSysChineseNme(ohsSysConfig.getSysChineseNme());
			mde.setUpdateUser(ohsModuleCfg.getRelationUserInfo().getUpdateUser());
			mde.setUpdateDate(ohsModuleCfg.getRelationUserInfo().getUpdateDate());
			mde.setCreateDate(ohsModuleCfg.getRelationUserInfo().getCreateDate());
			mde.setCreateUser(ohsModuleCfg.getRelationUserInfo().getCreateUser());
			moduleLst.add(mde);
		}

		PageResponse<Module> modulePageRsp = new PageResponse<Module>(moduleLst, 
					ohsModuleConfigListPage.getNumber(), 
					ohsModuleConfigListPage.getSize(),
					ohsModuleConfigListPage.getTotalElements(),
					ohsModuleConfigListPage.getTotalPages());
		return modulePageRsp;
	}

	@Override
	public Module updateById(Module module) throws OhsException {
		Optional<OhsModuleConfig> ohsModuleConfigOpt = ohsModuleConfigRepository.findById(module.getId());
		if (!ohsModuleConfigOpt.isPresent()) {
			throw new OhsException("该模块不存在！可能被其他人删除了！");
		}
		OhsModuleConfig ohsModuleConfig = ohsModuleConfigOpt.get();
		ohsModuleConfig.setModuleAlias(module.getModuleAlias());
		ohsModuleConfig.setModuleName(module.getModuleName());

		ohsModuleConfig.getRelationUserInfo().setUpdateUser("修改者");
		ohsModuleConfig.getRelationUserInfo().setUpdateDate(new Timestamp(new Date().getTime()));
		
		module.setUpdateDate(ohsModuleConfig.getRelationUserInfo().getUpdateDate());
		module.setUpdateUser(ohsModuleConfig.getRelationUserInfo().getUpdateUser());
		module.setCreateDate(ohsModuleConfig.getRelationUserInfo().getCreateDate());
		module.setCreateUser(ohsModuleConfig.getRelationUserInfo().getCreateUser());
		ohsModuleConfigRepository.save(ohsModuleConfig);
		return module;
	}

	@Override
	public Module deleteById(Integer id) throws OhsException {
		Optional<OhsModuleConfig> ohsModuleConfigOpt = ohsModuleConfigRepository.findById(id);
		if (!ohsModuleConfigOpt.isPresent()) {
			throw new OhsException("该模块不存在！可能被其他人删除了！");
		}
		ohsModuleConfigRepository.deleteById(id);
		Module module = new Module();
		module.setId(id);
		return module;
	}

	@Override
	public Module saveModuleConfig(Module module) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(module.getSysAlias()) ? null : module.getSysAlias());
		ohsSysConfig.setSysChineseNme(StringUtils.isEmpty(module.getSysChineseNme()) ? null : module.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("对应系统配置信息不存在，可能被别人删除了。请先添加系统配置信息后再查询或新增修改模块！");
		}
		
		// 只允许系统中存在唯一系统码和唯一系统名的系统
		ohsSysConfig = ohsSysConfigLst.get(0);
		
		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setSysId(ohsSysConfig.getId());
		ohsModuleConfig.setModuleAlias(module.getModuleAlias());
		ohsModuleConfig.setModuleName(module.getModuleName());
		RelationUserInfo relationUserInfo = new RelationUserInfo();
		relationUserInfo.setCreateDate(new Timestamp(new Date().getTime()));
		relationUserInfo.setCreateUser("姜杰");
		ohsModuleConfig.setRelationUserInfo(relationUserInfo);
		ohsModuleConfig = ohsModuleConfigRepository.save(ohsModuleConfig);
		
		module.setCreateDate(ohsModuleConfig.getRelationUserInfo().getCreateDate());
		module.setCreateUser(ohsModuleConfig.getRelationUserInfo().getCreateUser());
		module.setId(ohsModuleConfig.getId());
		return module;
	}

	@Autowired
	private OhsSingleSqlConfigRepository ohsSingleSqlConfigRepository;
	
	@Autowired
	private OhsSingleQueryWhereInfoRepository ohsSingleQueryWhereInfoRepository;
	
	@Override
	public List<Module> getModuleBySysAlias(Module module) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(module.getSysAlias());
		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("系统中不存在该系统码！");
		}
		if (ohsSysConfigLst.size() > 1) {
			throw new OhsException("数据库中系统码不唯一，请联系管理员检查系统数据！");
		}
		
		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setSysId(ohsSysConfigLst.get(0).getId());
		List<OhsModuleConfig> ohsModuleConfigLst = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigLst)) {
			throw new OhsException("当前系统下不存在模块信息！");
		}
		List<Module> moduleLst = new ArrayList<>();
		for (OhsModuleConfig  oshModuleConfig : ohsModuleConfigLst) {
			Module newModule = new Module();
			// 查询sql中的条件字段信息
			List<WhereInfo> whereInfos = new ArrayList<WhereInfo>();
			newModule.setWhereInfo(whereInfos);
			
			OhsSingleSqlConfig ohsConfigSqlConfig = new OhsSingleSqlConfig();
			ohsConfigSqlConfig.setSysId(ohsSysConfigLst.get(0).getId());
			ohsConfigSqlConfig.setModuleId(oshModuleConfig.getId());
			List<OhsSingleSqlConfig> ohsSingleSqlConfigLst = ohsSingleSqlConfigRepository.findAll(Example.of(ohsConfigSqlConfig));
			if (!CollectionUtils.isEmpty(ohsSingleSqlConfigLst)) {
				for (OhsSingleSqlConfig singleSqlConfig : ohsSingleSqlConfigLst) {
					OhsSingleQueryWhereInfo ohsSingleQueryWhereInfo  = new OhsSingleQueryWhereInfo();
					ohsSingleQueryWhereInfo.setSingleSqlId(singleSqlConfig.getId());
					List<OhsSingleQueryWhereInfo> ohsSingleQueryWhereInfoLst = ohsSingleQueryWhereInfoRepository.findAll(Example.of(ohsSingleQueryWhereInfo));
					if (!StringUtils.isEmpty(ohsSingleQueryWhereInfoLst)) {
						for (OhsSingleQueryWhereInfo whereInfo : ohsSingleQueryWhereInfoLst) {
							WhereInfo wi = new WhereInfo();
							wi.setKeyInfo(whereInfo.getKeyInfo());
							wi.setKeyChnInfo(whereInfo.getKeyChnInfo());
							whereInfos.add(wi);
						}
					}
				}
			}
			
			// 查询环境信息
			OhsEnvironmentConfig ohsEnvironmentConfig = new OhsEnvironmentConfig();
			ohsEnvironmentConfig.setSysId(ohsSysConfigLst.get(0).getId());
			List<OhsEnvironmentConfig> ohsEnvironmentConfigLst = ohsEnvironmentConfigRepository.findAll(Example.of(ohsEnvironmentConfig));
			if (!CollectionUtils.isEmpty(ohsEnvironmentConfigLst)) {
				List<EnvInfo> envInfos = new ArrayList<EnvInfo>();
				ohsEnvironmentConfigLst.stream().forEach(ohsEnv -> {
					EnvInfo envInfo = new EnvInfo();
					envInfo.setEnvId(ohsEnv.getId() + "");
					envInfo.setEnvAlias(ohsEnv.getEvnName());
					envInfos.add(envInfo);
				});
				newModule.setEnvInfo(envInfos);
			}
			
			
			
			newModule.setSysId(ohsSysConfigLst.get(0).getId());
			newModule.setSysAlias(ohsSysConfigLst.get(0).getSysAlias());
			newModule.setSysChineseNme(ohsSysConfigLst.get(0).getSysChineseNme());
			newModule.setModuleAlias(oshModuleConfig.getModuleAlias());
			newModule.setModuleName(oshModuleConfig.getModuleName());
			moduleLst.add(newModule);
		}
		return moduleLst;
	}

}
