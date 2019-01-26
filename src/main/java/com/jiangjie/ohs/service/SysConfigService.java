package com.jiangjie.ohs.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jiangjie.ohs.dto.SysInfo;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.exception.OhsException;

/**
 * 系统配置服务类
 * @author Administrator
 *
 */
public interface SysConfigService {

	/**
	 * 获得所有的系统配置信息
	 * @param ohsSysConfig
	 * @return
	 */
	public Page<OhsSysConfig> getAllSys(OhsSysConfig ohsSysConfig);
	
	/**
	 * 获得系统配置信息，包括系统下的所有表信息
	 * @param ohsSysConfig
	 * @return
	 */
	public List<SysInfo> getAllSysInfo(OhsSysConfig ohsSysConfig);

	/**
	 * 保存系统配置信息
	 * @param ohsSysConfig
	 * @return
	 * @throws OhsException
	 */
	public OhsSysConfig saveSysConfig(OhsSysConfig ohsSysConfig) throws OhsException;
	
	/**
	 * 删除系统配置信息
	 * @param ohsSysConfig
	 * @return
	 * @throws OhsException
	 */
	public OhsSysConfig deleteById(OhsSysConfig ohsSysConfig) throws OhsException;
	
	/**
	 * 更新系统配置信息
	 * @param ohsSysConfig
	 * @return
	 * @throws OhsException
	 */
	public OhsSysConfig updateById(OhsSysConfig ohsSysConfig) throws OhsException;
}
