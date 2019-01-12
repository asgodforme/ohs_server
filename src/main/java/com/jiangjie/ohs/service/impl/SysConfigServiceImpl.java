package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.service.SysConfigService;

/**
 * 系统配置服务实现类
 * @author Administrator
 *
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

	@Autowired
	private OhsSysConfigRepository sysConfigRepository;
	
	public List<OhsSysConfig> getAllSys(OhsSysConfig ohsSysConfig) {
		Example<OhsSysConfig> example = Example.of(ohsSysConfig);
		return sysConfigRepository.findAll(example);
	}
	
	@Override
	public OhsSysConfig saveSysConfig(OhsSysConfig ohsSysConfig) throws OhsException {
		
		if (sysConfigRepository.exists(Example.of(ohsSysConfig))
				|| sysConfigRepository.exists(Example.of(new OhsSysConfig(ohsSysConfig.getSysAlias(), null)))
				|| sysConfigRepository.exists(Example.of(new OhsSysConfig(null, ohsSysConfig.getSysChineseNme())))) {
			throw new OhsException("该系统已经存在！");
		}
		ohsSysConfig.setCreateDate(new Timestamp(new Date().getTime()));
		ohsSysConfig.setCreateUser("姜杰");
		return sysConfigRepository.save(ohsSysConfig);
	}

	@Override
	public OhsSysConfig deleteById(OhsSysConfig ohsSysConfig) throws OhsException {
		Optional<OhsSysConfig> ohsSysConfigOpt = sysConfigRepository.findById(ohsSysConfig.getId());
		if (!ohsSysConfigOpt.isPresent()) {
			throw new OhsException("该系统已被删除！");
		}
		ohsSysConfig.setCreateDate(ohsSysConfigOpt.get().getCreateDate());
		ohsSysConfig.setCreateUser(ohsSysConfigOpt.get().getCreateUser());
		sysConfigRepository.deleteById(ohsSysConfig.getId());
		return ohsSysConfigOpt.get();
	}

	@Override
	public OhsSysConfig updateById(OhsSysConfig ohsSysConfig) throws OhsException {
		Optional<OhsSysConfig> ohsSysConfigOpt = sysConfigRepository.findById(ohsSysConfig.getId());
		if (!ohsSysConfigOpt.isPresent()) {
			throw new OhsException("该系统不存在！");
		}
		ohsSysConfig.setCreateDate(ohsSysConfigOpt.get().getCreateDate());
		ohsSysConfig.setCreateUser(ohsSysConfigOpt.get().getCreateUser());
		ohsSysConfig.setUpdateDate(new Timestamp(new Date().getTime()));
		ohsSysConfig.setUpdateUser("修改者");
		return sysConfigRepository.save(ohsSysConfig);
	}
}
