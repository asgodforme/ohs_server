package com.jiangjie.ohs.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jiangjie.ohs.entity.common.RelationUserInfo;

/**
 * 模块配置表
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsModuleConfig {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 归属系统id
	 */
	private int sysId;

	/**
	 * 模块名
	 */
	private String moduleName;

	/**
	 * 模块简称
	 */
	private String moduleAlias;

	/**
	 * 公共信息
	 */
	@Embedded
	private RelationUserInfo relationUserInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSysId() {
		return sysId;
	}

	public void setSysId(int sysId) {
		this.sysId = sysId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleAlias() {
		return moduleAlias;
	}

	public void setModuleAlias(String moduleAlias) {
		this.moduleAlias = moduleAlias;
	}

	public RelationUserInfo getRelationUserInfo() {
		return relationUserInfo;
	}

	public void setRelationUserInfo(RelationUserInfo relationUserInfo) {
		this.relationUserInfo = relationUserInfo;
	}

}
