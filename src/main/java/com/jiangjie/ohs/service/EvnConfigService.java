package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.Evn;
import com.jiangjie.ohs.exception.OhsException;

public interface EvnConfigService {

	List<Evn> getAllEvn(Evn evn) throws OhsException;

	Evn saveEvnConfig(Evn evn) throws OhsException;

	Evn deleteById(int id) throws OhsException;

	Evn updateById(Evn evn) throws OhsException;

}
