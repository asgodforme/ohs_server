package com.jiangjie.ohs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public PageResponse<Module> getAllModule(Module Module) throws OhsException {
		return moduleConfigService.getAllModule(Module);
	}
	
	/**
	 * TODO POST传值报错！！！麻蛋
	 */
	@GetMapping("/saveModuleConfig")
	public Module saveModuleConfig(Module module) throws OhsException {
		return moduleConfigService.saveModuleConfig(module);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Module deleteById(@PathVariable("id") String id) throws OhsException {
		return moduleConfigService.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * TODO PUT传值报错！！！麻蛋
	 * @param ohsSysConfig
	 * @return 
	 * @throws OhsException 
	 */
	@GetMapping("/updateById")
	public Module updateById(Module module) throws OhsException {
		return moduleConfigService.updateById(module);
	}
	
	/**
	 * 通过系统码获取当前系统下的模块名
	 * @param module
	 * @return
	 * @throws OhsException 
	 */
	@GetMapping("/getModuleBySysAlias")
	public List<Module> getModuleBySysAlias(Module module) throws OhsException {
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
