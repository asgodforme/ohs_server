package com.jiangjie.ohs.dto;

public class TestsuitRecords {

	private Integer id;

	private Integer testsuitId;

	private String sysAlias;

	private String sysChineseNme;

	private String moduleAlias;

	private String moduleName;

	private String interfaceAlias;

	private String interfaceName;

	private Integer testSeq;

	public Integer getTestsuitId() {
		return testsuitId;
	}

	public void setTestsuitId(Integer testsuitId) {
		this.testsuitId = testsuitId;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getTestSeq() {
		return testSeq;
	}

	public void setTestSeq(Integer testSeq) {
		this.testSeq = testSeq;
	}

}
