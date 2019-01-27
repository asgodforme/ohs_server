package com.jiangjie.ohs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public PageResponse<SingleSql> getAllSingleSql(SingleSql SingleSql) throws OhsException {
		return singleSqlConfigService.getAllSingleSql(SingleSql);
	}
	
	/**
	 * TODO POST传值报错！！！麻蛋
	 */
	@GetMapping("/saveSingleSqlConfig")
	public SingleSql saveSingleSqlConfig(SingleSql singleSql) throws OhsException {
		return singleSqlConfigService.saveSingleSqlConfig(singleSql);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public SingleSql deleteById(@PathVariable("id") String id) throws OhsException {
		return singleSqlConfigService.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * TODO PUT传值报错！！！麻蛋
	 * @param ohsSysConfig
	 * @return 
	 * @throws OhsException 
	 */
	@GetMapping("/updateById")
	public SingleSql updateById(SingleSql singleSql) throws OhsException {
		return singleSqlConfigService.updateById(singleSql);
	}

}
