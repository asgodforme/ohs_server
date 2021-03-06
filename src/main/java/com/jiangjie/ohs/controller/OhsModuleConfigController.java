package com.jiangjie.ohs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.Module;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.ModuleConfigService;

@RestController
@RequestMapping("/api/moduleConfig")
public class OhsModuleConfigController {

	
	@Autowired
	private ModuleConfigService moduleConfigService;
	
	/**
	 * 根据指定的条件查询所有的模块
	 * @param Module
	 * @return
	 * @throws OhsException
	 */
	@GetMapping("/getAllModule")
	public PageResponse<Module> getAllModule(Module module, String tokenName) throws OhsException {
		module.setCreateUser(tokenName);
		return moduleConfigService.getAllModule(module);
	}
	
	@PostMapping("/saveModuleConfig")
	@ResponseBody
	public Module saveModuleConfig(@RequestBody Map<String, Object> requestParam) throws OhsException {
		Module module = new Module();
		module.setSysAlias(((String) requestParam.get("sysAlias")).toUpperCase());
		module.setSysChineseNme(((String) requestParam.get("sysChineseNme")).toUpperCase());
		module.setModuleAlias(((String) requestParam.get("moduleAlias")).toUpperCase());
		module.setModuleName(((String) requestParam.get("moduleName")).toUpperCase());
		module.setCreateUser((String) requestParam.get("tokenName"));
		return moduleConfigService.saveModuleConfig(module);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Module deleteById(@PathVariable("id") String id, String tokenName) throws OhsException {
		return moduleConfigService.deleteById(Integer.parseInt(id), tokenName);
	}
	
	@PutMapping("/updateById")
	@ResponseBody
	public Module updateById(@RequestBody Map<String, Object> requestParam) throws OhsException {
		Module module = new Module();
		module.setId((Integer) requestParam.get("id"));
		module.setSysAlias((String) requestParam.get("sysAlias"));
		module.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		module.setModuleAlias((String) requestParam.get("moduleAlias"));
		module.setModuleName((String) requestParam.get("moduleName")); 
		module.setCreateUser((String) requestParam.get("tokenName"));
		return moduleConfigService.updateById(module);
	}
	
	/**
	 * 通过系统码获取当前系统下的模块名
	 * @param module
	 * @return
	 * @throws OhsException 
	 */
	@GetMapping("/getModuleBySysAlias")
	public List<Module> getModuleBySysAlias(Module module, String tokenName) throws OhsException {
		module.setCreateUser(tokenName);
		return moduleConfigService.getModuleBySysAlias(module);
	}

	/**
	 * 数据查询提交，POST请求通了。
	 * @param requestMap
	 * @return
	 */
	@PostMapping("/querySubmit")
	@ResponseBody
	public Map<String, Object> querySubmit(@RequestBody Map<String, Object> requestMap) {
		System.out.println(requestMap);
		return requestMap;
	}
	
}
