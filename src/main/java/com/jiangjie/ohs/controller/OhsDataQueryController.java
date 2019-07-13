package com.jiangjie.ohs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiangjie.ohs.dto.DataQueryResponse;
import com.jiangjie.ohs.exception.OhsException;
import com.jiangjie.ohs.service.OhsDataQueryService;

@RestController
@RequestMapping("/api/dataQuery")
public class OhsDataQueryController {

	@Autowired
	private OhsDataQueryService ohsDataQueryService;

	/**
	 * 数据查询提交，POST请求通了。
	 * 
	 * @param requestMap
	 * @return
	 * @throws OhsException 
	 */
	@PostMapping("/querySubmit")
	@ResponseBody
	public List<DataQueryResponse> querySubmit(@RequestBody Map<String, Object> requestMap) throws OhsException {
		return ohsDataQueryService.queryBatchDataFileds(requestMap);
	}

}
