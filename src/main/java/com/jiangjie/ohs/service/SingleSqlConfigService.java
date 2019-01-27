package com.jiangjie.ohs.service;

import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.dto.SingleSql;
import com.jiangjie.ohs.exception.OhsException;

public interface SingleSqlConfigService {

	PageResponse<SingleSql> getAllSingleSql(SingleSql singleSql) throws OhsException;

	SingleSql saveSingleSqlConfig(SingleSql singleSql) throws OhsException;

	SingleSql deleteById(int parseInt) throws OhsException;

	SingleSql updateById(SingleSql singleSql) throws OhsException;

}
