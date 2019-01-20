package com.jiangjie.ohs.mapper;

import java.util.List;

import com.jiangjie.ohs.dto.SingleSql;
import com.jiangjie.ohs.entity.OhsModuleConfig;

public interface OhsSingleSqlConfigMapper {

	List<OhsModuleConfig> findOhsTableConfigInnerOhsModuleConfig();
	
	List<SingleSql> findOhsSingleSqlConfig(SingleSql singleSql);

}
