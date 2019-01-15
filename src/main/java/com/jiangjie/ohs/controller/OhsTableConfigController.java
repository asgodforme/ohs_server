package com.jiangjie.ohs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.Table;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.TableConfigService;

@RestController
@RequestMapping("/api/tableConfig")
public class OhsTableConfigController {

	
	@Autowired
	private TableConfigService tableConfigService;
	
	/**
	 * 根据指定的条件查询所有的环境
	 * @param Table
	 * @return
	 * @throws OhsException
	 */
	@GetMapping("/getAllTable")
	public List<Table> getAllTable(Table Table) throws OhsException {
		return tableConfigService.getAllTable(Table);
	}
	
	/**
	 * TODO POST传值报错！！！麻蛋
	 */
	@GetMapping("/saveTableConfig")
	public Table saveTableConfig(Table table) throws OhsException {
		return tableConfigService.saveTableConfig(table);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Table deleteById(@PathVariable("id") String id) throws OhsException {
		return tableConfigService.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * TODO PUT传值报错！！！麻蛋
	 * @param ohsSysConfig
	 * @return 
	 * @throws OhsException 
	 */
	@GetMapping("/updateById")
	public Table updateById(Table table) throws OhsException {
		return tableConfigService.updateById(table);
	}

}
