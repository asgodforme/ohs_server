package com.jiangjie.ohs.controller;

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

import com.jiangjie.ohs.dto.Interface;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.InterfaceConfigService;

@RestController
@RequestMapping("/api/interfaceConfig")
public class OhsInterfaceConfigController {

	@Autowired
	private InterfaceConfigService interfaceConfigService;

	@GetMapping("/getAllInterface")
	public PageResponse<Interface> getAllInterface(Interface interfaceObj) throws OhsException {
		return interfaceConfigService.getAllInterface(interfaceObj);
	}

	@PostMapping("/saveInterfaceConfig")
	@ResponseBody
	public Interface saveInterfaceConfig(@RequestBody Map<String, Object> requestParam) throws OhsException {
		Interface interfaceObj = new Interface();
		interfaceObj.setSysAlias((String) requestParam.get("sysAlias"));
		interfaceObj.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		interfaceObj.setModuleAlias((String) requestParam.get("moduleAlias"));
		interfaceObj.setModuleName((String) requestParam.get("moduleName"));
		interfaceObj.setMethod((String) requestParam.get("method"));
		interfaceObj.setUrlPath((String) requestParam.get("urlPath"));
		interfaceObj.setInterfaceType((String) requestParam.get("interfaceType"));
		interfaceObj.setInterfaceName((String) requestParam.get("interfaceName"));
		interfaceObj.setInterfaceAlias((String) requestParam.get("interfaceAlias"));
		interfaceObj.setRequestTemplate((String) requestParam.get("requestTemplate"));
		interfaceObj.setResponseTemplate((String) requestParam.get("responseTemplate"));
		return interfaceConfigService.saveInterfaceConfig(interfaceObj);
	}

	@DeleteMapping("/deleteById/{id}")
	public Interface deleteById(@PathVariable("id") String id) throws OhsException {
		return interfaceConfigService.deleteById(Integer.parseInt(id));
	}

	@PutMapping("/updateById")
	@ResponseBody
	public Interface updateById(@RequestBody Map<String, Object> requestParam) throws OhsException {
		Interface interfaceObj = new Interface();
		interfaceObj.setId((String) requestParam.get("id"));
		interfaceObj.setSysAlias((String) requestParam.get("sysAlias"));
		interfaceObj.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		interfaceObj.setModuleAlias((String) requestParam.get("moduleAlias"));
		interfaceObj.setModuleName((String) requestParam.get("moduleName"));
		interfaceObj.setMethod((String) requestParam.get("method"));
		interfaceObj.setUrlPath((String) requestParam.get("urlPath"));
		interfaceObj.setInterfaceType((String) requestParam.get("interfaceType"));
		interfaceObj.setInterfaceName((String) requestParam.get("interfaceName"));
		interfaceObj.setInterfaceAlias((String) requestParam.get("interfaceAlias"));
		interfaceObj.setRequestTemplate((String) requestParam.get("requestTemplate"));
		interfaceObj.setResponseTemplate((String) requestParam.get("responseTemplate"));
		return interfaceConfigService.updateById(interfaceObj);
	}

	@PostMapping("/executeInterface")
	@ResponseBody
	public Interface executeInterface(@RequestBody Map<String, Object> requestParam) throws OhsException {
		Interface interfaceObj = new Interface();
		interfaceObj.setTargetServerId(requestParam.get("targetServerId") + "");
		interfaceObj.setId(requestParam.get("id") + "");
		interfaceObj.setSingleRecordsId((Integer) requestParam.get("singleRecordsId"));
		return interfaceConfigService.restfulRequest(interfaceObj);
	}
}
