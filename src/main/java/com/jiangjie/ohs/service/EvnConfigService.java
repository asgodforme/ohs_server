package com.jiangjie.ohs.service;

import com.jiangjie.ohs.dto.Evn;
import com.jiangjie.ohs.dto.PageResponse;
import com.jiangjie.ohs.exception.OhsException;

public interface EvnConfigService {

	PageResponse<Evn> getAllEvn(Evn evn) throws OhsException;

	Evn saveEvnConfig(Evn evn) throws OhsException;

	Evn deleteById(int id) throws OhsException;

	Evn updateById(Evn evn) throws OhsException;

}
