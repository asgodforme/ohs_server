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
import com.jiangjie.ohs.service.EnumValueConfigService;

@RestController
@RequestMapping("/api/enumValueConfig")
public class OhsEnumValueConfigController {

	
	@Autowired
	private EnumValueConfigService enumValueConfigService;
	
	/**
	 * 根据指定的条件查询所有的环境
	 * @param EnumValue
	 * @return
	 * @throws OhsException
	 */
	@GetMapping("/getAllEnumValue")
	public List<Column> getAllEnumValue(Column column) throws OhsException {
		return enumValueConfigService.getAllEnumValue(column);
	}
	
	/**
	 * TODO POST传值报错！！！麻蛋
	 */
	@GetMapping("/saveEnumValueConfig")
	public Column saveEnumValueConfig(Column column) throws OhsException {
		return enumValueConfigService.saveEnumValueConfig(column);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Column deleteById(@PathVariable("id") String id) throws OhsException {
		return enumValueConfigService.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * TODO PUT传值报错！！！麻蛋
	 * @param ohsSysConfig
	 * @return 
	 * @throws OhsException 
	 */
	@GetMapping("/updateById")
	public Column updateById(Column column) throws OhsException {
		return enumValueConfigService.updateById(column);
	}

}
