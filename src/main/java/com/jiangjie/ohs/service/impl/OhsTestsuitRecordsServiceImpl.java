package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jiangjie.ohs.dto.TestsuitRecords;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.autoTest.OhsInterfaceConfig;
import com.jiangjie.ohs.entity.autoTest.OhsTestsuitRecords;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsInterfaceConfigRepository;
import com.jiangjie.ohs.repository.OhsModuleConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.repository.OhsTestsuitRecordsRepository;
import com.jiangjie.ohs.service.OhsTestsuitRecordsService;
import com.jiangjie.ohs.utils.OhsUtils;


@Service
public class OhsTestsuitRecordsServiceImpl implements OhsTestsuitRecordsService {

	@Autowired
	private OhsTestsuitRecordsRepository ohsTestsuitRecordsRepository;
	
	@Autowired
	private OhsModuleConfigRepository ohsModuleConfigRepository;
	
	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;
	
	@Autowired
	private OhsInterfaceConfigRepository ohsInterfaceConfigRepository;
	
	@Override
	public TestsuitRecords deleteById(String id) throws OhsException {
		Optional<OhsTestsuitRecords> ohsTestsuitRecordsOpt = ohsTestsuitRecordsRepository.findById(Integer.parseInt(id));
		if (!ohsTestsuitRecordsOpt.isPresent()) {
			throw new OhsException("当前接口不存在！");
		}
		
		Optional<OhsInterfaceConfig> ohsInterfaceConfigOpt = ohsInterfaceConfigRepository.findById(ohsTestsuitRecordsOpt.get().getInterfaceId());
		if (!ohsInterfaceConfigOpt.isPresent()) {
			throw new OhsException("当前接口不存在！");
		}
		
		OhsInterfaceConfig ohsInterfaceConfig = ohsInterfaceConfigOpt.get();
		ohsTestsuitRecordsRepository.deleteById(Integer.parseInt(id));
		TestsuitRecords testsuitRecords = new TestsuitRecords();
		testsuitRecords.setId(Integer.parseInt(id));
		testsuitRecords.setTestsuitId(ohsTestsuitRecordsOpt.get().getTestsuitId());
		testsuitRecords.setInterfaceAlias(ohsInterfaceConfig.getInterfaceAlias());
		testsuitRecords.setInterfaceName(ohsInterfaceConfig.getInterfaceName());
		return testsuitRecords;
	}

	@Override
	public TestsuitRecords saveTestsuitRecords(TestsuitRecords testsuitRecords) throws OhsException {
		// 校验系统信息是否有
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(OhsUtils.putIfNotBlank(testsuitRecords.getSysAlias()));
		ohsSysConfig.setSysChineseNme(OhsUtils.putIfNotBlank(testsuitRecords.getSysChineseNme()));
		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("当前系统中不存在系统配置信息，请先在“公共参数配置-系统配置”中配置系统信息！");
		}

		// 校验模块信息是否有
		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(OhsUtils.putIfNotBlank(testsuitRecords.getModuleAlias()));
		ohsModuleConfig.setModuleName(OhsUtils.putIfNotBlank(testsuitRecords.getModuleName()));
		List<OhsModuleConfig> ohsModuleConfigLst = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigLst)) {
			throw new OhsException("当前系统中不存在模块配置信息，请先在“公共参数配置-模块配置”中配置模块信息！");
		}
		
		OhsInterfaceConfig ohsInterfaceConfig = new OhsInterfaceConfig();
		ohsInterfaceConfig.setInterfaceAlias(testsuitRecords.getInterfaceAlias());
		ohsInterfaceConfig.setInterfaceName(testsuitRecords.getInterfaceName());
		
		List<OhsInterfaceConfig> ohsInterfaceConfigLst = ohsInterfaceConfigRepository.findAll(Example.of(ohsInterfaceConfig));
		if (CollectionUtils.isEmpty(ohsInterfaceConfigLst)) {
			throw new OhsException("当前接口不存在！");
		}
		
		OhsInterfaceConfig ohsIntefaceConfig = ohsInterfaceConfigLst.get(0);
		
		OhsTestsuitRecords ohsTestsuitRecords = new OhsTestsuitRecords();
		ohsTestsuitRecords.setInterfaceId(ohsIntefaceConfig.getId());
		ohsTestsuitRecords.setTestsuitId(testsuitRecords.getId());
		ohsTestsuitRecords.setTestSeq(testsuitRecords.getTestSeq());
		ohsTestsuitRecords.setCreateDate(new Timestamp(new Date().getTime()));
		ohsTestsuitRecords.setCreateUser("admin");
		ohsTestsuitRecords = ohsTestsuitRecordsRepository.save(ohsTestsuitRecords);
		
		testsuitRecords.setId(ohsTestsuitRecords.getId());
		testsuitRecords.setTestsuitId(ohsTestsuitRecords.getTestsuitId());
		
		return testsuitRecords;
	}
	
	

}
