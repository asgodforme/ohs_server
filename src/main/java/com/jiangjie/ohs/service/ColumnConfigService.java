package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.ColumnDTO;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.exception.OhsException;

public interface ColumnConfigService {

	PageResponse<ColumnDTO> getAllColumn(ColumnDTO column) throws OhsException ;
	
	List<ColumnDTO> getAllColumnList(ColumnDTO column) throws OhsException ;

	ColumnDTO saveColumnConfig(ColumnDTO column) throws OhsException ;

	ColumnDTO deleteById(int parseInt, String tokenName) throws OhsException ;

	ColumnDTO updateById(ColumnDTO column) throws OhsException ;

}
