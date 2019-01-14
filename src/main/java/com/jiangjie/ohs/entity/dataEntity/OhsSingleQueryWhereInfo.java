package com.jiangjie.ohs.entity.dataEntity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

	/** 键 */
	private String keyInfo;

	/** 比较关系符号 = > < >= <= */
	private String symbol;

	/** 值 */
	private String valueInfo;

	/**
	 * 维护多对多关系
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ohs_single_query_sql", // 表名
			// joincolumns需要将此entity中的什么字段添加到表的什么字段，name是存储在多对多关系表中的字段名，referencedColumnName为此外键
			joinColumns = { @JoinColumn(name = "single_query_id", referencedColumnName = "id") },
			inverseJoinColumns = { @JoinColumn(name="single_sql_id", referencedColumnName = "id")}
			)
	private List<OhsSingleSqlConfig> ohsSingleSqlConfigs;

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

	public List<OhsSingleSqlConfig> getOhsSingleSqlConfigs() {
		return ohsSingleSqlConfigs;
	}

	public void setOhsSingleSqlConfigs(List<OhsSingleSqlConfig> ohsSingleSqlConfigs) {
		this.ohsSingleSqlConfigs = ohsSingleSqlConfigs;
	}

}
