package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.ColumnDTO;
import com.jiangjie.ohs.exception.OhsException;

public interface EnumValueConfigService {

	List<ColumnDTO> getAllEnumValue(ColumnDTO column) throws OhsException;

	ColumnDTO saveEnumValueConfig(ColumnDTO column) throws OhsException;

	ColumnDTO deleteById(int parseInt) throws OhsException;

	ColumnDTO updateById(ColumnDTO column) throws OhsException;

}
