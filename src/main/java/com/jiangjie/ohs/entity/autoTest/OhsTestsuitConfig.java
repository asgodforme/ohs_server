package com.jiangjie.ohs.entity.autoTest;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 测试案列配置
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsTestsuitConfig {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 归属系统id */
	private Integer sysId;

	/** 归属模块id */
	private Integer moduleId;

	/** 测试案列名称 */
	private String testsuitName;

	/**
	 * 版本号
	 */
	private String versionNo;

	/**
	 * 前置操作
	 */
	private String preOprUrl;

	/**
	 * 前置请求数据域
	 */
	private String preReqOprData;

	/**
	 * 前置响应解析正则
	 */
	private String preRspDataRegx;

	/**
	 * 后置操作
	 */
	private String afterOperUrl;

	/**
	 * 后置请求数据域
	 */
	private String afterReqOprData;

	/**
	 * 后置响应解析正则
	 */
	private String afterRspDataRegx;

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

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getTestsuitName() {
		return testsuitName;
	}

	public void setTestsuitName(String testsuitName) {
		this.testsuitName = testsuitName;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getPreOprUrl() {
		return preOprUrl;
	}

	public void setPreOprUrl(String preOprUrl) {
		this.preOprUrl = preOprUrl;
	}

	public String getPreReqOprData() {
		return preReqOprData;
	}

	public void setPreReqOprData(String preReqOprData) {
		this.preReqOprData = preReqOprData;
	}

	public String getPreRspDataRegx() {
		return preRspDataRegx;
	}

	public void setPreRspDataRegx(String preRspDataRegx) {
		this.preRspDataRegx = preRspDataRegx;
	}

	public String getAfterOperUrl() {
		return afterOperUrl;
	}

	public void setAfterOperUrl(String afterOperUrl) {
		this.afterOperUrl = afterOperUrl;
	}

	public String getAfterReqOprData() {
		return afterReqOprData;
	}

	public void setAfterReqOprData(String afterReqOprData) {
		this.afterReqOprData = afterReqOprData;
	}

	public String getAfterRspDataRegx() {
		return afterRspDataRegx;
	}

	public void setAfterRspDataRegx(String afterRspDataRegx) {
		this.afterRspDataRegx = afterRspDataRegx;
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
