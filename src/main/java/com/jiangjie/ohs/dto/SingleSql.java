package com.jiangjie.ohs.dto;

import java.sql.Timestamp;
import java.util.List;

import com.jiangjie.ohs.entity.dataEntity.OhsSingleQueryWhereInfo;

public class SingleSql {

	private Integer id;

	private String sysAlias;

	private String sysChineseNme;

	private String moduleName;

	private String moduleAlias;

	private String tableName;

	private String tableChnName;

	private String remark;

	private String singleTableSql;

	private String columnName;

	private String columnAlias;

	private List<OhsSingleQueryWhereInfo> ohsSingleQueryWhereInfoLst;

	private Timestamp createDate;

	private String createUser;

	private Timestamp updateDate;

	private String updateUser;

	private int current;

	private int pageSize;

	private int offsetSize;

	public int getOffsetSize() {
		return offsetSize;
	}

	public void setOffsetSize(int offsetSize) {
		this.offsetSize = offsetSize;
	}

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

	public List<OhsSingleQueryWhereInfo> getOhsSingleQueryWhereInfoLst() {
		return ohsSingleQueryWhereInfoLst;
	}

	public void setOhsSingleQueryWhereInfoLst(List<OhsSingleQueryWhereInfo> ohsSingleQueryWhereInfoLst) {
		this.ohsSingleQueryWhereInfoLst = ohsSingleQueryWhereInfoLst;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableChnName() {
		return tableChnName;
	}

	public void setTableChnName(String tableChnName) {
		this.tableChnName = tableChnName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSingleTableSql() {
		return singleTableSql;
	}

	public void setSingleTableSql(String singleTableSql) {
		this.singleTableSql = singleTableSql;
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

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnAlias() {
		return columnAlias;
	}

	public void setColumnAlias(String columnAlias) {
		this.columnAlias = columnAlias;
	}

	@Override
	public String toString() {
		return "SingleSql [id=" + id + ", moduleName=" + moduleName + ", moduleAlias=" + moduleAlias + ", tableName="
				+ tableName + ", tableChnName=" + tableChnName + ", remark=" + remark + ", singleTableSql="
				+ singleTableSql + ", createDate=" + createDate + ", createUser=" + createUser + ", updateDate="
				+ updateDate + ", updateUser=" + updateUser + "]";
	}

}
