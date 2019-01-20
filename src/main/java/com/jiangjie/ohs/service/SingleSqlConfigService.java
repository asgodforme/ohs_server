package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.SingleSql;
import com.jiangjie.ohs.exception.OhsException;

public interface SingleSqlConfigService {

	List<SingleSql> getAllSingleSql(SingleSql singleSql) throws OhsException;

	SingleSql saveSingleSqlConfig(SingleSql singleSql) throws OhsException;

	SingleSql deleteById(int parseInt) throws OhsException;

	SingleSql updateById(SingleSql singleSql) throws OhsException;

}
