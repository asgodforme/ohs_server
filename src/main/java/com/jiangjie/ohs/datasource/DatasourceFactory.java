package com.jiangjie.ohs.datasource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;

import com.jiangjie.ohs.entity.OhsEnvironmentConfig;

/**
 * 数据源工厂
 * 
 * @author Administrator
 *
 */
public class DatasourceFactory {

	public static final Map<Integer, DataSource> datasourceMap = new ConcurrentHashMap<>();

	private static DataSourceBuilder<?> datasourceBuilder = DataSourceBuilder.create();

	private DatasourceFactory() {

	}

	public static DataSource getDataSourceById(Integer id) {
		return datasourceMap.get(id);
	}

	public static void putDataSource(Integer id, OhsEnvironmentConfig ohsEnvironmentConfig) {
		datasourceMap.put(id, buildDataSource(ohsEnvironmentConfig));
	}

	public static DataSource buildDataSource(OhsEnvironmentConfig ohsEnvironmentConfig) {
		datasourceBuilder.driverClassName("com.mysql.jdbc.Driver");
		datasourceBuilder.url("jdbc:" + ohsEnvironmentConfig.getDbType() + "://" + ohsEnvironmentConfig.getEvnIp() + ":"
				+ ohsEnvironmentConfig.getEvnPort() + "/" + ohsEnvironmentConfig.getDbSchema());
		datasourceBuilder.username(ohsEnvironmentConfig.getDbNme());
		datasourceBuilder.password(ohsEnvironmentConfig.getDbPwd());
		return datasourceBuilder.build();
	}

}
