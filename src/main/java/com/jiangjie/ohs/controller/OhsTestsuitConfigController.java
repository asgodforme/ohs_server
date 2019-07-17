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

import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.Testsuit;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.TestsuitConfigService;


@RestController
@RequestMapping("/api/testsuitConfig")
public class OhsTestsuitConfigController {

	
	@Autowired
	private TestsuitConfigService testsuitConfigService;
	
	@GetMapping("/getAllTestsuit")
	public PageResponse<Testsuit> getAllTestsuit(Testsuit testsuitObj) throws OhsException {
		return testsuitConfigService.getAllTestsuit(testsuitObj);
	}
	
	@PostMapping("/saveTestsuitConfig")
	@ResponseBody
	public Testsuit saveTestsuitConfig(@RequestBody Map<String, Object> requestParam) throws OhsException {
		Testsuit testsuitObj = new Testsuit();
		testsuitObj.setSysAlias((String) requestParam.get("sysAlias"));
		testsuitObj.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		testsuitObj.setModuleAlias((String) requestParam.get("moduleAlias"));
		testsuitObj.setModuleName((String) requestParam.get("moduleName"));
		return testsuitConfigService.saveTestsuitConfig(testsuitObj);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Testsuit deleteById(@PathVariable("id") String id) throws OhsException {
		return testsuitConfigService.deleteById(Integer.parseInt(id));
	}
	

	@PutMapping("/updateById")
	@ResponseBody
	public Testsuit updateById(@RequestBody Map<String, Object> requestParam) throws OhsException {
		Testsuit testsuitObj = new Testsuit();
		testsuitObj.setId((String) requestParam.get("id"));
		testsuitObj.setSysAlias((String) requestParam.get("sysAlias"));
		testsuitObj.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		testsuitObj.setModuleAlias((String) requestParam.get("moduleAlias"));
		testsuitObj.setModuleName((String) requestParam.get("moduleName"));
		return testsuitConfigService.updateById(testsuitObj);
	}
}
