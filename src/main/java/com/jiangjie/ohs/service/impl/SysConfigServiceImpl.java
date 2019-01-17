package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jiangjie.ohs.dto.Column;
import com.jiangjie.ohs.dto.SysInfo;
import com.jiangjie.ohs.dto.Table;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsColumnConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsTableConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsColumnConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.repository.OhsTableConfigRepository;
import com.jiangjie.ohs.service.SysConfigService;

/**
 * 系统配置服务实现类
 * @author Administrator
 *
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

	@Autowired
	private OhsSysConfigRepository sysConfigRepository;
	
	@Autowired
	private OhsTableConfigRepository ohsTableConfigRepository;
	
	@Autowired
	private OhsColumnConfigRepository columnConfigRepository;
	
	private static final Function<OhsSysConfig, SysInfo> toSysInfo = ohsSysCfg -> {
		SysInfo sysInfo = new SysInfo();
		sysInfo.setId(ohsSysCfg.getId());
		sysInfo.setSysAlias(ohsSysCfg.getSysAlias());
		sysInfo.setSysChineseNme(ohsSysCfg.getSysChineseNme());
		sysInfo.setSchemaName(ohsSysCfg.getSchemaName());
		sysInfo.setCreateDate(ohsSysCfg.getCreateDate());
		sysInfo.setCreateUser(ohsSysCfg.getCreateUser());
		sysInfo.setUpdateDate(ohsSysCfg.getUpdateDate());
		sysInfo.setUpdateUser(ohsSysCfg.getUpdateUser());
		return sysInfo;
	};
	
	private static final Function<OhsTableConfig, Table> toTable = ohsTableCfg -> {
		Table table = new Table();
		table.setId(ohsTableCfg.getId());
		table.setSysId(ohsTableCfg.getSysId());
		table.setSchemaName(ohsTableCfg.getSchemaName());
		table.setTableName(ohsTableCfg.getTableName());
		table.setCreateDate(ohsTableCfg.getCreateDate());
		table.setCreateUser(ohsTableCfg.getCreateUser());
		table.setUpdateDate(ohsTableCfg.getUpdateDate());
		table.setUpdateUser(ohsTableCfg.getUpdateUser());
		return table;
	};
	
	private static final Function<OhsColumnConfig, Column> toColumn = ohsColumnConfig -> {
		Column column = new Column();
		column.setId(ohsColumnConfig.getId());
		column.setSysId(ohsColumnConfig.getSysId());
		column.setTableId(ohsColumnConfig.getTableId());
		column.setColumnName(ohsColumnConfig.getColumnName());
		column.setColumnAlias(ohsColumnConfig.getColumnAlias());
		column.setCreateDate(ohsColumnConfig.getCreateDate());
		column.setCreateUser(ohsColumnConfig.getCreateUser());
		column.setUpdateDate(ohsColumnConfig.getUpdateDate());
		column.setUpdateUser(ohsColumnConfig.getUpdateUser());
		return column;
	};
	
	public List<OhsSysConfig> getAllSys(OhsSysConfig ohsSysConfig) {
		Example<OhsSysConfig> example = Example.of(ohsSysConfig);
		return sysConfigRepository.findAll(example);
	}
	
	public List<SysInfo> getAllSysInfo(OhsSysConfig ohsSysConfig) {
		Example<OhsSysConfig> example = Example.of(ohsSysConfig);
		List<OhsSysConfig> OhsSysConfigLst = sysConfigRepository.findAll(example);
		List<SysInfo> sysInfoLst = new ArrayList<SysInfo>();
		if (!CollectionUtils.isEmpty(OhsSysConfigLst)) {
			OhsSysConfigLst.stream().forEach(ohsSysCfg -> {
				SysInfo sysInfo = toSysInfo.apply(ohsSysCfg);
				OhsTableConfig qryOhsTableCfg = new OhsTableConfig();
				qryOhsTableCfg.setSysId(ohsSysCfg.getId());
				qryOhsTableCfg.setSchemaName(ohsSysCfg.getSchemaName());
				List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(qryOhsTableCfg));
				List<Table> tableLst = new ArrayList<>();
				if (!CollectionUtils.isEmpty(ohsTableConfigLst)) {
					tableLst = ohsTableConfigLst.stream().map(toTable::apply).collect(Collectors.toList());;
					tableLst.stream().forEach(table -> {
						OhsColumnConfig ohsColumnConfig = new OhsColumnConfig();
						ohsColumnConfig.setSysId(table.getSysId());
						ohsColumnConfig.setTableId(table.getId());
						List<OhsColumnConfig> ohsColumnConfigLst = columnConfigRepository.findAll(Example.of(ohsColumnConfig));
						List<Column> columnLst = ohsColumnConfigLst.stream().map(toColumn::apply).collect(Collectors.toList());;
						table.setColumns(columnLst);
					});
				}
				sysInfo.setOhsTableConfigs(tableLst);
				sysInfoLst.add(sysInfo);
			});
		}
		return sysInfoLst;
	}
	
	@Override
	public OhsSysConfig saveSysConfig(OhsSysConfig ohsSysConfig) throws OhsException {

		if (!CollectionUtils.isEmpty(sysConfigRepository.findAll(Example.of(ohsSysConfig)))) {
			throw new OhsException("该系统已经存在！");
		}
		ohsSysConfig.setCreateDate(new Timestamp(new Date().getTime()));
		ohsSysConfig.setCreateUser("姜杰");
		return sysConfigRepository.save(ohsSysConfig);
	}

	@Override
	public OhsSysConfig deleteById(OhsSysConfig ohsSysConfig) throws OhsException {
		Optional<OhsSysConfig> ohsSysConfigOpt = sysConfigRepository.findById(ohsSysConfig.getId());
		if (!ohsSysConfigOpt.isPresent()) {
			throw new OhsException("该系统已被删除！");
		}
		ohsSysConfig.setCreateDate(ohsSysConfigOpt.get().getCreateDate());
		ohsSysConfig.setCreateUser(ohsSysConfigOpt.get().getCreateUser());
		sysConfigRepository.deleteById(ohsSysConfig.getId());
		return ohsSysConfigOpt.get();
	}

	@Override
	public OhsSysConfig updateById(OhsSysConfig ohsSysConfig) throws OhsException {
		Optional<OhsSysConfig> ohsSysConfigOpt = sysConfigRepository.findById(ohsSysConfig.getId());
		if (!ohsSysConfigOpt.isPresent()) {
			throw new OhsException("该系统不存在！");
		}
		ohsSysConfig.setCreateDate(ohsSysConfigOpt.get().getCreateDate());
		ohsSysConfig.setCreateUser(ohsSysConfigOpt.get().getCreateUser());
		ohsSysConfig.setUpdateDate(new Timestamp(new Date().getTime()));
		ohsSysConfig.setUpdateUser("修改者");
		return sysConfigRepository.save(ohsSysConfig);
	}
}
