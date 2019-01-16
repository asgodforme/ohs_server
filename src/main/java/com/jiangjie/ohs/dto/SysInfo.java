package com.jiangjie.ohs.dto;

import java.sql.Timestamp;
import java.util.List;

import com.jiangjie.ohs.entity.dataEntity.OhsTableConfig;

/**
 * 系统信息
 * 
 * @author Administrator
 *
 */
public class SysInfo {

	private Integer id;

	/**
	 * 系统别名，简称
	 */
	private String sysAlias;

	/**
	 * 系统中文全称
	 */
	private String sysChineseNme;

	/**
	 * 数据库schema名
	 */
	private String schemaName;

	private List<OhsTableConfig> ohsTableConfigs;

	/** 创建时间 */
	private Timestamp createDate;
	/** 创建用户 */
	private String createUser;
	/** 修改时间 */
	private Timestamp updateDate;
	/** 修改用户 */
	private String updateUser;

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public SysInfo() {
		super();
	}

	public SysInfo(String sysAlias, String sysChineseNme) {
		super();
		this.sysAlias = sysAlias;
		this.sysChineseNme = sysChineseNme;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSysAlias() {
		return sysAlias;
	}

	public void setSysAlias(String sysAlias) {
		this.sysAlias = sysAlias;
	}

	public String getSysChineseNme() {
		return sysChineseNme;
	}

	public void setSysChineseNme(String sysChineseNme) {
		this.sysChineseNme = sysChineseNme;
	}

	public List<OhsTableConfig> getOhsTableConfigs() {
		return ohsTableConfigs;
	}

	public void setOhsTableConfigs(List<OhsTableConfig> ohsTableConfigs) {
		this.ohsTableConfigs = ohsTableConfigs;
	}

	@Override
	public String toString() {
		return "SysInfo [id=" + id + ", sysAlias=" + sysAlias + ", sysChineseNme=" + sysChineseNme + ", schemaName="
				+ schemaName + ", ohsTableConfigs=" + ohsTableConfigs + ", createDate=" + createDate + ", createUser="
				+ createUser + ", updateDate=" + updateDate + ", updateUser=" + updateUser + "]";
	}

}