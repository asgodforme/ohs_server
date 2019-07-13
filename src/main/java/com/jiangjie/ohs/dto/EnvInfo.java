package com.jiangjie.ohs.dto;

/**
 * 环境信息
 * 
 * @author Administrator
 *
 */
public class EnvInfo {

	private String envId; // 环境主键

	private String envAlias; // 环境别名

	public String getEnvId() {
		return envId;
	}

	public void setEnvId(String envId) {
		this.envId = envId;
	}

	public String getEnvAlias() {
		return envAlias;
	}

	public void setEnvAlias(String envAlias) {
		this.envAlias = envAlias;
	}

}
