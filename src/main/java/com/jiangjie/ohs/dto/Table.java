package com.jiangjie.ohs.dto;

import java.sql.Timestamp;

public class Table {

	private int id;

	private String sysAlias;

	private String sysChineseNme;

	private String schemaName;

	private String tableName;

	private Timestamp updateDate;

	private String updateUser;

	private Timestamp createDate;

	private String createUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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
		return "Table [id=" + id + ", sysAlias=" + sysAlias + ", sysChineseNme=" + sysChineseNme + ", schemaName="
				+ schemaName + ", tableName=" + tableName + ", updateDate=" + updateDate + ", updateUser=" + updateUser
				+ ", createDate=" + createDate + ", createUser=" + createUser + "]";
	}

}
