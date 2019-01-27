package com.jiangjie.ohs.dto;

import java.sql.Timestamp;

/**
 * 环境信息
 * 
 * @author Administrator
 *
 */
public class Evn {

	/**
	 * 主键
	 */
	private int id;

	/**
	 * 归属系统名
	 */
	private String sysAlias;

	/** 归属系统码 */
	private String sysChineseNme;

	/**
	 * 环境类型： 1-数据库查询的环境，用户动态数据源的配置和加载。 2-自动测试的请求地址环境
	 */
	private String evnTyp;

	/**
	 * ip地址
	 */
	private String evnIp;

	/**
	 * 端口号
	 */
	private String evnPort;

	/**
	 * 接口名
	 */
	private String interfaceNme;

	/**
	 * 数据库用户名
	 */
	private String dbNme;

	/**
	 * 数据库密码
	 */
	private String dbPwd;

	/**
	 * 环境别名
	 */
	private String evnAlias;

	/**
	 * 环境名
	 */
	private String evnName;

	private Timestamp updateDate;

	private String updateUser;

	private Timestamp createDate;

	private String createUser;

	private int current;

	private int pageSize;

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

	public String getInterfaceNme() {
		return interfaceNme;
	}

	public void setInterfaceNme(String interfaceNme) {
		this.interfaceNme = interfaceNme;
	}

	public String getDbNme() {
		return dbNme;
	}

	public void setDbNme(String dbNme) {
		this.dbNme = dbNme;
	}

	public String getDbPwd() {
		return dbPwd;
	}

	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
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

	public String getEvnTyp() {
		return evnTyp;
	}

	public void setEvnTyp(String evnTyp) {
		this.evnTyp = evnTyp;
	}

	public String getEvnIp() {
		return evnIp;
	}

	public void setEvnIp(String evnIp) {
		this.evnIp = evnIp;
	}

	public String getEvnPort() {
		return evnPort;
	}

	public void setEvnPort(String evnPort) {
		this.evnPort = evnPort;
	}

	public String getEvnAlias() {
		return evnAlias;
	}

	public void setEvnAlias(String evnAlias) {
		this.evnAlias = evnAlias;
	}

	public String getEvnName() {
		return evnName;
	}

	public void setEvnName(String evnName) {
		this.evnName = evnName;
	}

}
