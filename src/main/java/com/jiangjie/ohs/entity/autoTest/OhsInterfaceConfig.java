package com.jiangjie.ohs.entity.autoTest;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 接口配置
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsInterfaceConfig {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** 接口类型： restful接口， webservice接口 */
	private String interfaceType;

	/**
	 * 接口名
	 */
	private String interfaceName;

	/**
	 * 接口别名
	 */
	private String interfaceAlias;

	/** 归属系统id */
	private Integer sysId;

	/** 归属模块id */
	private Integer moduleId;

	/** 接口访问的路径 */
	private String urlPath;

	/** 如果是restful接口，接口请求方法 POST PUT GET DELETE */
	private String method;

	/**
	 * 请求报文模板
	 */
	private String requestTemplate;

	/**
	 * 响应报文模板
	 */
	private String responseTemplate;

	private Timestamp createDate;

	private String createUser;

	private Timestamp updateDate;

	private String updateUser;

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

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceAlias() {
		return interfaceAlias;
	}

	public void setInterfaceAlias(String interfaceAlias) {
		this.interfaceAlias = interfaceAlias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
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
