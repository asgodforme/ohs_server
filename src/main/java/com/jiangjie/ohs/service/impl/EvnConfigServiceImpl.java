package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jiangjie.ohs.dto.Evn;
import com.jiangjie.ohs.entity.OhsEnvironmentConfig;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.common.RelationUserInfo;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsEvnironmentConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.service.EvnConfigService;
import com.jiangjie.ohs.utils.OhsUtils;

@Service
public class EvnConfigServiceImpl implements EvnConfigService {

	@Autowired
	private OhsEvnironmentConfigRepository ohsEnvironmentConfigRepository;

	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;

	private static final Function<Evn, OhsEnvironmentConfig> toOhsEnvironmentConfig = frontEvn -> {
		OhsEnvironmentConfig ohsEnvironmentConfig = new OhsEnvironmentConfig();
		ohsEnvironmentConfig.setDbNme(OhsUtils.putIfNotBlank(frontEvn.getDbNme()));
		ohsEnvironmentConfig.setDbPwd(OhsUtils.putIfNotBlank(frontEvn.getDbPwd()));
		ohsEnvironmentConfig.setEvnAlias(OhsUtils.putIfNotBlank(frontEvn.getEvnAlias()));
		ohsEnvironmentConfig.setEvnIp(OhsUtils.putIfNotBlank(frontEvn.getEvnIp()));
		ohsEnvironmentConfig.setEvnName(OhsUtils.putIfNotBlank(frontEvn.getEvnName()));
		ohsEnvironmentConfig.setEvnPort(OhsUtils.putIfNotBlank(frontEvn.getEvnPort()));
		ohsEnvironmentConfig.setEvnTyp(OhsUtils.putIfNotBlank(frontEvn.getEvnTyp()));
		ohsEnvironmentConfig.setInterfaceNme(OhsUtils.putIfNotBlank(frontEvn.getInterfaceNme()));
		return ohsEnvironmentConfig;
	};

	private static final Function<OhsEnvironmentConfig, Evn> toEvn = ohsEvn -> {
		Evn retEvn = new Evn();
		retEvn.setDbNme(OhsUtils.putIfNotBlank(ohsEvn.getDbNme()));
		retEvn.setDbPwd(OhsUtils.putIfNotBlank(ohsEvn.getDbPwd()));
		retEvn.setEvnAlias(OhsUtils.putIfNotBlank(ohsEvn.getEvnAlias()));
		retEvn.setEvnIp(OhsUtils.putIfNotBlank(ohsEvn.getEvnIp()));
		retEvn.setEvnName(OhsUtils.putIfNotBlank(ohsEvn.getEvnName()));
		retEvn.setEvnPort(OhsUtils.putIfNotBlank(ohsEvn.getEvnPort()));
		retEvn.setEvnTyp(OhsUtils.putIfNotBlank(ohsEvn.getEvnTyp()));
		retEvn.setInterfaceNme(OhsUtils.putIfNotBlank(ohsEvn.getInterfaceNme()));
		return retEvn;
	};

	@Override
	public List<Evn> getAllEvn(Evn evn) throws OhsException {

		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(evn.getSysAlias()) ? null : evn.getSysAlias());
		ohsSysConfig.setSysChineseNme(StringUtils.isEmpty(evn.getSysChineseNme()) ? null : evn.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("不存在指定的系统配置信息！");
		}

		ohsSysConfig = ohsSysConfigLst.get(0);

		OhsEnvironmentConfig environmentConfig = toOhsEnvironmentConfig.apply(evn);
		// 如果送了系统码或者系统名，表示不是查询全部的环境信息
		if (!StringUtils.isEmpty(evn.getSysAlias()) || !StringUtils.isEmpty(evn.getSysChineseNme())) {
			environmentConfig.setSysId(ohsSysConfig.getId());
		} 
		List<OhsEnvironmentConfig> ohsEnvironmentConfigLst = ohsEnvironmentConfigRepository.findAll(Example.of(environmentConfig));

		if (CollectionUtils.isEmpty(ohsEnvironmentConfigLst)) {
			throw new OhsException("不存在对应的环境配置信息！");
		} else {
			List<Evn> EvnLst = new ArrayList<>();
			for (OhsEnvironmentConfig ohsEvn :ohsEnvironmentConfigLst) {
				Evn retEvn = toEvn.apply(ohsEvn);
				if (environmentConfig.getId() == null) {
					Optional<OhsSysConfig> ohsSysConfigOpt = ohsSysConfigRepository.findById(ohsEvn.getSysId());
					if (ohsSysConfigOpt.isPresent()) {
						retEvn.setSysAlias(ohsSysConfigOpt.get().getSysAlias());
						retEvn.setSysChineseNme(ohsSysConfigOpt.get().getSysChineseNme());
					}
				} else {
					// 如果送了系统码或者系统名，表示不是查询全部的环境信息
					retEvn.setSysAlias(ohsSysConfig.getSysAlias());
					retEvn.setSysChineseNme(ohsSysConfig.getSysChineseNme());
				}
				retEvn.setCreateDate(ohsEvn.getRelationUserInfo().getCreateDate());
				retEvn.setCreateUser(ohsEvn.getRelationUserInfo().getCreateUser());
				retEvn.setUpdateDate(ohsEvn.getRelationUserInfo().getUpdateDate());
				retEvn.setUpdateUser(ohsEvn.getRelationUserInfo().getUpdateUser());
				retEvn.setId(ohsEvn.getId());
				EvnLst.add(retEvn);
			}
			return EvnLst;
		}

	}

	@Override
	public Evn saveEvnConfig(Evn evn) throws OhsException {
		
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(evn.getSysAlias()) ? null : evn.getSysAlias());
		ohsSysConfig.setSysChineseNme(StringUtils.isEmpty(evn.getSysChineseNme()) ? null : evn.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("不存在指定的系统配置信息！");
		}
		
		OhsEnvironmentConfig environmentConfig = toOhsEnvironmentConfig.apply(evn);
		environmentConfig.setSysId(ohsSysConfigLst.get(0).getId());
		RelationUserInfo relationUserInfo = new RelationUserInfo();
		relationUserInfo.setCreateDate(new Timestamp(new Date().getTime()));
		relationUserInfo.setCreateUser("姜杰");
		environmentConfig.setRelationUserInfo(relationUserInfo);

		environmentConfig = ohsEnvironmentConfigRepository.save(environmentConfig);
		
		evn.setId(environmentConfig.getId());
		evn.setCreateDate(environmentConfig.getRelationUserInfo().getCreateDate());
		evn.setCreateUser(environmentConfig.getRelationUserInfo().getCreateUser());
		
		return evn;
	}

	@Override
	public Evn deleteById(int id) throws OhsException {
		Optional<OhsEnvironmentConfig> ohsEnvironmentConfigOpt = ohsEnvironmentConfigRepository.findById(id);
		if (!ohsEnvironmentConfigOpt.isPresent()) {
			throw new OhsException("该配置信息已经被删除，请重新查询！");
		}
		ohsEnvironmentConfigRepository.deleteById(id);
		Evn evn = new Evn();
		evn.setId(id);
		return evn;
	}

	@Override
	public Evn updateById(Evn evn) throws OhsException {
		Optional<OhsEnvironmentConfig> ohsEnvironmentConfigOpt = ohsEnvironmentConfigRepository.findById(evn.getId());
		if (!ohsEnvironmentConfigOpt.isPresent()) {
			throw new OhsException("该配置信息已经被删除，请重新查询！");
		}
		
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(evn.getSysAlias()) ? null : evn.getSysAlias());
		ohsSysConfig.setSysChineseNme(StringUtils.isEmpty(evn.getSysChineseNme()) ? null : evn.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("不存在指定的系统配置信息！");
		}
		
		OhsEnvironmentConfig environmentConfig = toOhsEnvironmentConfig.apply(evn);
		environmentConfig.setSysId(ohsSysConfigLst.get(0).getId());
		RelationUserInfo relationUserInfo = new RelationUserInfo();
		relationUserInfo.setUpdateDate(new Timestamp(new Date().getTime()));
		relationUserInfo.setUpdateUser("修改者");
		relationUserInfo.setCreateDate(ohsSysConfigLst.get(0).getCreateDate());
		relationUserInfo.setCreateUser(ohsSysConfigLst.get(0).getCreateUser());
		environmentConfig.setRelationUserInfo(relationUserInfo);
		environmentConfig.setId(evn.getId());
		
		ohsEnvironmentConfigRepository.save(environmentConfig);
		
		evn.setUpdateDate(environmentConfig.getRelationUserInfo().getUpdateDate());
		evn.setUpdateUser(environmentConfig.getRelationUserInfo().getUpdateUser());
		evn.setCreateDate(environmentConfig.getRelationUserInfo().getCreateDate());
		evn.setCreateUser(environmentConfig.getRelationUserInfo().getCreateUser());
		return evn;
	}

}
