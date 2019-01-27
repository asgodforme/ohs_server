package com.jiangjie.ohs.dto;

import java.sql.Timestamp;

/**
 * 前端传送过来的模块对象，与后端的数据库表对比，多了系统码和系统名
 * 
 * @author Administrator
 *
 */
public class Module {

	private int id;

	private int sysId;

	private String sysAlias;

	private String sysChineseNme;

	private String moduleName;

	private String moduleAlias;

	private Timestamp updateDate;

	private String updateUser;

	private Timestamp createDate;

	private String createUser;

	private int current;

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

	@Override
	public String toString() {
		return "Module [id=" + id + ", sysId=" + sysId + ", sysAlias=" + sysAlias + ", sysChineseNme=" + sysChineseNme
				+ ", moduleName=" + moduleName + ", moduleAlias=" + moduleAlias + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + ", createDate=" + createDate + ", createUser=" + createUser + "]";
	}

}
