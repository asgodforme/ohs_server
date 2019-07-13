package com.jiangjie.ohs.service;

import java.util.List;
import java.util.Map;

import com.jiangjie.ohs.dto.DataQueryResponse;
import com.jiangjie.ohs.exception.OhsException;

public interface OhsDataQueryService {

	public List<DataQueryResponse> queryBatchDataFileds(Map<String, Object> param) throws OhsException;

}
