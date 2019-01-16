package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jiangjie.ohs.dto.Module;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.common.RelationUserInfo;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsModuleConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.service.ModuleConfigService;

@Service
public class ModuleConfigServiceImpl implements ModuleConfigService {

	@Autowired
	private OhsModuleConfigRepository ohsModuleConfigRepository;

	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;

	@Override
	public List<Module> getAllModule(Module module) throws OhsException {
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

		List<OhsModuleConfig> ohsModuleConfigList = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
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

		return moduleLst;
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

}
