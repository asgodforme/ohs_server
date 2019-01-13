package com.jiangjie.ohs.service;

import java.util.List;

import com.jiangjie.ohs.dto.Module;
import com.jiangjie.ohs.exception.OhsException;

public interface ModuleConfigService {

	public List<Module> getAllModule(Module Module) throws OhsException;
	
	public Module updateById(Module module) throws OhsException;
	
	public Module deleteById(Integer id) throws OhsException;
	
	public Module saveModuleConfig(Module module) throws OhsException;
}
