package com.jiangjie.ohs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.Column;
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
	public List<Column> getAllColumn(Column Column) throws OhsException {
		return columnConfigService.getAllColumn(Column);
	}
	
	/**
	 * TODO POST传值报错！！！麻蛋
	 */
	@GetMapping("/saveColumnConfig")
	public Column saveColumnConfig(Column column) throws OhsException {
		return columnConfigService.saveColumnConfig(column);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Column deleteById(@PathVariable("id") String id) throws OhsException {
		return columnConfigService.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * TODO PUT传值报错！！！麻蛋
	 * @param ohsSysConfig
	 * @return 
	 * @throws OhsException 
	 */
	@GetMapping("/updateById")
	public Column updateById(Column column) throws OhsException {
		return columnConfigService.updateById(column);
	}

}