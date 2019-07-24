package com.jiangjie.ohs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.TestsuitRecords;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.OhsTestsuitRecordsService;

@RestController
@RequestMapping("/api/testsuitRecordsConfig")
public class OhsTestsuitRecordsController {

	@Autowired
	private OhsTestsuitRecordsService ohsTestsuitRecordsService;

	@DeleteMapping("/deleteById/{id}")
	public TestsuitRecords deleteById(@PathVariable("id") String id) throws OhsException {
		return ohsTestsuitRecordsService.deleteById(id);
	}

	@PostMapping("/saveTestsuitRecords")
	@ResponseBody
	public TestsuitRecords saveTestsuitRecords(@RequestBody Map<String, Object> requestParam) throws OhsException {
		TestsuitRecords testsuitRecords = new TestsuitRecords();
		testsuitRecords.setId(Integer.parseInt((String) requestParam.get("id")));
		testsuitRecords.setSysAlias((String) requestParam.get("sysAlias"));
		testsuitRecords.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		testsuitRecords.setModuleAlias((String) requestParam.get("moduleAlias"));
		testsuitRecords.setModuleName((String) requestParam.get("moduleName"));
		testsuitRecords.setTestSeq((Integer) requestParam.get("testSeq"));
		testsuitRecords.setInterfaceAlias((String) requestParam.get("interfaceAlias"));
		testsuitRecords.setInterfaceName((String) requestParam.get("interfaceName"));
		return ohsTestsuitRecordsService.saveTestsuitRecords(testsuitRecords);
	}

}
