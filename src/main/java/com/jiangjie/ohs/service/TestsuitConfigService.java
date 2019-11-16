package com.jiangjie.ohs.service;

import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.Testsuit;
import com.jiangjie.ohs.exception.OhsException;

public interface TestsuitConfigService {

	PageResponse<Testsuit> getAllTestsuit(Testsuit testsuitObj) throws OhsException;

	Testsuit saveTestsuitConfig(Testsuit testsuitObj) throws OhsException;

	Testsuit deleteById(int parseInt, String tokenName) throws OhsException;

	Testsuit updateById(Testsuit testsuitObj) throws OhsException;

}
