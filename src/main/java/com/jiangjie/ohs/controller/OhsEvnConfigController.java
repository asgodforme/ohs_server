package com.jiangjie.ohs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.Evn;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.EvnConfigService;

@RestController
@RequestMapping("/api/evnConfig")
public class OhsEvnConfigController {

	
	@Autowired
	private EvnConfigService evnConfigService;
	
	/**
	 * 根据指定的条件查询所有的环境
	 * @param Evn
	 * @return
	 * @throws OhsException
	 */
	@GetMapping("/getAllEvn")
	public List<Evn> getAllEvn(Evn Evn) throws OhsException {
		return evnConfigService.getAllEvn(Evn);
	}
	
	/**
	 * TODO POST传值报错！！！麻蛋
	 */
	@GetMapping("/saveEvnConfig")
	public Evn saveEvnConfig(Evn evn) throws OhsException {
		return evnConfigService.saveEvnConfig(evn);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Evn deleteById(@PathVariable("id") String id) throws OhsException {
		return evnConfigService.deleteById(Integer.parseInt(id));
	}
	
	/**
	 * TODO PUT传值报错！！！麻蛋
	 * @param ohsSysConfig
	 * @return 
	 * @throws OhsException 
	 */
	@GetMapping("/updateById")
	public Evn updateById(Evn evn) throws OhsException {
		return evnConfigService.updateById(evn);
	}

}
