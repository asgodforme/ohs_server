package com.jiangjie.ohs.entity.dataEntity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 单表查询where条件信息
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsSingleQueryWhereInfo {

	/** 主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 单表sql主键
	 */
	private Integer singleSqlId;

	/** 键 */
	private String keyInfo;

	/** 键中文名 */
	private String keyChnInfo;

	/** 比较关系符号 = > < >= <= */
	private String symbol;

	/** 值 */
	private String valueInfo;

	private Timestamp createDate;

	private String createUser;

	private Timestamp updateDate;

	private String updateUser;

	public String getKeyChnInfo() {
		return keyChnInfo;
	}

	public void setKeyChnInfo(String keyChnInfo) {
		this.keyChnInfo = keyChnInfo;
	}

	public Integer getSingleSqlId() {
		return singleSqlId;
	}

	public void setSingleSqlId(Integer singleSqlId) {
		this.singleSqlId = singleSqlId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyInfo() {
		return keyInfo;
	}

	public void setKeyInfo(String keyInfo) {
		this.keyInfo = keyInfo;
	}

	public String getValueInfo() {
		return valueInfo;
	}

	public void setValueInfo(String valueInfo) {
		this.valueInfo = valueInfo;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
