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

import com.jiangjie.ohs.dto.Evn;
import com.jiangjie.ohs.dto.PageResponse;
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
	public PageResponse<Evn> getAllEvn(Evn Evn, String tokenName) throws OhsException {
		Evn.setCreateUser(tokenName);
		return evnConfigService.getAllEvn(Evn);
	}
	
	@PostMapping("/saveEvnConfig")
	@ResponseBody
	public Evn saveEvnConfig(@RequestBody Map<String, Object> requestParam) throws OhsException {
		Evn evn = new Evn();
		evn.setSysAlias(((String) requestParam.get("sysAlias")).toUpperCase());
		evn.setSysChineseNme(((String) requestParam.get("sysChineseNme")).toUpperCase());
		evn.setDbNme((String) requestParam.get("dbNme"));
		evn.setDbPwd((String) requestParam.get("dbPwd"));
		evn.setEvnAlias((String) requestParam.get("evnAlias"));
		evn.setEvnIp((String) requestParam.get("evnIp"));
		evn.setEvnName((String) requestParam.get("evnName"));
		evn.setEvnPort((String) requestParam.get("evnPort"));
		evn.setEvnTyp((String) requestParam.get("evnTyp"));
		evn.setDbType((String) requestParam.get("dbType"));
		evn.setCreateUser((String) requestParam.get("tokenName"));
		return evnConfigService.saveEvnConfig(evn);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public Evn deleteById(@PathVariable("id") String id, String tokenName) throws OhsException {
		return evnConfigService.deleteById(Integer.parseInt(id), tokenName);
	}
	
	@PutMapping("/updateById")
	@ResponseBody
	public Evn updateById(@RequestBody Map<String, Object> requestParam) throws OhsException {
		Evn evn = new Evn();
		evn.setId((Integer) requestParam.get("id"));
		evn.setSysAlias(((String) requestParam.get("sysAlias")).toUpperCase());
		evn.setSysChineseNme(((String) requestParam.get("sysChineseNme")).toUpperCase());
		evn.setDbNme((String) requestParam.get("dbNme"));
		evn.setDbPwd((String) requestParam.get("dbPwd"));
		evn.setEvnAlias((String) requestParam.get("evnAlias"));
		evn.setEvnIp((String) requestParam.get("evnIp"));
		evn.setEvnName((String) requestParam.get("evnName"));
		evn.setEvnPort((String) requestParam.get("evnPort"));
		evn.setEvnTyp((String) requestParam.get("evnTyp"));
		evn.setDbType((String) requestParam.get("dbType"));
		evn.setCreateUser((String) requestParam.get("tokenName"));
		return evnConfigService.updateById(evn);
	}
}
