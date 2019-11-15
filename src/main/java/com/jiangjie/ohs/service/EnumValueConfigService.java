package com.jiangjie.ohs.service;

import com.jiangjie.ohs.dto.ColumnDTO;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.exception.OhsException;

public interface EnumValueConfigService {

	PageResponse<ColumnDTO> getAllEnumValue(ColumnDTO column) throws OhsException;

	ColumnDTO saveEnumValueConfig(ColumnDTO column) throws OhsException;

	ColumnDTO deleteById(int parseInt, String tokenName) throws OhsException;

	ColumnDTO updateById(ColumnDTO column) throws OhsException;

}
