package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.Column;
import com.jiangjie.ohs.exception.OhsException;

public interface EnumValueConfigService {

	List<Column> getAllEnumValue(Column column) throws OhsException;

	Column saveEnumValueConfig(Column column) throws OhsException;

	Column deleteById(int parseInt) throws OhsException;

	Column updateById(Column column) throws OhsException;

}
