package com.jiangjie.ohs.datasource;

import java.util.HashMap;
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
	
	public static final Map<String, String> dataBaseTypeMapping = new HashMap<String, String>();
	
	static {
		dataBaseTypeMapping.put("0", "mysql");
		dataBaseTypeMapping.put("1", "oracle");
		dataBaseTypeMapping.put("2", "db2");
	}
	
	public static final Map<String, String> evnTypeMapping = new HashMap<String, String>();
	
	static {
		evnTypeMapping.put("0", "应用服务器");
		evnTypeMapping.put("1", "数据库");
	}

	private DatasourceFactory() {

	}

	public static DataSource getDataSourceById(Integer id) {
		return datasourceMap.get(id);
	}

	public static void putDataSource(Integer id, OhsEnvironmentConfig ohsEnvironmentConfig) {
		datasourceMap.put(id, buildDataSource(ohsEnvironmentConfig));
	}

	/**
	 * 0-mysql
	 * 1-oracle
	 * 2-db2
	 * @param ohsEnvironmentConfig
	 * @return
	 */
	public static DataSource buildDataSource(OhsEnvironmentConfig ohsEnvironmentConfig) {
		datasourceBuilder.driverClassName("com.mysql.jdbc.Driver");
		datasourceBuilder.url("jdbc:" + dataBaseTypeMapping.get(ohsEnvironmentConfig.getDbType())
										+ "://" + ohsEnvironmentConfig.getEvnIp() 
										+ ":"
										+ ohsEnvironmentConfig.getEvnPort() + "/" 
										+ ohsEnvironmentConfig.getEvnAlias());
		datasourceBuilder.username(ohsEnvironmentConfig.getDbNme());
		datasourceBuilder.password(ohsEnvironmentConfig.getDbPwd());
		return datasourceBuilder.build();
	}

}
