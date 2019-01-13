package com.jiangjie.ohs.entity.dataEntity;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 多表sql配置
 * 
 * @author Administrator
 *
 */
public class OhsMultiSQLConfig {

	/** 主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 归属模块id */
	private Integer moduleId;

	/** sql备注 */
	private String remark;

	/** 主表id */
	private Integer mainTableId;

	/** 关联表id */
	private Integer relateTableId;

	/** 超过第三张的id */
	private Integer multiTableId;

	/** 多表查询的sql语句 */
	private String mutilTableSql;

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

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMainTableId() {
		return mainTableId;
	}

	public void setMainTableId(Integer mainTableId) {
		this.mainTableId = mainTableId;
	}

	public Integer getRelateTableId() {
		return relateTableId;
	}

	public void setRelateTableId(Integer relateTableId) {
		this.relateTableId = relateTableId;
	}

	public Integer getMultiTableId() {
		return multiTableId;
	}

	public void setMultiTableId(Integer multiTableId) {
		this.multiTableId = multiTableId;
	}

	public String getMutilTableSql() {
		return mutilTableSql;
	}

	public void setMutilTableSql(String mutilTableSql) {
		this.mutilTableSql = mutilTableSql;
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

}
