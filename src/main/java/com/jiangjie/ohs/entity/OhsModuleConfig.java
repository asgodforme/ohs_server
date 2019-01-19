package com.jiangjie.ohs.entity;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.jiangjie.ohs.entity.common.RelationUserInfo;
import com.jiangjie.ohs.entity.dataEntity.OhsTableConfig;

/**
 * 模块配置表
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsModuleConfig {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 归属系统id
	 */
	private Integer sysId;

	/**
	 * 模块名
	 */
	private String moduleName;

	/**
	 * 模块简称
	 */
	private String moduleAlias;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ohs_module_table_config", joinColumns = {
			@JoinColumn(name = "module_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "table_id", referencedColumnName = "id") })
	private List<OhsTableConfig> ohsTableConfigs;

	public List<OhsTableConfig> getOhsTableConfigs() {
		return ohsTableConfigs;
	}

	public void setOhsTableConfigs(List<OhsTableConfig> ohsTableConfigs) {
		this.ohsTableConfigs = ohsTableConfigs;
	}

	/**
	 * 公共信息
	 */
	@Embedded
	private RelationUserInfo relationUserInfo;

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

	public RelationUserInfo getRelationUserInfo() {
		return relationUserInfo;
	}

	public void setRelationUserInfo(RelationUserInfo relationUserInfo) {
		this.relationUserInfo = relationUserInfo;
	}

	@Override
	public String toString() {
		return "OhsModuleConfig [id=" + id + ", sysId=" + sysId + ", moduleName=" + moduleName + ", moduleAlias="
				+ moduleAlias + ", ohsTableConfigs=" + ohsTableConfigs + ", relationUserInfo=" + relationUserInfo + "]";
	}

}
