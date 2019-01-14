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
 * 单表sql配置
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsSingleSqlConfig {

	/** 主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 归属模块id */
	private Integer moduleId;

	/** sql备注 */
	private String remark;

	/** 归属表id */
	private Integer tableId;

	/** 单表查询的sql语句 */
	private String singleTableSql;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ohs_single_query_sql", // 表名
			// joincolumns需要将此entity中的什么字段添加到表的什么字段，name是存储在多对多关系表中的字段名，referencedColumnName为此外键
			joinColumns = { @JoinColumn(name = "single_sql_id", referencedColumnName = "id")  }, 
			inverseJoinColumns = {@JoinColumn(name = "single_query_id", referencedColumnName = "id") })
	private List<OhsSingleQueryWhereInfo> OhsSingleQueryWhereInfos;

	private Timestamp createDate;

	private String createUser;

	private Timestamp updateDate;

	private String updateUser;

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

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

	public List<OhsSingleQueryWhereInfo> getOhsSingleQueryWhereInfos() {
		return OhsSingleQueryWhereInfos;
	}

	public void setOhsSingleQueryWhereInfos(List<OhsSingleQueryWhereInfo> ohsSingleQueryWhereInfos) {
		OhsSingleQueryWhereInfos = ohsSingleQueryWhereInfos;
	}

}
