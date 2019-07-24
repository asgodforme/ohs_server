package com.jiangjie.ohs.service;

import com.jiangjie.ohs.dto.TestsuitRecords;
import com.jiangjie.ohs.exception.OhsException;

public interface OhsTestsuitRecordsService {

	TestsuitRecords deleteById(String id) throws OhsException;

	TestsuitRecords saveTestsuitRecords(TestsuitRecords testsuitRecords) throws OhsException;


}
