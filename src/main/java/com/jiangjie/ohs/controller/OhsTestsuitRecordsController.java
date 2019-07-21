package com.jiangjie.ohs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
