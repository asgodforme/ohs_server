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
import org.springframework.util.StringUtils;

import com.jiangjie.ohs.dto.Column;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsColumnConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsTableConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsColumnConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.repository.OhsTableConfigRepository;
import com.jiangjie.ohs.service.ColumnConfigService;
import com.jiangjie.ohs.utils.OhsUtils;

@Service
public class ColumnConfigServiceImpl implements ColumnConfigService {
	
	@Autowired
	private OhsTableConfigRepository ohsTableConfigRepository;
	
	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;
	
	@Autowired
	private OhsColumnConfigRepository ohsColumnConfigRepository;
	
	@Override
	public List<Column> getAllColumn(Column column) throws OhsException {
		
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(OhsUtils.putIfNotBlank(column.getSysAlias()));
		ohsSysConfig.setSysChineseNme(OhsUtils.putIfNotBlank(column.getSysChineseNme()));

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("不存在对应系统配置信息，请先添加系统后再查询或新增修改表信息！");
		}
		
		if (!StringUtils.isEmpty(column.getSysAlias()) || !StringUtils.isEmpty(column.getSysChineseNme())) {
			ohsSysConfig = ohsSysConfigLst.get(0);
		}
		
		
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setSchemaName(OhsUtils.putIfNotBlank(column.getSchemaName()));
		ohsTableConfig.setTableName(OhsUtils.putIfNotBlank(column.getTableName()));
		ohsTableConfig.setSysId(ohsSysConfig.getId());
		
		List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(ohsTableConfig));
		
		if (CollectionUtils.isEmpty(ohsTableConfigLst)) {
			throw new OhsException("不存在对应表信息，请先添加表配置信息后在查询新增字段信息！");
		}
		
		List<Column> columnLst = new ArrayList<>();
		OhsColumnConfig ohsColumnConfig = new OhsColumnConfig();
		// 未上送系统相关信息，相当于查询了全部的表信息
		if (ohsSysConfig.getId() == null) {
			for (OhsTableConfig ohsTableCfg : ohsTableConfigLst) {
				ohsColumnConfig.setColumnAlias(OhsUtils.putIfNotBlank(column.getColumnAlias()));
				ohsColumnConfig.setColumnName(OhsUtils.putIfNotBlank(column.getColumnName()));
				ohsColumnConfig.setSysId(ohsTableCfg.getSysId());
				ohsColumnConfig.setTableId(ohsTableCfg.getId());
				List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigRepository.findAll(Example.of(ohsColumnConfig));	
				for (OhsColumnConfig ohsColumnCfg : ohsColumnConfigLst) {
					if (ohsTableCfg.getId().equals(ohsColumnCfg.getTableId()) && ohsTableCfg.getSysId().equals(ohsColumnCfg.getSysId())) {
						Column col = new Column();
						col.setId(ohsColumnCfg.getId());
						col.setColumnAlias(ohsColumnCfg.getColumnAlias());
						col.setColumnName(ohsColumnCfg.getColumnName());
						col.setCreateDate(ohsColumnCfg.getCreateDate());
						col.setCreateUser(ohsColumnCfg.getCreateUser());
						col.setSchemaName(ohsTableCfg.getSchemaName());
						Optional<OhsSysConfig> ohsSysConfigOpt = ohsSysConfigRepository.findById(ohsTableCfg.getSysId());
						if (ohsSysConfigOpt.isPresent()) {
							col.setSysAlias(ohsSysConfigOpt.get().getSysAlias());
							col.setSysChineseNme(ohsSysConfigOpt.get().getSysChineseNme());
						}
						col.setTableName(ohsTableCfg.getTableName());
						col.setUpdateDate(ohsColumnCfg.getUpdateDate());
						col.setUpdateUser(ohsColumnCfg.getUpdateUser());
						columnLst.add(col);
					}
				}
			}
		} else { 
			ohsColumnConfig.setColumnAlias(OhsUtils.putIfNotBlank(column.getColumnAlias()));
			ohsColumnConfig.setColumnName(OhsUtils.putIfNotBlank(column.getColumnName()));
			ohsColumnConfig.setSysId(ohsSysConfig.getId());
			ohsColumnConfig.setTableId(ohsTableConfigLst.get(0).getId());
			List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigRepository.findAll(Example.of(ohsColumnConfig));	
			for (OhsColumnConfig ohsColumnCfg : ohsColumnConfigLst) {
				Column col = new Column();
				col.setId(ohsColumnCfg.getId());
				col.setColumnAlias(ohsColumnCfg.getColumnAlias());
				col.setColumnName(ohsColumnCfg.getColumnName());
				col.setCreateDate(ohsColumnCfg.getCreateDate());
				col.setCreateUser(ohsColumnCfg.getCreateUser());
				col.setSchemaName(ohsTableConfigLst.get(0).getSchemaName());
				col.setSysAlias(ohsSysConfig.getSysAlias());
				col.setSysChineseNme(ohsSysConfig.getSysChineseNme());
				col.setTableName(ohsTableConfigLst.get(0).getTableName());
				col.setUpdateDate(ohsColumnCfg.getUpdateDate());
				col.setUpdateUser(ohsColumnCfg.getUpdateUser());
				columnLst.add(col);
			}
		}
		
		if (CollectionUtils.isEmpty(columnLst)) {
			throw new OhsException("不存在字段信息，请新增！");
		}
		
		return columnLst;
	}

	@Override
	public Column saveColumnConfig(Column column) throws OhsException {
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
		ohsColumnConfig.setCreateDate(new Timestamp(new Date().getTime()));
		ohsColumnConfig.setCreateUser("姜杰");
		ohsColumnConfig.setSysId(ohsTableConfig.getSysId());
		ohsColumnConfig.setTableId(ohsTableConfigLst.get(0).getId());
		
		ohsColumnConfig = ohsColumnConfigRepository.save(ohsColumnConfig);
		column.setCreateDate(ohsColumnConfig.getCreateDate());
		column.setCreateUser(ohsColumnConfig.getCreateUser());
		column.setId(ohsColumnConfig.getId());
		
		return column;
	}

	@Override
	public Column deleteById(int id) throws OhsException  {
		Optional<OhsColumnConfig> ohsColumnConfiOpt = ohsColumnConfigRepository.findById(id);
		if (!ohsColumnConfiOpt.isPresent()) {
			throw new OhsException("该字段信息已被删除！请重新查询！");
		}
		ohsColumnConfigRepository.deleteById(id);
		Column column = new Column();
		column.setId(id);
		return column;
	}

	@Override
	public Column updateById(Column column) throws OhsException  {
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
		ohsColumnConfig.setId(column.getId());
		ohsColumnConfig.setColumnAlias(column.getColumnAlias());
		ohsColumnConfig.setColumnName(column.getColumnName());
		ohsColumnConfig.setUpdateDate(new Timestamp(new Date().getTime()));
		ohsColumnConfig.setUpdateUser("修改者");
		ohsColumnConfig.setCreateDate(ohsTableConfigLst.get(0).getCreateDate());
		ohsColumnConfig.setCreateUser(ohsTableConfigLst.get(0).getCreateUser());
		ohsColumnConfig.setSysId(ohsTableConfig.getSysId());
		ohsColumnConfig.setTableId(ohsTableConfigLst.get(0).getId());
		
		ohsColumnConfig = ohsColumnConfigRepository.save(ohsColumnConfig);
		
		column.setCreateDate(ohsColumnConfig.getCreateDate());
		column.setCreateUser(ohsColumnConfig.getCreateUser());
		column.setUpdateDate(ohsColumnConfig.getUpdateDate());
		column.setUpdateUser(ohsColumnConfig.getUpdateUser());
		return column;
	}

}
