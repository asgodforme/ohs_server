package com.jiangjie.ohs.service.impl;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.jiangjie.ohs.entity.OhsEnvironmentConfig;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsColumnConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsEnumValueConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsSingleQueryWhereInfo;
import com.jiangjie.ohs.entity.dataEntity.OhsSingleSqlConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsTableConfig;
import com.jiangjie.ohs.repository.OhsColumnConfigRepository;
import com.jiangjie.ohs.repository.OhsEnumValueConfigRepository;
import com.jiangjie.ohs.repository.OhsEvnironmentConfigRepository;
import com.jiangjie.ohs.repository.OhsModuleConfigRepository;
import com.jiangjie.ohs.repository.OhsSingleQueryWhereInfoRepository;
import com.jiangjie.ohs.repository.OhsSingleSqlConfigRepository;
import com.jiangjie.ohs.repository.OhsTableConfigRepository;
import com.jiangjie.ohs.service.OhsDeleteService;

/**
 * 手动级联删除
 * 
 * @author Administrator
 *
 */
@Service
public class OhsDeleteServiceImpl implements OhsDeleteService {

	@Autowired
	private OhsModuleConfigRepository ohsModuleConfigRepository;

	@Autowired
	private OhsEvnironmentConfigRepository ohsEvnironmentConfigRepository;

	@Autowired
	private OhsTableConfigRepository ohsTableConfigRepository;

	@Autowired
	private OhsColumnConfigRepository ohsColumnConfigRepository;

	@Autowired
	private OhsEnumValueConfigRepository ohsEnumValueConfigRepository;
	
	@Autowired
	private OhsSingleSqlConfigRepository ohsSingleSqlConfigRepository;
	
	@Autowired
	private OhsSingleQueryWhereInfoRepository ohsSingleQueryWhereInfoRepository;

	public void deleteAllModule(Consumer<OhsModuleConfig> consumer) {
		// 模块
		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		consumer.accept(ohsModuleConfig);
		ohsModuleConfigRepository.deleteInBatch(ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig)));
	}

	public void deleteAllEvn(Consumer<OhsEnvironmentConfig> consumer) {
		// 环境
		OhsEnvironmentConfig ohsEnvironmentConfig = new OhsEnvironmentConfig();
		consumer.accept(ohsEnvironmentConfig);
		ohsEvnironmentConfigRepository.deleteInBatch(ohsEvnironmentConfigRepository.findAll(Example.of(ohsEnvironmentConfig)));
	}

	public void deleteAllTable(Consumer<OhsTableConfig> consumer) {
		// 表
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		consumer.accept(ohsTableConfig);
		ohsTableConfigRepository.deleteInBatch(ohsTableConfigRepository.findAll(Example.of(ohsTableConfig)));
	}

	public void deleteAllColumns(Consumer<OhsColumnConfig> consumer) {
		// 字段
		OhsColumnConfig ohsColumnConfig = new OhsColumnConfig();
		consumer.accept(ohsColumnConfig);
		List<OhsColumnConfig> ohsColumnCOnfigLst = ohsColumnConfigRepository.findAll(Example.of(ohsColumnConfig));
		ohsColumnCOnfigLst.stream().forEach(column -> {
			// 枚举值
			OhsEnumValueConfig ohsEnumValueConfig = new OhsEnumValueConfig();
			ohsEnumValueConfig.setColumnId(column.getId());
			ohsEnumValueConfigRepository.deleteInBatch(ohsEnumValueConfigRepository.findAll(Example.of(ohsEnumValueConfig)));
		});
		ohsColumnConfigRepository.deleteInBatch(ohsColumnCOnfigLst);
	}

	public void deleteAllSingleSql(Consumer<OhsSingleSqlConfig> consumer) {
		// 单表SQL
		OhsSingleSqlConfig ohsSingleSqlConfig = new OhsSingleSqlConfig();
		consumer.accept(ohsSingleSqlConfig);
		List<OhsSingleSqlConfig> ohsSingleSqlConfigLst = ohsSingleSqlConfigRepository.findAll(Example.of(ohsSingleSqlConfig));
		ohsSingleSqlConfigLst.stream().forEach(ohsSingleSql -> {
			// 查询key
			OhsSingleQueryWhereInfo ohsSingleQueryWhereInfo = new OhsSingleQueryWhereInfo();
			ohsSingleQueryWhereInfo.setSingleSqlId(ohsSingleSql.getId());
			ohsSingleQueryWhereInfoRepository.deleteInBatch(ohsSingleQueryWhereInfoRepository.findAll(Example.of(ohsSingleQueryWhereInfo)));
		});
		ohsSingleSqlConfigRepository.deleteInBatch(ohsSingleSqlConfigLst);
	}
}
