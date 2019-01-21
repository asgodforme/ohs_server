package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jiangjie.ohs.dto.Table;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsTableConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.repository.OhsTableConfigRepository;
import com.jiangjie.ohs.service.OhsDeleteService;
import com.jiangjie.ohs.service.TableConfigService;
import com.jiangjie.ohs.utils.OhsUtils;

@Service
public class TableConfigServiceImpl implements TableConfigService {
	
	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;
	
	@Autowired
	private OhsTableConfigRepository ohsTableConfigRepository;
	
	@Autowired
	private OhsDeleteService ohsDeleteService;
	
	private static final Function<Table, OhsTableConfig> toOhsTableConfig = table -> {
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setCreateDate(new Timestamp(new Date().getTime()));
		ohsTableConfig.setCreateUser("姜杰");
		ohsTableConfig.setSchemaName(table.getSchemaName());
		ohsTableConfig.setTableName(table.getTableName());
		ohsTableConfig.setTableChnName(table.getTableChnName());
		return ohsTableConfig;
	};
	
	private static final Function<OhsTableConfig, Table> toTable = ohsTableConfig -> {
		Table table = new Table();
		table.setId(ohsTableConfig.getId());
		table.setSchemaName(ohsTableConfig.getSchemaName());
		table.setTableName(ohsTableConfig.getTableName());
		table.setCreateDate(ohsTableConfig.getCreateDate());
		table.setCreateUser(ohsTableConfig.getCreateUser());
		table.setUpdateDate(ohsTableConfig.getUpdateDate());
		table.setUpdateUser(ohsTableConfig.getUpdateUser());
		table.setTableChnName(ohsTableConfig.getTableChnName());
		return table;
	};

	@Override
	public List<Table> getAllTable(Table table) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(StringUtils.isEmpty(table.getSysAlias()) ? null : table.getSysAlias());
		ohsSysConfig.setSysChineseNme(StringUtils.isEmpty(table.getSysChineseNme()) ? null : table.getSysChineseNme());

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("不存在对应系统配置信息，请先添加系统后再查询或新增修改表信息！");
		}
		
		if (!StringUtils.isEmpty(table.getSysAlias()) || !StringUtils.isEmpty(table.getSysChineseNme())) {
			ohsSysConfig = ohsSysConfigLst.get(0);
		}
		
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setSchemaName(OhsUtils.putIfNotBlank(table.getSchemaName()));
		ohsTableConfig.setTableName(OhsUtils.putIfNotBlank(table.getTableName()));
		ohsTableConfig.setTableChnName(OhsUtils.putIfNotBlank(table.getTableChnName()));
		ohsTableConfig.setSysId(ohsSysConfig.getId());
		
		List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(ohsTableConfig));
		
		if (CollectionUtils.isEmpty(ohsTableConfigLst)) {
			throw new OhsException("不存在表配置信息，请新增！");
		}
		
		List<Table> tables = new ArrayList<>();
		
		// 未输入系统相关信息去查询，需要遍历查询系统信息
		if (ohsSysConfig.getId() == null) {
			ohsTableConfigLst.stream().forEach(ohsTableCfg -> {
				Table tmpTable = toTable.apply(ohsTableCfg);
				Optional<OhsSysConfig> ohsSysConfigOpt = ohsSysConfigRepository.findById(ohsTableCfg.getSysId());
				if (ohsSysConfigOpt.isPresent()) {
					tmpTable.setSysAlias(ohsSysConfigOpt.get().getSysAlias());
					tmpTable.setSysChineseNme(ohsSysConfigOpt.get().getSysChineseNme());
				}
				tables.add(tmpTable);
			});
		} else {
			ohsTableConfigLst.stream().forEach(ohsTableCfg -> tables.add(toTable.apply(ohsTableCfg)));
			String sysAlias = ohsSysConfig.getSysAlias();
			String sysChineseNme = ohsSysConfig.getSysChineseNme();
			tables.stream().forEach(tab -> {
				tab.setSysAlias(sysAlias);
				tab.setSysChineseNme(sysChineseNme);
			});
		}
		return tables;
	}

	@Override
	public Table saveTableConfig(Table table) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(OhsUtils.putIfNotBlank(table.getSysAlias()));
		ohsSysConfig.setSysChineseNme(OhsUtils.putIfNotBlank(table.getSysChineseNme()));

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("不存在对应系统配置信息，请先添加系统后再查询或新增修改表信息！");
		}
		
		OhsTableConfig ohsTableConfig = toOhsTableConfig.apply(table);
		ohsTableConfig.setSysId(ohsSysConfigLst.get(0).getId());
		
		ohsTableConfig = ohsTableConfigRepository.save(ohsTableConfig);
		
		table.setId(ohsTableConfig.getId());
		table.setCreateDate(ohsTableConfig.getCreateDate());
		table.setCreateUser(ohsTableConfig.getCreateUser());
		
		return table;
	}

	@Override
	public Table deleteById(int id) throws OhsException {
		Optional<OhsTableConfig> ohsTableConfigOpt = ohsTableConfigRepository.findById(id);
		if (!ohsTableConfigOpt.isPresent()) {
//			throw new OhsException("该表信息已经被删除，请重新查询！");
			Table table = new Table();
			table.setId(id);
			return table;
		}
		// 手动级联删除
		ohsDeleteService.deleteAllColumns(column -> column.setTableId(ohsTableConfigOpt.get().getId()));
		ohsDeleteService.deleteAllSingleSql(singleSql -> singleSql.setTableId(ohsTableConfigOpt.get().getId()));
		ohsTableConfigRepository.deleteById(id);
		Table table = new Table();
		table.setId(id);
		return table;
	}

	@Override
	public Table updateById(Table table) throws OhsException {
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(OhsUtils.putIfNotBlank(table.getSysAlias()));
		ohsSysConfig.setSysChineseNme(OhsUtils.putIfNotBlank(table.getSysChineseNme()));

		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("不存在对应系统配置信息，请先添加系统后再查询或新增修改表信息！");
		}
		
		Optional<OhsTableConfig> ohsTableConfigOpt = ohsTableConfigRepository.findById(table.getId());
		if (!ohsTableConfigOpt.isPresent()) {
			throw new OhsException("该表信息已经被删除，请重新查询！");
		}
		
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setId(table.getId());
		ohsTableConfig.setSysId(ohsSysConfigLst.get(0).getId());
		ohsTableConfig.setSchemaName(table.getSchemaName());
		ohsTableConfig.setTableName(table.getTableName());
		ohsTableConfig.setUpdateDate(new Timestamp(new Date().getTime()));
		ohsTableConfig.setUpdateUser("修改者");
		ohsTableConfig.setCreateDate(ohsTableConfigOpt.get().getCreateDate());
		ohsTableConfig.setCreateUser(ohsTableConfigOpt.get().getCreateUser());
		ohsTableConfig.setTableChnName(table.getTableChnName());
		
		ohsTableConfig = ohsTableConfigRepository.save(ohsTableConfig);
		
		table.setUpdateDate(ohsTableConfig.getUpdateDate());
		table.setUpdateUser(ohsTableConfig.getUpdateUser());
		table.setCreateDate(ohsTableConfig.getCreateDate());
		table.setCreateUser(ohsTableConfig.getCreateUser());
		
		return table;
	}

}
