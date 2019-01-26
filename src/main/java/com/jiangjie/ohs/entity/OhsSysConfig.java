package com.jiangjie.ohs.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * 系统配置表： 一个系统对应多个环境（sit, uat, pro等等） 一个系统对应多个功能模块
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsSysConfig {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	/** 创建时间 */
	private Timestamp createDate;
	/** 创建用户 */
	private String createUser;
	/** 修改时间 */
	private Timestamp updateDate;
	/** 修改用户 */
	private String updateUser;

	@Transient
	private int current;

	@Transient
	private int pageSize;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

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

	public OhsSysConfig() {
		super();
	}

	public OhsSysConfig(String sysAlias, String sysChineseNme) {
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

	@Override
	public String toString() {
		return "OhsSysConfig [id=" + id + ", sysAlias=" + sysAlias + ", sysChineseNme=" + sysChineseNme
				+ ", createDate=" + createDate + ", createUser=" + createUser + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + "]";
	}

}
