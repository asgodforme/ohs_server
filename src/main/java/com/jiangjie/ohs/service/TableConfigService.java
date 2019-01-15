package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.Table;
import com.jiangjie.ohs.exception.OhsException;

public interface TableConfigService {

	List<Table> getAllTable(Table table) throws OhsException;

	Table saveTableConfig(Table table) throws OhsException;

	Table deleteById(int parseInt) throws OhsException;

	Table updateById(Table table) throws OhsException ;

}
