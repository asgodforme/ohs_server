package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jiangjie.ohs.dto.ColumnDTO;
import com.jiangjie.ohs.dto.PageResponse;
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
	public PageResponse<ColumnDTO> getAllColumn(ColumnDTO column) throws OhsException {

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
		ohsTableConfig.setTableChnName(OhsUtils.putIfNotBlank(column.getTableChnName()));
		ohsTableConfig.setSysId(ohsSysConfig.getId());

		List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(ohsTableConfig));

		if (CollectionUtils.isEmpty(ohsTableConfigLst)) {
			throw new OhsException("不存在对应表信息，请先添加表配置信息后在查询新增字段信息！");
		}

		List<ColumnDTO> columnLst = new ArrayList<>();
		OhsColumnConfig ohsColumnConfig = new OhsColumnConfig();
		Pageable pageable = PageRequest.of(column.getCurrent() - 1 < 0 ? 0 : column.getCurrent() - 1,
				column.getPageSize());
		Page<OhsColumnConfig> ohsColumnConfigPage = null;
		// 未上送系统相关信息，相当于查询了全部的表信息
		if (ohsSysConfig.getId() == null) {
			// 先去分页查询字段信息，然后，从字段信息中筛选出符合表信息和系统信息的字段信息
			ohsColumnConfig.setColumnAlias(OhsUtils.putIfNotBlank(column.getColumnAlias()));
			ohsColumnConfig.setColumnName(OhsUtils.putIfNotBlank(column.getColumnName()));
			ohsColumnConfig.setIsHide(OhsUtils.putIfNotBlank(column.getIsHide()));
			ohsColumnConfigPage = ohsColumnConfigRepository.findAll(Example.of(ohsColumnConfig), pageable);
			List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigPage.getContent();
			for (OhsColumnConfig ohsColumnCfg : ohsColumnConfigLst) {
				for (OhsTableConfig ohsTableCfg : ohsTableConfigLst) {
					// 当前字段信息的表和系统信息符合
					if (ohsTableCfg.getId().equals(ohsColumnCfg.getTableId())
							&& ohsTableCfg.getSysId().equals(ohsColumnCfg.getSysId())) {
						ColumnDTO col = new ColumnDTO();
						col.setId(ohsColumnCfg.getId());
						col.setColumnAlias(ohsColumnCfg.getColumnAlias());
						col.setColumnName(ohsColumnCfg.getColumnName());
						col.setCreateDate(ohsColumnCfg.getCreateDate());
						col.setCreateUser(ohsColumnCfg.getCreateUser());
						col.setSchemaName(ohsTableCfg.getSchemaName());
						col.setIsHide(ohsColumnCfg.getIsHide());
						Optional<OhsSysConfig> ohsSysConfigOpt = ohsSysConfigRepository
								.findById(ohsTableCfg.getSysId());
						if (ohsSysConfigOpt.isPresent()) {
							col.setSysAlias(ohsSysConfigOpt.get().getSysAlias());
							col.setSysChineseNme(ohsSysConfigOpt.get().getSysChineseNme());
						}
						col.setTableName(ohsTableCfg.getTableName());
						col.setTableChnName(ohsTableCfg.getTableChnName());
						col.setUpdateDate(ohsColumnCfg.getUpdateDate());
						col.setUpdateUser(ohsColumnCfg.getUpdateUser());
						columnLst.add(col);
					}
				}
			}
		} else {
			ohsColumnConfig.setColumnAlias(OhsUtils.putIfNotBlank(column.getColumnAlias()));
			ohsColumnConfig.setColumnName(OhsUtils.putIfNotBlank(column.getColumnName()));
			ohsColumnConfig.setIsHide(OhsUtils.putIfNotBlank(column.getIsHide()));
			ohsColumnConfig.setSysId(ohsSysConfig.getId());
			ohsColumnConfigPage = ohsColumnConfigRepository.findAll(Example.of(ohsColumnConfig), pageable);
			List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigPage.getContent();
			if (!CollectionUtils.isEmpty(ohsColumnConfigLst)) {
				for (OhsColumnConfig ohsColumnCfg : ohsColumnConfigLst) {
					for (OhsTableConfig ohsTableCfg : ohsTableConfigLst) {
						ColumnDTO col = new ColumnDTO();
						col.setId(ohsColumnCfg.getId());
						col.setColumnAlias(ohsColumnCfg.getColumnAlias());
						col.setColumnName(ohsColumnCfg.getColumnName());
						col.setCreateDate(ohsColumnCfg.getCreateDate());
						col.setCreateUser(ohsColumnCfg.getCreateUser());
						col.setSchemaName(ohsTableCfg.getSchemaName());
						col.setIsHide(ohsColumnCfg.getIsHide());
						col.setSysAlias(ohsSysConfig.getSysAlias());
						col.setSysChineseNme(ohsSysConfig.getSysChineseNme());
						col.setTableName(ohsTableCfg.getTableName());
						col.setTableChnName(ohsTableCfg.getTableChnName());
						col.setUpdateDate(ohsColumnCfg.getUpdateDate());
						col.setUpdateUser(ohsColumnCfg.getUpdateUser());
						columnLst.add(col);
					}
				}
			}
		}

		if (CollectionUtils.isEmpty(columnLst)) {
			throw new OhsException("不存在字段信息，请新增！");
		}

		PageResponse<ColumnDTO> columnRsp = new PageResponse<>(columnLst, ohsColumnConfigPage.getNumber(),
				ohsColumnConfigPage.getSize(), ohsColumnConfigPage.getTotalElements(),
				ohsColumnConfigPage.getTotalPages());

		return columnRsp;
	}

	@Override
	public ColumnDTO saveColumnConfig(ColumnDTO column) throws OhsException {
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
		ohsColumnConfig.setIsHide(column.getIsHide());
		ohsColumnConfig.setCreateUser("admin");
		ohsColumnConfig.setSysId(ohsTableConfig.getSysId());
		ohsColumnConfig.setTableId(ohsTableConfigLst.get(0).getId());

		ohsColumnConfig = ohsColumnConfigRepository.save(ohsColumnConfig);
		column.setCreateDate(ohsColumnConfig.getCreateDate());
		column.setCreateUser(ohsColumnConfig.getCreateUser());
		column.setId(ohsColumnConfig.getId());

		return column;
	}

	@Override
	public ColumnDTO deleteById(int id) throws OhsException {
		Optional<OhsColumnConfig> ohsColumnConfiOpt = ohsColumnConfigRepository.findById(id);
		if (!ohsColumnConfiOpt.isPresent()) {
			throw new OhsException("该字段信息已被删除！请重新查询！");
		}
		ohsColumnConfigRepository.deleteById(id);
		ColumnDTO column = new ColumnDTO();
		column.setId(id);
		return column;
	}

	@Override
	public ColumnDTO updateById(ColumnDTO column) throws OhsException {
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
		ohsColumnConfig.setUpdateUser("admin");
		ohsColumnConfig.setIsHide(column.getIsHide());
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

	@Override
	public List<ColumnDTO> getAllColumnList(ColumnDTO column) throws OhsException {
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
		ohsTableConfig.setTableChnName(OhsUtils.putIfNotBlank(column.getTableChnName()));
		ohsTableConfig.setSysId(ohsSysConfig.getId());

		List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(ohsTableConfig));

		if (CollectionUtils.isEmpty(ohsTableConfigLst)) {
			throw new OhsException("不存在对应表信息，请先添加表配置信息后在查询新增字段信息！");
		}

		List<ColumnDTO> columnLst = new ArrayList<>();
		OhsColumnConfig ohsColumnConfig = new OhsColumnConfig();
		// 未上送系统相关信息，相当于查询了全部的表信息
		if (ohsSysConfig.getId() == null) {
			for (OhsTableConfig ohsTableCfg : ohsTableConfigLst) {
				ohsColumnConfig.setColumnAlias(OhsUtils.putIfNotBlank(column.getColumnAlias()));
				ohsColumnConfig.setColumnName(OhsUtils.putIfNotBlank(column.getColumnName()));
				ohsColumnConfig.setIsHide(OhsUtils.putIfNotBlank(column.getIsHide()));
				ohsColumnConfig.setSysId(ohsTableCfg.getSysId());
				ohsColumnConfig.setTableId(ohsTableCfg.getId());
				List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigRepository
						.findAll(Example.of(ohsColumnConfig));
				for (OhsColumnConfig ohsColumnCfg : ohsColumnConfigLst) {
					if (ohsTableCfg.getId().equals(ohsColumnCfg.getTableId())
							&& ohsTableCfg.getSysId().equals(ohsColumnCfg.getSysId())) {
						ColumnDTO col = new ColumnDTO();
						col.setId(ohsColumnCfg.getId());
						col.setColumnAlias(ohsColumnCfg.getColumnAlias());
						col.setColumnName(ohsColumnCfg.getColumnName());
						col.setCreateDate(ohsColumnCfg.getCreateDate());
						col.setCreateUser(ohsColumnCfg.getCreateUser());
						col.setSchemaName(ohsTableCfg.getSchemaName());
						col.setIsHide(ohsColumnCfg.getIsHide());
						Optional<OhsSysConfig> ohsSysConfigOpt = ohsSysConfigRepository
								.findById(ohsTableCfg.getSysId());
						if (ohsSysConfigOpt.isPresent()) {
							col.setSysAlias(ohsSysConfigOpt.get().getSysAlias());
							col.setSysChineseNme(ohsSysConfigOpt.get().getSysChineseNme());
						}
						col.setTableName(ohsTableCfg.getTableName());
						col.setTableChnName(ohsTableCfg.getTableChnName());
						col.setUpdateDate(ohsColumnCfg.getUpdateDate());
						col.setUpdateUser(ohsColumnCfg.getUpdateUser());
						columnLst.add(col);
					}
				}
			}
		} else {
			for (OhsTableConfig ohsTableCfg : ohsTableConfigLst) {
				ohsColumnConfig.setColumnAlias(OhsUtils.putIfNotBlank(column.getColumnAlias()));
				ohsColumnConfig.setColumnName(OhsUtils.putIfNotBlank(column.getColumnName()));
				ohsColumnConfig.setSysId(ohsSysConfig.getId());
				ohsColumnConfig.setTableId(ohsTableCfg.getId());
				List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigRepository
						.findAll(Example.of(ohsColumnConfig));
				if (!CollectionUtils.isEmpty(ohsColumnConfigLst)) {
					for (OhsColumnConfig ohsColumnCfg : ohsColumnConfigLst) {
						ColumnDTO col = new ColumnDTO();
						col.setId(ohsColumnCfg.getId());
						col.setColumnAlias(ohsColumnCfg.getColumnAlias());
						col.setColumnName(ohsColumnCfg.getColumnName());
						col.setCreateDate(ohsColumnCfg.getCreateDate());
						col.setCreateUser(ohsColumnCfg.getCreateUser());
						col.setSchemaName(ohsTableCfg.getSchemaName());
						col.setIsHide(ohsColumnCfg.getIsHide());
						col.setSysAlias(ohsSysConfig.getSysAlias());
						col.setSysChineseNme(ohsSysConfig.getSysChineseNme());
						col.setTableName(ohsTableCfg.getTableName());
						col.setTableChnName(ohsTableCfg.getTableChnName());
						col.setUpdateDate(ohsColumnCfg.getUpdateDate());
						col.setUpdateUser(ohsColumnCfg.getUpdateUser());
						columnLst.add(col);
					}
				}
			}
		}

		if (CollectionUtils.isEmpty(columnLst)) {
			throw new OhsException("不存在字段信息，请新增！");
		}

		return columnLst;
	}

}
