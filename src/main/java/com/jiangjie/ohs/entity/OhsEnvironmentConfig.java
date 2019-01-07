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
	private int id;

	/**
	 * 归属系统id
	 */
	private int sysId;

	/**
	 * 环境类型： 1-数据库查询的环境，用户动态数据源的配置和加载。 2-自动测试的请求地址环境
	 */
	private String envTyp;

	/**
	 * ip地址
	 */
	private String envIp;

	/**
	 * 端口号
	 */
	private String envPort;

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
	private String envAlias;

	/**
	 * 环境名
	 */
	private String envName;

	/**
	 * 公共信息
	 */
	@Embedded
	private RelationUserInfo relationUserInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSysId() {
		return sysId;
	}

	public void setSysId(int sysId) {
		this.sysId = sysId;
	}

	public String getEnvTyp() {
		return envTyp;
	}

	public void setEnvTyp(String envTyp) {
		this.envTyp = envTyp;
	}

	public String getEnvIp() {
		return envIp;
	}

	public void setEnvIp(String envIp) {
		this.envIp = envIp;
	}

	public String getEnvPort() {
		return envPort;
	}

	public void setEnvPort(String envPort) {
		this.envPort = envPort;
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

	public String getEnvAlias() {
		return envAlias;
	}

	public void setEnvAlias(String envAlias) {
		this.envAlias = envAlias;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public RelationUserInfo getRelationUserInfo() {
		return relationUserInfo;
	}

	public void setRelationUserInfo(RelationUserInfo relationUserInfo) {
		this.relationUserInfo = relationUserInfo;
	}

}
