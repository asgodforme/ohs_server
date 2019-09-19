package com.jiangjie.ohs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.entity.OhsInterfaceSingleRecords;
import com.jiangjie.ohs.service.OhsInterfaceSingleRecordsService;

@RestController
@RequestMapping("/api/interfaceSingleRecords")
public class OhsInterfaceSingleRecordsController {
	
	@Autowired
	private OhsInterfaceSingleRecordsService ohsInterfaceSingleRecordsService;

	@PostMapping("/saveInterfaceSingleRecords")
	@ResponseBody
	public OhsInterfaceSingleRecords saveInterfaceSingleRecords(@RequestBody Map<String, Object> requestParam) {
		return ohsInterfaceSingleRecordsService.saveInterfaceSingleRecords(requestParam);
	}
}
