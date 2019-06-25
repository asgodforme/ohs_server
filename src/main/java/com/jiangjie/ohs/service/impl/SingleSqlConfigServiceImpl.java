package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.SingleSql;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsColumnConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsSingleQueryWhereInfo;
import com.jiangjie.ohs.entity.dataEntity.OhsSingleSqlConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsTableConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.mapper.OhsSingleSqlConfigMapper;
import com.jiangjie.ohs.repository.OhsColumnConfigRepository;
import com.jiangjie.ohs.repository.OhsModuleConfigRepository;
import com.jiangjie.ohs.repository.OhsSingleQueryWhereInfoRepository;
import com.jiangjie.ohs.repository.OhsSingleSqlConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.repository.OhsTableConfigRepository;
import com.jiangjie.ohs.service.SingleSqlConfigService;
import com.jiangjie.ohs.utils.OhsUtils;

/**
 * 单表sql配置服务类
 * 
 * @author Administrator
 *
 */
@Service
public class SingleSqlConfigServiceImpl implements SingleSqlConfigService {

	@Autowired
	private OhsModuleConfigRepository ohsModuleConfigRepository;

	@Autowired
	private OhsTableConfigRepository ohsTableConfigRepository;

	@Autowired
	private OhsSingleSqlConfigRepository ohsSingleSqlConfigRepository;

	@Autowired
	private OhsSingleSqlConfigMapper ohsSingleSqlConfigMapper;

	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;
	
	@Autowired
	private OhsSingleQueryWhereInfoRepository ohsSingleQueryWhereInfoRepository;

	@Override
	public PageResponse<SingleSql> getAllSingleSql(SingleSql singleSql) throws OhsException {
		// 校验系统信息是否有
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(OhsUtils.putIfNotBlank(singleSql.getSysAlias()));
		ohsSysConfig.setSysChineseNme(OhsUtils.putIfNotBlank(singleSql.getSysChineseNme()));
		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("当前系统中不存在系统配置信息，请先在“公共参数配置-系统配置”中配置系统信息！");
		}

		// 校验模块信息是否有
		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(OhsUtils.putIfNotBlank(singleSql.getModuleAlias()));
		ohsModuleConfig.setModuleName(OhsUtils.putIfNotBlank(singleSql.getModuleName()));
		List<OhsModuleConfig> ohsModuleConfigLst = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigLst)) {
			throw new OhsException("当前系统中不存在模块配置信息，请先在“公共参数配置-模块配置”中配置模块信息！");
		}
		// 校验表信息是否有
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setTableName(OhsUtils.putIfNotBlank(singleSql.getTableName()));
		ohsTableConfig.setTableChnName(OhsUtils.putIfNotBlank(singleSql.getTableChnName()));
		List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(ohsTableConfig));
		if (CollectionUtils.isEmpty(ohsTableConfigLst)) {
			throw new OhsException("当前系统中不存在表配置信息，请先在“数据定制化配置-表配置”中配置表信息！");
		}
		
		singleSql.setCurrent(singleSql.getCurrent() - 1 < 0 ? 0 : singleSql.getCurrent() - 1);
		singleSql.setOffsetSize(singleSql.getCurrent() * singleSql.getPageSize());
		
		// 实际查询的sql
		List<SingleSql> singleSqlLst = ohsSingleSqlConfigMapper.findOhsSingleSqlConfig(singleSql);
		if (CollectionUtils.isEmpty(singleSqlLst)) {
			throw new OhsException("当前系统中不存在单表SQL信息，请新增！");
		}
		
		Integer singleSqlSum = ohsSingleSqlConfigMapper.findOhsSingleSqlConfigCount(singleSql);
		Integer totalPages = singleSqlSum % singleSql.getPageSize() == 0 ? singleSqlSum / singleSql.getPageSize() : singleSqlSum / singleSql.getPageSize() + 1; 
		
		for (int i = 0; i < singleSqlLst.size(); i++) {
			SingleSql loopObject = singleSqlLst.get(i);
			List<OhsSingleQueryWhereInfo> ohsSingleQueryWhereInfoLst = loopObject.getOhsSingleQueryWhereInfoLst();
			StringBuffer columnAliass = new StringBuffer();
			StringBuffer columnNames = new StringBuffer();
			columnAliass.append("|");
			columnNames.append("|");
			for (OhsSingleQueryWhereInfo queryWhereInfo : ohsSingleQueryWhereInfoLst) {
				columnNames.append(queryWhereInfo.getKeyChnInfo());
				columnAliass.append(queryWhereInfo.getKeyInfo());
				columnAliass.append("|");
				columnNames.append("|");
			}
			loopObject.setColumnAlias(columnAliass.toString());
			loopObject.setColumnName(columnNames.toString());
		}
		
		PageResponse<SingleSql> pageRsp = new PageResponse<>(singleSqlLst, singleSql.getCurrent(), singleSql.getPageSize(), singleSqlSum, totalPages);
		
		return pageRsp;
	}

	@Override
	public SingleSql saveSingleSqlConfig(SingleSql singleSql) throws OhsException {
		// 当前系统中系统名和系统码唯一！ TODO
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(OhsUtils.putIfNotBlank(singleSql.getSysAlias()));
		ohsSysConfig.setSysChineseNme(OhsUtils.putIfNotBlank(singleSql.getSysChineseNme()));
		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("该系统信息不存在！");
		}

		ohsSysConfig = ohsSysConfigLst.get(0);
		Integer sysId = ohsSysConfig.getId();

		// 系统中模块名称和别名唯一，控制了必输
		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(OhsUtils.putIfNotBlank(singleSql.getModuleAlias()));
		ohsModuleConfig.setModuleName(OhsUtils.putIfNotBlank(singleSql.getModuleName()));
		ohsModuleConfig.setSysId(sysId);
		List<OhsModuleConfig> ohsModuleConfigLst = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigLst)) {
			throw new OhsException("当前系统下不存在该模块信息！");
		}

		// 系统中表名和表中文名唯一，控制了必输
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setTableName(singleSql.getTableName());
		ohsTableConfig.setTableChnName(singleSql.getTableChnName());
		ohsTableConfig.setSysId(sysId);
		List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(ohsTableConfig));
		if (CollectionUtils.isEmpty(ohsTableConfigLst)) {
			throw new OhsException("当前系统下不存在该表信息！");
		}

		OhsSingleSqlConfig ohsSingleSqlConfig = new OhsSingleSqlConfig();
		ohsSingleSqlConfig.setSysId(sysId);
		ohsSingleSqlConfig.setModuleId(ohsModuleConfigLst.get(0).getId());
		ohsSingleSqlConfig.setTableId(ohsTableConfigLst.get(0).getId());
		List<OhsSingleSqlConfig> ohsSingleSqlConfigLst = ohsSingleSqlConfigRepository.findAll(Example.of(ohsSingleSqlConfig));
		ohsSingleSqlConfig.setSingleTableSql(singleSql.getSingleTableSql());
		ohsSingleSqlConfig.setCreateUser("姜杰");
		ohsSingleSqlConfig.setCreateDate(new Timestamp(new Date().getTime()));
		OhsSingleQueryWhereInfo ohsSingleQueryWhereInfo = new OhsSingleQueryWhereInfo();
		// 不存在则去保存，存在就只保存查询键信息
		if (CollectionUtils.isEmpty(ohsSingleSqlConfigLst)) {
			ohsSingleSqlConfig.setRemark(singleSql.getRemark());
			ohsSingleSqlConfig = ohsSingleSqlConfigRepository.save(ohsSingleSqlConfig);
			ohsSingleQueryWhereInfo.setSingleSqlId(ohsSingleSqlConfig.getId());
		} else {
			ohsSingleQueryWhereInfo.setSingleSqlId(ohsSingleSqlConfigLst.get(0).getId());
			ohsSingleSqlConfig.setId(ohsSingleSqlConfigLst.get(0).getId());
			ohsSingleSqlConfig.setRemark(ohsSingleSqlConfigLst.get(0).getRemark());
			ohsSingleSqlConfig.setUpdateDate(new Timestamp(new Date().getTime()));			
			ohsSingleSqlConfig.setUpdateUser("姜杰");
		}
		
		// 每个单表SQL只能添加3个查询条件 // TODO  参数化硬编码，后期考虑配置化
		if (ohsSingleQueryWhereInfoRepository.count(Example.of(ohsSingleQueryWhereInfo)) >= 3) {
			throw new OhsException("单表SQL查询只能配置三个查询条件，请知悉！");
		}
		
		ohsSingleQueryWhereInfo.setKeyInfo(singleSql.getColumnAlias());
		if (ohsSingleQueryWhereInfoRepository.exists(Example.of(ohsSingleQueryWhereInfo))) {
			throw new OhsException("该单表查询SQL该字段查询条件已经存在！");
		}
		ohsSingleQueryWhereInfo.setKeyChnInfo(singleSql.getColumnName());
		ohsSingleQueryWhereInfo.setCreateUser("姜杰");
		ohsSingleQueryWhereInfo.setCreateDate(new Timestamp(new Date().getTime()));
		ohsSingleQueryWhereInfoRepository.save(ohsSingleQueryWhereInfo);
		
		singleSql.setId(ohsSingleSqlConfig.getId());
		// 根据数据库中配置的信息生成单表sql
		String singleSqlResult = generateSingleSql(ohsTableConfigLst.get(0).getId(), ohsSingleSqlConfig.getId());
		ohsSingleSqlConfig.setSingleTableSql(singleSqlResult);
		ohsSingleSqlConfigRepository.save(ohsSingleSqlConfig);
		singleSql.setSingleTableSql(singleSqlResult);
		singleSql.setCreateUser(ohsSingleSqlConfig.getCreateUser());
		singleSql.setCreateDate(ohsSingleSqlConfig.getCreateDate());
		return singleSql;
	}

	@Autowired
	private OhsColumnConfigRepository ohsColumnConfigRepository;
	
	private String generateSingleSql(Integer tableId, Integer singleSqlId) throws OhsException {
		StringBuffer singleSqlSb = new StringBuffer();
		singleSqlSb.append("select ");
		
		// 查出当前表的所有配置字段
		OhsColumnConfig queryObj = new OhsColumnConfig();
		queryObj.setTableId(tableId);
		List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigRepository.findAll(Example.of(queryObj));
		
		if (CollectionUtils.isEmpty(ohsColumnConfigLst)) {
			singleSqlSb.append("* ");
		} else {
			for (OhsColumnConfig column : ohsColumnConfigLst) {
				singleSqlSb.append(column.getColumnName()).append(", ");
			}
			singleSqlSb.delete(singleSqlSb.length() - 2, singleSqlSb.length());
		}
		singleSqlSb.append(" from ");
		
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setId(tableId);
		Optional<OhsTableConfig> ohsTableConfigOpt = ohsTableConfigRepository.findOne(Example.of(ohsTableConfig));
		if (!ohsTableConfigOpt.isPresent()) {
			throw new OhsException("当前表已经被删除！");
		}
		
		singleSqlSb.append(ohsTableConfigOpt.get().getSchemaName()).append(".").append( ohsTableConfigOpt.get().getTableName()).append(" ");
		
		OhsSingleQueryWhereInfo ohsSingleQueryWhereInfo = new OhsSingleQueryWhereInfo();
		ohsSingleQueryWhereInfo.setSingleSqlId(singleSqlId);
		List<OhsSingleQueryWhereInfo> OhsSingleQueryWhereInfoLst = ohsSingleQueryWhereInfoRepository.findAll(Example.of(ohsSingleQueryWhereInfo));
		
		if (!CollectionUtils.isEmpty(OhsSingleQueryWhereInfoLst)) {
			singleSqlSb.append("where ");
			for (OhsSingleQueryWhereInfo whereInfo : OhsSingleQueryWhereInfoLst) {
				singleSqlSb.append(whereInfo.getKeyInfo()).append("=").append("{").append(whereInfo.getKeyInfo()).append("}").append(" ").append("and ");
			}
			singleSqlSb.delete(singleSqlSb.length() - 4, singleSqlSb.length());
		}
		
		return singleSqlSb.toString();
	}

	@Override
	public SingleSql deleteById(int id) throws OhsException {
		Optional<OhsSingleSqlConfig> ohsSingleSqlConfigOpt = ohsSingleSqlConfigRepository.findById(id);
		if (!ohsSingleSqlConfigOpt.isPresent()) {
			throw new OhsException("当前纪录已经被删除！");
		}
		// 删除单表sql下配置的查询条件字段
		OhsSingleQueryWhereInfo ohsSingleQueryWhereInfo = new OhsSingleQueryWhereInfo();
		ohsSingleQueryWhereInfo.setSingleSqlId(ohsSingleSqlConfigOpt.get().getId());
		ohsSingleQueryWhereInfoRepository.deleteInBatch(ohsSingleQueryWhereInfoRepository.findAll(Example.of(ohsSingleQueryWhereInfo)));
		ohsSingleSqlConfigRepository.deleteById(id);
		SingleSql singleSql = new SingleSql();
		singleSql.setId(id);
		return singleSql;
	}

	@Override
	public SingleSql updateById(SingleSql singleSql) throws OhsException {
		Optional<OhsSingleSqlConfig> ohsSingleSqlConfigOpt =  ohsSingleSqlConfigRepository.findById(singleSql.getId());
		if (!ohsSingleSqlConfigOpt.isPresent()) {
			throw new OhsException("当前单表SQL纪录不存在！");
		}
		// 当前系统中系统名和系统码唯一！ TODO
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias(OhsUtils.putIfNotBlank(singleSql.getSysAlias()));
		ohsSysConfig.setSysChineseNme(OhsUtils.putIfNotBlank(singleSql.getSysChineseNme()));
		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("该系统信息不存在！");
		}
	
		ohsSysConfig = ohsSysConfigLst.get(0);
		Integer sysId = ohsSysConfig.getId();
	
		// 系统中模块名称和别名唯一，控制了必输
		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias(OhsUtils.putIfNotBlank(singleSql.getModuleAlias()));
		ohsModuleConfig.setModuleName(OhsUtils.putIfNotBlank(singleSql.getModuleName()));
		ohsModuleConfig.setSysId(sysId);
		List<OhsModuleConfig> ohsModuleConfigLst = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigLst)) {
			throw new OhsException("当前系统下不存在该模块信息！");
		}
	
		// 系统中表名和表中文名唯一，控制了必输
		OhsTableConfig ohsTableConfig = new OhsTableConfig();
		ohsTableConfig.setTableName(singleSql.getTableName());
		ohsTableConfig.setTableChnName(singleSql.getTableChnName());
		ohsTableConfig.setSysId(sysId);
		List<OhsTableConfig> ohsTableConfigLst = ohsTableConfigRepository.findAll(Example.of(ohsTableConfig));
		if (CollectionUtils.isEmpty(ohsTableConfigLst)) {
			throw new OhsException("当前系统下不存在该表信息！");
		}
		
		// 修改对应的查询条件
		if (!StringUtils.isEmpty(singleSql.getColumnAlias()) && singleSql.getColumnAlias().indexOf(",") > -1) {
			// 查询当前单表SQL下的查询条件
			OhsSingleQueryWhereInfo whereInfo = new OhsSingleQueryWhereInfo();
			whereInfo.setSingleSqlId(singleSql.getId());
			List<OhsSingleQueryWhereInfo> ohsSingleQueryWhereInfoLst = ohsSingleQueryWhereInfoRepository.findAll(Example.of(whereInfo));
			List<String> columnAliass = Arrays.asList(singleSql.getColumnAlias().split(",")).stream().filter(colAlias -> !StringUtils.isEmpty(colAlias)).collect(Collectors.toList());
			List<OhsSingleQueryWhereInfo> resultAlias = ohsSingleQueryWhereInfoLst.stream().filter(ohsSingleSqlWhereInfo -> columnAliass.stream().allMatch(col -> !col.equals(ohsSingleSqlWhereInfo.getKeyInfo()))).collect(Collectors.toList());
			if (!CollectionUtils.isEmpty(resultAlias)) {
				for (OhsSingleQueryWhereInfo resltAlias : resultAlias) {
					// 删除掉对应的查询条件
					whereInfo.setKeyInfo(resltAlias.getKeyInfo());
					Optional<OhsSingleQueryWhereInfo> ohsSingleQueryWhereInfoOpt = ohsSingleQueryWhereInfoRepository.findOne(Example.of(whereInfo));
					if (ohsSingleQueryWhereInfoOpt.isPresent()) {
						ohsSingleQueryWhereInfoRepository.delete(ohsSingleQueryWhereInfoOpt.get());
					} else {
						throw new OhsException("对应删除条件不存在！");
					}
				}
			}
		}
		
		OhsSingleSqlConfig ohsSingleSqlConfig = ohsSingleSqlConfigOpt.get();
		ohsSingleSqlConfig.setSysId(sysId);
		ohsSingleSqlConfig.setModuleId(ohsModuleConfigLst.get(0).getId());
		ohsSingleSqlConfig.setTableId(ohsTableConfigLst.get(0).getId());
		ohsSingleSqlConfig.setRemark(singleSql.getRemark());
		ohsSingleSqlConfig.setUpdateDate(new Timestamp(new Date().getTime()));
		ohsSingleSqlConfig.setUpdateUser("修改者");
		
		ohsSingleSqlConfigRepository.save(ohsSingleSqlConfig);
		singleSql.setUpdateDate(ohsSingleSqlConfig.getUpdateDate());
		singleSql.setUpdateUser(ohsSingleSqlConfig.getUpdateUser());
		singleSql.setCreateDate(ohsSingleSqlConfig.getCreateDate());
		singleSql.setCreateUser(ohsSingleSqlConfig.getCreateUser());
		return singleSql;
	}

}
