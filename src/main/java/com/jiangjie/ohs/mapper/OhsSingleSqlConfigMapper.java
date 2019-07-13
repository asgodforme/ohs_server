package com.jiangjie.ohs.mapper;

import java.util.List;
import java.util.Map;

import com.jiangjie.ohs.dto.SingleSql;
import com.jiangjie.ohs.entity.OhsModuleConfig;

/**
 * 自定义查询单表sql Mapper:使用mybatis
 * @author Administrator
 *
 */
public interface OhsSingleSqlConfigMapper {

	/**
	 * 通过关系表关联查询：项目中未使用
	 * @return
	 */
	List<OhsModuleConfig> findOhsTableConfigInnerOhsModuleConfig();

	/**
	 * 查询单表SQL
	 * @param singleSql
	 * @return
	 */
	List<SingleSql> findOhsSingleSqlConfig(SingleSql singleSql);

	/**
	 * 查询单表SQL数量
	 * @param singleSql
	 * @return
	 */
	Integer findOhsSingleSqlConfigCount(SingleSql singleSql);
	
	/**
	 * 查询指定的sql
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> queryDataFields(String sql);
}
