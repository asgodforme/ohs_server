package com.jiangjie.ohs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.SysInfo;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.SysConfigService;

@RestController
@RequestMapping("/api/sysConfig")
public class OhsSysConfigController {

	@Autowired
	private SysConfigService sysConfigService;

	@GetMapping("/getAllSys")
	public Page<OhsSysConfig> getAllMenu(OhsSysConfig ohsSysConfig, String tokenName) throws OhsException {
		// 前端传送过来的空格也会当做查询条件去查询数据库，故在此置为null,过滤掉该条件，TODO 应该有更加先进的方法。
		if (StringUtils.isEmpty(ohsSysConfig.getSysAlias())) ohsSysConfig.setSysAlias(null);
		if (StringUtils.isEmpty(ohsSysConfig.getSysChineseNme())) ohsSysConfig.setSysChineseNme(null);
		if (StringUtils.isEmpty(ohsSysConfig.getSchemaName())) ohsSysConfig.setSchemaName(null);
//		List<OhsSysConfig> ohsSysConfigLst = sysConfigService.getAllSys(ohsSysConfig);
		ohsSysConfig.setCreateUser(tokenName);
		Page<OhsSysConfig> ohsSysConfigPage = sysConfigService.getAllSys(ohsSysConfig);
		if (CollectionUtils.isEmpty(ohsSysConfigPage.getContent())) {
			throw new OhsException("当前系统中无系统配置信息，请点击新增新增系统配置信息！");
		}
		return ohsSysConfigPage;
	}
	
	@GetMapping("/getAllSysWhenInit")
	public List<SysInfo> getAllSysInfo(OhsSysConfig ohsSysConfig, String tokenName) {
		// 前端传送过来的空格也会当做查询条件去查询数据库，故在此置为null,过滤掉该条件，TODO 应该有更加先进的方法。
		if (StringUtils.isEmpty(ohsSysConfig.getSysAlias())) ohsSysConfig.setSysAlias(null);
		if (StringUtils.isEmpty(ohsSysConfig.getSysChineseNme())) ohsSysConfig.setSysChineseNme(null);
		if (StringUtils.isEmpty(ohsSysConfig.getSchemaName())) ohsSysConfig.setSchemaName(null);
		ohsSysConfig.setCreateUser(tokenName);
		return sysConfigService.getAllSysInfo(ohsSysConfig);
	}
	
	/**
	 * 保存系统信息
	 */
	@PostMapping("/saveSysConfig")
	@ResponseBody
	public OhsSysConfig saveSysConfig(@RequestBody Map<String, Object> requestParam) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(((String) requestParam.get("sysAlias")).toUpperCase());
		ohsSysConfig.setSysChineseNme(((String) requestParam.get("sysChineseNme")).toUpperCase());
		ohsSysConfig.setSchemaName(((String) requestParam.get("schemaName")).toUpperCase());
		ohsSysConfig.setCreateUser((String) requestParam.get("tokenName"));
		return sysConfigService.saveSysConfig(ohsSysConfig);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public OhsSysConfig deleteById(@PathVariable("id") String id, String tokenName) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setId(Integer.parseInt(id));
		return sysConfigService.deleteById(ohsSysConfig, tokenName);
	}
	
	
	@PutMapping("/updateById")
	@ResponseBody
	public OhsSysConfig updateById(@RequestBody Map<String, Object> requestParam) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setId((Integer) requestParam.get("id"));
		ohsSysConfig.setSysAlias(((String) requestParam.get("sysAlias")).toUpperCase());
		ohsSysConfig.setSysChineseNme(((String) requestParam.get("sysChineseNme")).toUpperCase());
		ohsSysConfig.setSchemaName(((String) requestParam.get("schemaName")).toUpperCase());
		ohsSysConfig.setCreateUser((String) requestParam.get("tokenName"));
		return sysConfigService.updateById(ohsSysConfig);
	}

}
