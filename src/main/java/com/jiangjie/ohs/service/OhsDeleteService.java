package com.jiangjie.ohs.service;

import java.util.function.Consumer;

import com.jiangjie.ohs.entity.OhsEnvironmentConfig;
import com.jiangjie.ohs.entity.OhsModuleConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsColumnConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsSingleSqlConfig;
import com.jiangjie.ohs.entity.dataEntity.OhsTableConfig;

public interface OhsDeleteService {

	public void deleteAllModule(Consumer<OhsModuleConfig> consumer);

	public void deleteAllEvn(Consumer<OhsEnvironmentConfig> consumer);

	public void deleteAllTable(Consumer<OhsTableConfig> consumer);

	public void deleteAllColumns(Consumer<OhsColumnConfig> consumer);

	public void deleteAllSingleSql(Consumer<OhsSingleSqlConfig> consumer);
}
