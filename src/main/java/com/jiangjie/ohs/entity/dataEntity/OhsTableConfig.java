package com.jiangjie.ohs.entity.dataEntity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.jiangjie.ohs.entity.OhsModuleConfig;

/**
 * 表配置
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsTableConfig {

	/** 主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 归属系统id */
	private Integer sysId;

	/** 表schema名 */
	private String schemaName;

	/** 表名 */
	private String tableName;

	/**
	 * 表中文名
	 */
	private String tableChnName;

	@ManyToMany(mappedBy = "ohsTableConfigs")
	private List<OhsModuleConfig> ohsModuleConfigs;

	private Timestamp createDate;

	private String createUser;

	private Timestamp updateDate;

	private String updateUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
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

	public String getTableChnName() {
		return tableChnName;
	}

	public void setTableChnName(String tableChnName) {
		this.tableChnName = tableChnName;
	}

	public List<OhsModuleConfig> getOhsModuleConfigs() {
		return ohsModuleConfigs;
	}

	public void setOhsModuleConfigs(List<OhsModuleConfig> ohsModuleConfigs) {
		this.ohsModuleConfigs = ohsModuleConfigs;
	}

	@Override
	public String toString() {
		return "OhsTableConfig [id=" + id + ", sysId=" + sysId + ", schemaName=" + schemaName + ", tableName="
				+ tableName + ", tableChnName=" + tableChnName + ", ohsModuleConfigs=" + ohsModuleConfigs
				+ ", createDate=" + createDate + ", createUser=" + createUser + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + "]";
	}

}
