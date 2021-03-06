package com.jiangjie.ohs.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Interface {

	private String id;

	private String sysAlias;

	private String sysChineseNme;

	private String moduleAlias;

	private String moduleName;

	private String testSeq;

	private String interfaceAlias;

	private String interfaceName;

	private String interfaceType;

	private String urlPath;

	private String method;

	/**
	 * 请求报文模板
	 */
	private String requestTemplate;

	/**
	 * 响应报文模板
	 */
	private String responseTemplate;

	private int current;

	private int pageSize;

	private String isTest;

	private List<String> parameters;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp createDate;

	private String createUser;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp updateDate;

	private String updateUser;

	private List<EnvironmentInfo> environmentInfos;

	private String targetServerId;

	private Integer singleRecordsId;

	private String tokenName;

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public Integer getSingleRecordsId() {
		return singleRecordsId;
	}

	public void setSingleRecordsId(Integer singleRecordsId) {
		this.singleRecordsId = singleRecordsId;
	}

	public String getTargetServerId() {
		return targetServerId;
	}

	public void setTargetServerId(String targetServerId) {
		this.targetServerId = targetServerId;
	}

	public class EnvironmentInfo {
		private Integer id;
		private String evnName;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getEvnName() {
			return evnName;
		}

		public void setEvnName(String evnName) {
			this.evnName = evnName;
		}

	}

	public List<EnvironmentInfo> getEnvironmentInfos() {
		return environmentInfos;
	}

	public void setEnvironmentInfos(List<EnvironmentInfo> environmentInfos) {
		this.environmentInfos = environmentInfos;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public String getIsTest() {
		return isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}

	public String getTestSeq() {
		return testSeq;
	}

	public void setTestSeq(String testSeq) {
		this.testSeq = testSeq;
	}

	public String getRequestTemplate() {
		return requestTemplate;
	}

	public void setRequestTemplate(String requestTemplate) {
		this.requestTemplate = requestTemplate;
	}

	public String getResponseTemplate() {
		return responseTemplate;
	}

	public void setResponseTemplate(String responseTemplate) {
		this.responseTemplate = responseTemplate;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
