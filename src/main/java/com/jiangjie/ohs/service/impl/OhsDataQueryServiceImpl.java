package com.jiangjie.ohs.service.impl;

import java.util.ArrayList;
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
import com.jiangjie.ohs.entity.dataEntity.OhsSingleSqlConfig;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.mapper.OhsSingleSqlConfigMapper;
import com.jiangjie.ohs.repository.OhsEnvironmentConfigRepository;
import com.jiangjie.ohs.repository.OhsModuleConfigRepository;
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
	private OhsSingleSqlConfigMapper ohsSingleSqlConfigMapper;
	
	@Autowired
	private OhsEnvironmentConfigRepository ohsEnvironmentConfigRepository;

	@Override
	public List<DataQueryResponse> queryBatchDataFileds(Map<String, Object> param) throws OhsException {

		if (param == null) {
			throw new OhsException("请上送数据域！");
		}
		OhsUtils.throwIfEmpty(param, "sysAlias", "系统码不能为空！");
		OhsUtils.throwIfEmpty(param, "sysChineseNme", "系统名不能为空！");
		OhsUtils.throwIfEmpty(param, "moduleAlias", "模块名不能为空！");
		OhsUtils.throwIfEmpty(param, "envInfo", "环境	不能为空！");

		// 定位系统
		OhsSysConfig ohsSysConfig = new OhsSysConfig();
		ohsSysConfig.setSysAlias((String) param.get("sysAlias"));
		ohsSysConfig.setSysChineseNme((String) param.get("sysChineseNme"));
		List<OhsSysConfig> ohsSysConfigLst = ohsSysConfigRepository.findAll(Example.of(ohsSysConfig));
		if (CollectionUtils.isEmpty(ohsSysConfigLst)) {
			throw new OhsException("该系统不存在！请联系管理员！");

		}
		ohsSysConfig = ohsSysConfigLst.get(0);

		// 定位模块
		OhsModuleConfig ohsModuleConfig = new OhsModuleConfig();
		ohsModuleConfig.setModuleAlias((String) param.get("moduleAlias"));
		ohsModuleConfig.setSysId(ohsSysConfig.getId());
		List<OhsModuleConfig> ohsModuleConfigLst = ohsModuleConfigRepository.findAll(Example.of(ohsModuleConfig));
		if (CollectionUtils.isEmpty(ohsModuleConfigLst)) {
			throw new OhsException("该模块不存在！请联系管理员！");
		}
		ohsModuleConfig = ohsModuleConfigLst.get(0);
		
		// 定位模块下的sql
		OhsSingleSqlConfig ohsSingleSqlConfig = new OhsSingleSqlConfig();
		ohsSingleSqlConfig.setSysId(ohsSysConfig.getId());
		ohsSingleSqlConfig.setModuleId(ohsModuleConfig.getId());
		List<OhsSingleSqlConfig> ohsSingleSqlConfigLst= ohsSingleSqlConfigRepository.findAll(Example.of(ohsSingleSqlConfig));
		if (CollectionUtils.isEmpty(ohsSingleSqlConfigLst)) {
			throw new OhsException("该单表SQL不存在！请联系管理员！");
		}
		
		System.out.println(ohsSingleSqlConfigMapper.queryDataFields(ohsSingleSqlConfigLst.get(0).getSingleTableSql()));
		
		if (DatasourceFactory.getDataSourceById(Integer.parseInt((String) param.get("envInfo"))) == null) {
			Optional<OhsEnvironmentConfig> OhsEnvironmentConfigOpt = ohsEnvironmentConfigRepository.findById(Integer.parseInt((String) param.get("envInfo")));
			if (!OhsEnvironmentConfigOpt.isPresent()) {
				throw new OhsException("该环境信息不存在！请联系管理员！");
			}
			DatasourceFactory.putDataSource(Integer.parseInt((String) param.get("envInfo")), OhsEnvironmentConfigOpt.get());
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(DatasourceFactory.getDataSourceById(Integer.parseInt((String) param.get("envInfo"))));
		System.out.println(jdbcTemplate.queryForList("select * from ohs_menu"));
		return new ArrayList<DataQueryResponse>();
	}

}
