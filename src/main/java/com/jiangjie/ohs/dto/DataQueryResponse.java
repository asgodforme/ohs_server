package com.jiangjie.ohs.dto;

import java.util.List;
import java.util.Map;


/**
 * 数据查询返回对象
 */
public class DataQueryResponse {

	private String title; // 标题：批量开户批次信息表

	private List<String> dataHeader; // 对应数据库的表头

	private List<Map<String, String>> dataFields; // 实际数据

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getDataHeader() {
		return dataHeader;
	}

	public void setDataHeader(List<String> dataHeader) {
		this.dataHeader = dataHeader;
	}

	public List<Map<String, String>> getDataFields() {
		return dataFields;
	}

	public void setDataFields(List<Map<String, String>> dataFields) {
		this.dataFields = dataFields;
	}

}
