package com.jiangjie.ohs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.SingleSql;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.SingleSqlConfigService;

@RestController
@RequestMapping("/api/singleSqlConfig")
public class OhsSingleSqlConfigController {

	
	@Autowired
	private SingleSqlConfigService singleSqlConfigService;
	
	/**
	 * 根据指定的条件查询所有的环境
	 * @param SingleSql
	 * @return
	 * @throws OhsException
	 */
	@GetMapping("/getAllSingleSql")
	public PageResponse<SingleSql> getAllSingleSql(SingleSql SingleSql, String tokenName) throws OhsException {
		SingleSql.setCreateUser(tokenName);
		return singleSqlConfigService.getAllSingleSql(SingleSql);
	}
	
	@PostMapping("/saveSingleSqlConfig")
	@ResponseBody
	public SingleSql saveSingleSqlConfig(@RequestBody Map<String, Object> requestParam) throws OhsException {
		SingleSql singleSql = new SingleSql();
		singleSql.setSysAlias((String) requestParam.get("sysAlias"));
		singleSql.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		singleSql.setModuleAlias((String) requestParam.get("moduleAlias"));
		singleSql.setModuleName((String) requestParam.get("moduleName"));
		singleSql.setTableName((String) requestParam.get("tableName"));
		singleSql.setTableChnName((String) requestParam.get("tableChnName"));
		singleSql.setSingleTableSql((String) requestParam.get("singleTableSql"));
		singleSql.setRemark((String) requestParam.get("remark"));
		singleSql.setColumnAlias((String) requestParam.get("columnAlias"));
		singleSql.setColumnName((String) requestParam.get("columnName"));
		singleSql.setCreateUser((String) requestParam.get("tokenName"));
		return singleSqlConfigService.saveSingleSqlConfig(singleSql);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public SingleSql deleteById(@PathVariable("id") String id, String tokenName) throws OhsException {
		return singleSqlConfigService.deleteById(Integer.parseInt(id), tokenName);
	}
	
	@PutMapping("/updateById")
	@ResponseBody
	public SingleSql updateById(@RequestBody Map<String, Object> requestParam) throws OhsException {
		SingleSql singleSql = new SingleSql();
		singleSql.setId((Integer) requestParam.get("id"));
		singleSql.setSysAlias((String) requestParam.get("sysAlias"));
		singleSql.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		singleSql.setModuleAlias((String) requestParam.get("moduleAlias"));
		singleSql.setModuleName((String) requestParam.get("moduleName"));
		singleSql.setTableName((String) requestParam.get("tableName"));
		singleSql.setTableChnName((String) requestParam.get("tableChnName"));
		singleSql.setSingleTableSql((String) requestParam.get("singleTableSql"));
		singleSql.setRemark((String) requestParam.get("remark"));
		singleSql.setColumnAlias((String) requestParam.get("columnAlias"));
		singleSql.setColumnName((String) requestParam.get("columnName"));
		singleSql.setCreateUser((String) requestParam.get("tokenName"));
		
		return singleSqlConfigService.updateById(singleSql);
	}

}
