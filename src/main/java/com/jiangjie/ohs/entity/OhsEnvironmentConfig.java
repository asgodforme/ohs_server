package com.jiangjie.ohs.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jiangjie.ohs.entity.common.RelationUserInfo;

/**
 * 环境配置表
 * 
 * @author Administrator
 *
 */
@Entity
public class OhsEnvironmentConfig {

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
	 * 数据库名称
	 */
	private String dbSchema;

	/**
	 * 数据库用户名
	 */
	private String dbNme;

	/**
	 * 数据库密码
	 */
	private String dbPwd;

	/**
	 * 数据库类型
	 */
	private String dbType;

	/**
	 * 环境别名
	 */
	private String evnAlias;

	/**
	 * 环境名
	 */
	private String evnName;

	/**
	 * 公共信息
	 */
	@Embedded
	private RelationUserInfo relationUserInfo;

	public String getDbSchema() {
		return dbSchema;
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

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

	public String getEvnIp() {
		return evnIp;
	}

	public void setEvnIp(String evnIp) {
		this.evnIp = evnIp;
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

	public RelationUserInfo getRelationUserInfo() {
		return relationUserInfo;
	}

	public void setRelationUserInfo(RelationUserInfo relationUserInfo) {
		this.relationUserInfo = relationUserInfo;
	}

	public String getEvnTyp() {
		return evnTyp;
	}

	public void setEvnTyp(String evnTyp) {
		this.evnTyp = evnTyp;
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
