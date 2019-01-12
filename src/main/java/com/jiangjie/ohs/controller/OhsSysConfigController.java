package com.jiangjie.ohs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.SysConfigService;

@RestController
@RequestMapping("/api/sysConfig")
public class OhsSysConfigController {

	@Autowired
	private SysConfigService sysConfigService;

	@GetMapping("/getAllSys")
	public List<OhsSysConfig> getAllMenu(OhsSysConfig ohsSysConfig) {
		// 前端传送过来的空格也会当做查询条件去查询数据库，故在此置为null,过滤掉该条件，TODO 应该有更加先进的方法。
		if (StringUtils.isEmpty(ohsSysConfig.getSysAlias())) ohsSysConfig.setSysAlias(null);
		if (StringUtils.isEmpty(ohsSysConfig.getSysChineseNme())) ohsSysConfig.setSysChineseNme(null);
		return sysConfigService.getAllSys(ohsSysConfig);
	}
	
	/**
	 * TODO POST传值报错！！！麻蛋
	 * @param ohsSysConfig
	 */
	@GetMapping("/saveSysConfig")
	public OhsSysConfig saveSysConfig(OhsSysConfig ohsSysConfig) throws OhsException {
		return sysConfigService.saveSysConfig(ohsSysConfig);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public OhsSysConfig deleteById(@PathVariable("id") String id) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setId(Integer.parseInt(id));
		return sysConfigService.deleteById(ohsSysConfig);
	}
	
	/**
	 * TODO PUT传值报错！！！麻蛋
	 * @param ohsSysConfig
	 * @return 
	 * @throws OhsException 
	 */
	@GetMapping("/updateById")
	public OhsSysConfig updateById(OhsSysConfig ohsSysConfig) throws OhsException {
		System.out.println("---->" + ohsSysConfig);
		return sysConfigService.updateById(ohsSysConfig);
	}

}