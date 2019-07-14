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

import com.jiangjie.ohs.dto.ColumnDTO;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.ColumnConfigService;

@RestController
@RequestMapping("/api/columnConfig")
public class OhsColumnConfigController {

	
	@Autowired
	private ColumnConfigService columnConfigService;
	
	/**
	 * 根据指定的条件查询所有的环境
	 * @param Column
	 * @return
	 * @throws OhsException
	 */
	@GetMapping("/getAllColumn")
	public PageResponse<ColumnDTO> getAllColumn(ColumnDTO Column) throws OhsException {
		return columnConfigService.getAllColumn(Column);
	}
	
	/**
	 * TODO POST传值报错！！！麻蛋
	 */
//	@GetMapping("/saveColumnConfig")
//	public ColumnDTO saveColumnConfig(ColumnDTO column) throws OhsException {
//		System.out.println(column);
//		return columnConfigService.saveColumnConfig(column);
//	}
	
	@PostMapping("/saveColumnConfig")
	@ResponseBody
	public ColumnDTO saveColumnConfig(@RequestBody Map<String, Object> requestParam) throws OhsException {
		ColumnDTO column = new ColumnDTO();
		column.setSysAlias((String) requestParam.get("sysAlias"));
		column.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		column.setSchemaName((String) requestParam.get("schemaName"));
		column.setTableName((String) requestParam.get("tableName"));
		column.setColumnAlias((String) requestParam.get("columnAlias"));
		column.setColumnName((String) requestParam.get("columnName"));
		column.setIsHide((String) requestParam.get("isHide"));
		column.setTableChnName((String) requestParam.get("tableChnName"));
		return columnConfigService.saveColumnConfig(column);
	}
	
	
	@DeleteMapping("/deleteById/{id}")
	public ColumnDTO deleteById(@PathVariable("id") String id) throws OhsException {
		return columnConfigService.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * TODO PUT传值报错！！！麻蛋
	 * @param ohsSysConfig
	 * @return 
	 * @throws OhsException 
	 */
//	@GetMapping("/updateById")
//	public ColumnDTO updateById(ColumnDTO column) throws OhsException {
//		return columnConfigService.updateById(column);
//	}
	
	@PutMapping("/updateById")
	@ResponseBody
	public ColumnDTO updateById(@RequestBody Map<String, Object> requestParam) throws OhsException {
		ColumnDTO column = new ColumnDTO();
		column.setId((Integer) requestParam.get("id"));
		column.setSysAlias((String) requestParam.get("sysAlias"));
		column.setSysChineseNme((String) requestParam.get("sysChineseNme"));
		column.setSchemaName((String) requestParam.get("schemaName"));
		column.setTableName((String) requestParam.get("tableName"));
		column.setColumnAlias((String) requestParam.get("columnAlias"));
		column.setColumnName((String) requestParam.get("columnName"));
		column.setIsHide((String) requestParam.get("isHide"));
		column.setTableChnName((String) requestParam.get("tableChnName"));
		return columnConfigService.updateById(column);
	}

}
