package com.jiangjie.ohs.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jiangjie.ohs.entity.common.RelationUserInfo;

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
	private int id;

	/**
	 * 系统别名，简称
	 */
	private String sysAlias;

	/**
	 * 系统中文全称
	 */
	private String sysChineseNme;

	/**
	 * 公共的信息
	 */
	@Embedded
	private RelationUserInfo relationUserInfo;

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

	public RelationUserInfo getRelationUserInfo() {
		return relationUserInfo;
	}

	public void setRelationUserInfo(RelationUserInfo relationUserInfo) {
		this.relationUserInfo = relationUserInfo;
	}

}
