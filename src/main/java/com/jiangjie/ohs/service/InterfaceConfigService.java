package com.jiangjie.ohs.service;

import com.jiangjie.ohs.dto.Interface;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.exception.OhsException;

public interface InterfaceConfigService {

	PageResponse<Interface> getAllInterface(Interface interfaceObj) throws OhsException;

	Interface saveInterfaceConfig(Interface interfaceObj) throws OhsException;

	Interface deleteById(int parseInt) throws OhsException;

	Interface updateById(Interface interfaceObj) throws OhsException;

}
