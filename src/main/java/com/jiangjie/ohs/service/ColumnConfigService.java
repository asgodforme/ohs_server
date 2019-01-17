package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.ColumnDTO;
import com.jiangjie.ohs.exception.OhsException;

public interface ColumnConfigService {

	List<ColumnDTO> getAllColumn(ColumnDTO column) throws OhsException ;

	ColumnDTO saveColumnConfig(ColumnDTO column) throws OhsException ;

	ColumnDTO deleteById(int parseInt) throws OhsException ;

	ColumnDTO updateById(ColumnDTO column) throws OhsException ;

}
