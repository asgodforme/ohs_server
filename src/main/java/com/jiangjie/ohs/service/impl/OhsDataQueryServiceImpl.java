package com.jiangjie.ohs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jiangjie.ohs.datasource.DatasourceFactory;
import com.jiangjie.ohs.dto.DataQueryResponse;
import com.jiangjie.ohs.entity.OhsEnvironmentConfig;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.OhsSysConfig;
import com.jiangjie.ohs.entity.common.RelationUserInfo;
import com.jiangjie.ohs.entity.dataEntity.OhsColumnConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsEnumValueConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsSingleQueryWhereInfo;
import com.jiangjie.ohs.entity.dataEntity.OhsSingleSqlConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.repository.OhsColumnConfigRepository;
import com.jiangjie.ohs.repository.OhsEnumValueConfigRepository;
import com.jiangjie.ohs.repository.OhsEnvironmentConfigRepository;
import com.jiangjie.ohs.repository.OhsModuleConfigRepository;
import com.jiangjie.ohs.repository.OhsSingleQueryWhereInfoRepository;
import com.jiangjie.ohs.repository.OhsSingleSqlConfigRepository;
import com.jiangjie.ohs.repository.OhsSysConfigRepository;
import com.jiangjie.ohs.service.OhsDataQueryService;
import com.jiangjie.ohs.utils.OhsUtils;

@Service
public class OhsDataQueryServiceImpl implements OhsDataQueryService {

	@Autowired
	private OhsSysConfigRepository ohsSysConfigRepository;

	@Autowired
	private OhsModuleConfigRepository ohsModuleConfigRepository;

	@Autowired
	private OhsSingleSqlConfigRepository ohsSingleSqlConfigRepository;

	@Autowired
	private OhsEnvironmentConfigRepository ohsEnvironmentConfigRepository;

	@Autowired
	private OhsSingleQueryWhereInfoRepository ohsSingleQueryWhereInfoRepository;
	
	@Autowired
	private OhsColumnConfigRepository ohsColumnConfigRepository;
	
	@Autowired
	private OhsEnumValueConfigRepository ohsEnumValueConfigRepository;
	@Override
	public List<DataQueryResponse> queryBatchDataFileds(Map<String, Object> param) throws OhsException {

		if (param == null) {
			throw new OhsException("请上送数据域！");
		}
		OhsUtils.throwIfEmpty(param, "sysAlias", "系统码不能为空！");
		OhsUtils.throwIfEmpty(param, "sysChineseNme", "系统名不能为空！");
		OhsUtils.throwIfEmpty(param, "moduleAlias", "模块名不能为空！");
		OhsUtils.throwIfEmpty(param, "envInfo", "环境	不能为空！");
		
		String createUser = (String) param.get("tokenName");

		// 定位系统
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias((String) param.get("sysAlias"));
		ohsSysConfig.setSysChineseNme((String) param.get("sysChineseNme"));
		ohsSysConfig.setCreateUser(createUser);
		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("该系统不存在！请联系管理员！");

		}
		ohsSysConfig = ohsSysConfigLst.get(0);

		// 定位模块
		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias((String) param.get("moduleAlias"));
		ohsModuleConfig.setSysId(ohsSysConfig.getId());
		RelationUserInfo relationUserInfo = new RelationUserInfo();
		relationUserInfo.setCreateUser(createUser);
		ohsModuleConfig.setRelationUserInfo(relationUserInfo);
		List<OhsModuleConfig> ohsModuleConfigLst = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigLst)) {
			throw new OhsException("该模块不存在！请联系管理员！");
		}
		ohsModuleConfig = ohsModuleConfigLst.get(0);

		// 定位模块下的sql
		OhsSingleSqlConfig ohsSingleSqlConfig = new OhsSingleSqlConfig();
		ohsSingleSqlConfig.setSysId(ohsSysConfig.getId());
		ohsSingleSqlConfig.setModuleId(ohsModuleConfig.getId());
		ohsSingleSqlConfig.setCreateUser(createUser);
		List<OhsSingleSqlConfig> ohsSingleSqlConfigLst = ohsSingleSqlConfigRepository
				.findAll(Example.of(ohsSingleSqlConfig));
		if (CollectionUtils.isEmpty(ohsSingleSqlConfigLst)) {
			throw new OhsException("该单表SQL不存在！请联系管理员！");
		}

//		if (DatasourceFactory.getDataSourceById(Integer.parseInt((String) param.get("envInfo"))) == null) {
//		}
		Optional<OhsEnvironmentConfig> ohsEnvironmentConfigOpt = ohsEnvironmentConfigRepository.findById(Integer.parseInt((String) param.get("envInfo")));
		if (!ohsEnvironmentConfigOpt.isPresent()) {
			throw new OhsException("该环境信息不存在！请联系管理员！");
		}
		DatasourceFactory.putDataSource(Integer.parseInt((String) param.get("envInfo")),
				ohsEnvironmentConfigOpt.get());

		JdbcTemplate jdbcTemplate = new JdbcTemplate(
				DatasourceFactory.getDataSourceById(Integer.parseInt((String) param.get("envInfo"))));

		List<DataQueryResponse> dataQueryResponseLst = new ArrayList<>();

		for (OhsSingleSqlConfig ohsSingleSql : ohsSingleSqlConfigLst) {
			DataQueryResponse dataQueryResponse = new DataQueryResponse();
			dataQueryResponse.setTitle(ohsSingleSql.getRemark() + "(" + ohsSingleSql.getSingleTableSql()  + ")");
			dataQueryResponse.setRequestParam(param);

			// 查询条件信息
			OhsSingleQueryWhereInfo ohsSingleQueryWhereInfo = new OhsSingleQueryWhereInfo();
			ohsSingleQueryWhereInfo.setSingleSqlId(ohsSingleSql.getId());
			ohsSingleQueryWhereInfo.setCreateUser(createUser);
			List<OhsSingleQueryWhereInfo> ohsSingleQueryWhereInfoLst = ohsSingleQueryWhereInfoRepository
					.findAll(Example.of(ohsSingleQueryWhereInfo));

			String sql = ohsSingleSql.getSingleTableSql();

			if (!CollectionUtils.isEmpty(ohsSingleQueryWhereInfoLst)) {
				for (OhsSingleQueryWhereInfo whereInf : ohsSingleQueryWhereInfoLst) {
					sql = sql.replace("{" + whereInf.getKeyInfo() + "}", "\"" + (String) param.get(whereInf.getKeyInfo()) + "\"");
				}
			}

			List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
			dataQueryResponse.setDataFields(result);
			
			List<Map<String, String>> headerColumns = new ArrayList<>();
			dataQueryResponse.setDataHeader(headerColumns);
			if (!CollectionUtils.isEmpty(result)) {
				for (Map.Entry<String, Object> entry : result.get(0).entrySet()) {
					OhsColumnConfig ohsColumnConfig = new OhsColumnConfig();
					ohsColumnConfig.setSysId(ohsSysConfig.getId());
					ohsColumnConfig.setTableId(ohsSingleSql.getTableId());
					ohsColumnConfig.setColumnName(entry.getKey().toLowerCase());
					ohsColumnConfig.setCreateUser(createUser);
					List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigRepository.findAll(Example.of(ohsColumnConfig));
					Map<String, String> headerColumn = new HashMap<String, String>();
					if (CollectionUtils.isEmpty(ohsColumnConfigLst)) {
						headerColumn.put(entry.getKey(), entry.getKey());
					} else {
						headerColumn.put(entry.getKey(), ohsColumnConfigLst.get(0).getColumnAlias());
					}
					headerColumns.add(headerColumn);
				}
				for (Map<String, Object> map : result) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						OhsColumnConfig ohsColumnConfig = new OhsColumnConfig();
						ohsColumnConfig.setSysId(ohsSysConfig.getId());
						ohsColumnConfig.setTableId(ohsSingleSql.getTableId());
						ohsColumnConfig.setColumnName(entry.getKey().toLowerCase());
						ohsColumnConfig.setCreateUser(createUser);
						List<OhsColumnConfig> ohsColumnConfigLst = ohsColumnConfigRepository.findAll(Example.of(ohsColumnConfig));
						if (!CollectionUtils.isEmpty(ohsColumnConfigLst)) {
							ohsColumnConfig = ohsColumnConfigLst.get(0);
							OhsEnumValueConfig ohsEnumValueConfig = new OhsEnumValueConfig();
							ohsEnumValueConfig.setColumnId(ohsColumnConfig.getId());
							List<OhsEnumValueConfig> ohsEnumValueConfigLst = ohsEnumValueConfigRepository.findAll(Example.of(ohsEnumValueConfig));
							if (!CollectionUtils.isEmpty(ohsEnumValueConfigLst)) {
								for (OhsEnumValueConfig enumCfg : ohsEnumValueConfigLst) {
									if (entry.getValue() != null && (entry.getValue() + "").equals(enumCfg.getEnumValue())) {
										entry.setValue(enumCfg.getEnumChineseValue());
									}
								}
							}
						}
					}
				}
			}
			dataQueryResponseLst.add(dataQueryResponse);
		}
		
		return dataQueryResponseLst;
	}

}
