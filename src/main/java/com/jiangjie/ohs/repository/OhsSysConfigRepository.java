package com.jiangjie.ohs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jiangjie.ohs.entity.OhsSysConfig;

public interface OhsSysConfigRepository extends JpaRepository<OhsSysConfig, Integer> {
	
	public List<OhsSysConfig> findBySysAlias(String sysAlias);
	public List<OhsSysConfig> findBySysChineseNme(String sysChineseNme);
	public List<OhsSysConfig> findBySchemaName(String schemaName);
	
	public List<OhsSysConfig> findBySysAliasAndSysChineseNme(String sysAlias, String sysChineseNme);

}
