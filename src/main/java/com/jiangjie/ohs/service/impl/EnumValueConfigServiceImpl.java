package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jiangjie.ohs.dto.Column;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsColumnConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsEnumValueConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsTableConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsColumnConfigRepository;
import com.jiangjie.ohs.repository.OhsEnumValueConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.repository.OhsTableConfigRepository;
import com.jiangjie.ohs.service.ColumnConfigService;
import com.jiangjie.ohs.service.EnumValueConfigService;
import com.jiangjie.ohs.utils.OhsUtils;

@Service
public class EnumValueConfigServiceImpl implements EnumValueConfigService {
	
	@Autowired
	private ColumnConfigService columnConfigService;
	
	@Autowired
	private OhsEnumValueConfigRepository ohsEnumValueConfigRepository;
	
	@Autowired
	private OhsTableConfigRepository ohsTableConfigRepository;

	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;
	
	@Autowired
	private OhsColumnConfigRepository ohsColumnConfigRepository;
	
	@Override
	public List<Column> getAllEnumValue(Column column) throws OhsException {
		OhsEnumValueConfig ohsEnumValueConfig = new OhsEnumValueConfig();
		ohsEnumValueConfig.setEnumValue(OhsUtils.putIfNotBlank(column.getEnumValue()));
		ohsEnumValueConfig.setEnumChineseValue(OhsUtils.putIfNotBlank(column.getEnumChineseValue()));
		
		List<OhsEnumValueConfig> ohsEnumValueConfigLst = ohsEnumValueConfigRepository.findAll(Example.of(ohsEnumValueConfig));
		if (CollectionUtils.isEmpty(ohsEnumValueConfigLst)) {
			throw new OhsException("当前枚举值配置信息为空！请先添加后再查询！");
		}
		List<Column> columns = columnConfigService.getAllColumn(column);
		List<Column> returnCol = new ArrayList<>();
		for (OhsEnumValueConfig ohsEnumValueCfg : ohsEnumValueConfigLst) {
			Optional<Column> matchColOpt = columns.stream().filter(col -> col.getId() == ohsEnumValueCfg.getColumnId().intValue()).findFirst();
			if (matchColOpt.isPresent()) {
				Column temCol = matchColOpt.get();
				temCol.setId(ohsEnumValueCfg.getId());
				temCol.setEnumValue(ohsEnumValueCfg.getEnumValue());
				temCol.setEnumChineseValue(ohsEnumValueCfg.getEnumChineseValue());
				temCol.setCreateDate(ohsEnumValueCfg.getCreateDate());
				temCol.setCreateUser(ohsEnumValueCfg.getCreateUser());
				temCol.setUpdateDate(ohsEnumValueCfg.getUpdateDate());
				temCol.setUpdateUser(ohsEnumValueCfg.getUpdateUser());
				returnCol.add(temCol);
			}
		}
		if (CollectionUtils.isEmpty(returnCol)) {
			throw new OhsException("不存在枚举值，请新增！");
		}
		return returnCol;
	}

	@Override
	public Column saveEnumValueConfig(Column column) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(OhsUtils.putIfNotBlank(column.getSysAlias()));
		ohsSysConfig.setSysChineseNme(OhsUtils.putIfNotBlank(column.getSysChineseNme()));

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("不存在对应系统配置信息，请先添加系统后再查询或新增修改表信息！");
		}
		
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setSchemaName(OhsUtils.putIfNotBlank(column.getSchemaName()));
		ohsTableConfig.setTableName(OhsUtils.putIfNotBlank(column.getTableName()));
		ohsTableConfig.setSysId(ohsSysConfigLst.get(0).getId());
		
		List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(ohsTableConfig));
		
		if (CollectionUtils.isEmpty(ohsTableConfigLst)) {
			throw new OhsException("不存在对应表信息，请先添加表配置信息后在查询新增字段信息！");
		}
		
		OhsColumnConfig ohsColumnConfig = new OhsColumnConfig();
		ohsColumnConfig.setColumnAlias(column.getColumnAlias());
		ohsColumnConfig.setColumnName(column.getColumnName());
		ohsColumnConfig.setSysId(ohsTableConfig.getSysId());
		ohsColumnConfig.setTableId(ohsTableConfigLst.get(0).getId());
		
		List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigRepository.findAll(Example.of(ohsColumnConfig));
		if (CollectionUtils.isEmpty(ohsColumnConfigLst)) {
			throw new OhsException("不存在对应字段信息，请先添加字段配置信息后在查询新增枚举值信息！");
		}
		
		OhsEnumValueConfig ohsEnumValueConfig = new OhsEnumValueConfig();
		ohsEnumValueConfig.setEnumChineseValue(column.getEnumChineseValue());
		ohsEnumValueConfig.setEnumValue(column.getEnumValue());
		ohsEnumValueConfig.setColumnId(ohsColumnConfigLst.get(0).getId());
		ohsEnumValueConfig.setCreateDate(new Timestamp(new Date().getTime()));
		ohsEnumValueConfig.setCreateUser("姜杰");
		
		ohsEnumValueConfig = ohsEnumValueConfigRepository.save(ohsEnumValueConfig);
		
		column.setId(ohsEnumValueConfig.getId());
		column.setColumnAlias(ohsColumnConfigLst.get(0).getColumnAlias());
		column.setColumnName(ohsColumnConfigLst.get(0).getColumnName());
		column.setCreateDate(ohsEnumValueConfig.getCreateDate());
		column.setCreateUser(ohsEnumValueConfig.getCreateUser());
		return column;
	}

	@Override
	public Column deleteById(int id) throws OhsException {
		Optional<OhsEnumValueConfig> ohsEnumValueConfigOpt = ohsEnumValueConfigRepository.findById(id);
		if (!ohsEnumValueConfigOpt.isPresent()) {
			throw new OhsException("该枚举值信息已经被删除！");
		}
		ohsEnumValueConfigRepository.deleteById(id);
		Column column = new Column();
		column.setId(id);
		return column;
	}

	@Override
	public Column updateById(Column column) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(OhsUtils.putIfNotBlank(column.getSysAlias()));
		ohsSysConfig.setSysChineseNme(OhsUtils.putIfNotBlank(column.getSysChineseNme()));

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("不存在对应系统配置信息，请先添加系统后再修改表信息！");
		}
		
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setSchemaName(OhsUtils.putIfNotBlank(column.getSchemaName()));
		ohsTableConfig.setTableName(OhsUtils.putIfNotBlank(column.getTableName()));
		ohsTableConfig.setSysId(ohsSysConfigLst.get(0).getId());
		
		List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(ohsTableConfig));
		
		if (CollectionUtils.isEmpty(ohsTableConfigLst)) {
			throw new OhsException("不存在对应表信息，请先添加表配置信息后再修改字段信息！");
		}
		
		OhsColumnConfig ohsColumnConfig = new OhsColumnConfig();
		ohsColumnConfig.setColumnAlias(column.getColumnAlias());
		ohsColumnConfig.setColumnName(column.getColumnName());
		ohsColumnConfig.setSysId(ohsTableConfig.getSysId());
		ohsColumnConfig.setTableId(ohsTableConfigLst.get(0).getId());
		
		List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigRepository.findAll(Example.of(ohsColumnConfig));
		if (CollectionUtils.isEmpty(ohsColumnConfigLst)) {
			throw new OhsException("不存在对应字段信息，请先添加字段配置信息后再修改枚举值信息！");
		}
		
		Optional<OhsEnumValueConfig> ohsEnumValueConfigOpt = ohsEnumValueConfigRepository.findById(column.getId());
		if (!ohsEnumValueConfigOpt.isPresent()) {
			throw new OhsException("该枚举值不存在！");
		}
		OhsEnumValueConfig ohsEnumValueConfig = new OhsEnumValueConfig();
		ohsEnumValueConfig.setId(column.getId());
		ohsEnumValueConfig.setEnumChineseValue(column.getEnumChineseValue());
		ohsEnumValueConfig.setEnumValue(column.getEnumValue());
		ohsEnumValueConfig.setColumnId(ohsColumnConfigLst.get(0).getId());
		ohsEnumValueConfig.setCreateDate(ohsEnumValueConfigOpt.get().getCreateDate());
		ohsEnumValueConfig.setCreateUser(ohsEnumValueConfigOpt.get().getCreateUser());
		ohsEnumValueConfig.setUpdateDate(new Timestamp(new Date().getTime()));
		ohsEnumValueConfig.setUpdateUser("修改者");
		
		ohsEnumValueConfig = ohsEnumValueConfigRepository.save(ohsEnumValueConfig);
		
		column.setId(ohsEnumValueConfig.getId());
		column.setCreateDate(ohsEnumValueConfig.getCreateDate());
		column.setCreateUser(ohsEnumValueConfig.getCreateUser());
		column.setUpdateDate(ohsEnumValueConfig.getUpdateDate());
		column.setUpdateUser(ohsEnumValueConfig.getUpdateUser());
		
		return column;
	}

}
