package com.jiangjie.ohs.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Testsuit {

	private String id;

	private String sysAlias;

	private String sysChineseNme;

	private String moduleAlias;

	private String moduleName;

	private String interfaceAlias;

	private String interfaceName;

	/** 测试案列名称 */
	private String testsuitName;

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

	/** 请求数据域 */
	private String requestData;

	/** 响应数据域 */
	private String responseData;

	private int current;

	private int pageSize;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp createDate;

	private String createUser;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp updateDate;

	private String updateUser;

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getModuleAlias() {
		return moduleAlias;
	}

	public void setModuleAlias(String moduleAlias) {
		this.moduleAlias = moduleAlias;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getInterfaceAlias() {
		return interfaceAlias;
	}

	public void setInterfaceAlias(String interfaceAlias) {
		this.interfaceAlias = interfaceAlias;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getTestsuitName() {
		return testsuitName;
	}

	public void setTestsuitName(String testsuitName) {
		this.testsuitName = testsuitName;
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

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
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
