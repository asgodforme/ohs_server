package com.jiangjie.ohs.service;

import java.util.List;

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
	public List<OhsSysConfig> getAllSys(OhsSysConfig ohsSysConfig);

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
