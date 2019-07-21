package com.jiangjie.ohs.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangjie.ohs.dto.TestsuitRecords;
import com.jiangjie.ohs.entity.autoTest.OhsTestsuitRecords;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsTestsuitRecordsRepository;
import com.jiangjie.ohs.service.OhsTestsuitRecordsService;


@Service
public class OhsTestsuitRecordsServiceImpl implements OhsTestsuitRecordsService {

	@Autowired
	private OhsTestsuitRecordsRepository ohsTestsuitRecordsRepository;
	
	@Override
	public TestsuitRecords deleteById(String id) throws OhsException {
		Optional<OhsTestsuitRecords> ohsTestsuitRecordsOpt = ohsTestsuitRecordsRepository.findById(Integer.parseInt(id));
		if (!ohsTestsuitRecordsOpt.isPresent()) {
			throw new OhsException("当前接口不存在！");
		}
		ohsTestsuitRecordsRepository.deleteById(Integer.parseInt(id));
		TestsuitRecords testsuitRecords = new TestsuitRecords();
		testsuitRecords.setId(id);
		return testsuitRecords;
	}
	
	

}
