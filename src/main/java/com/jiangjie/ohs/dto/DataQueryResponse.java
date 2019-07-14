package com.jiangjie.ohs.dto;

import java.util.List;
import java.util.Map;

/**
 * 数据查询返回对象
 */
public class DataQueryResponse {

	private String title; // 标题：批量开户批次信息表

	private List<Map<String, String>> dataHeader; // 对应数据库的表头: 英文名称=中文名称

	private List<Map<String, Object>> dataFields; // 实际数据

	private Map<String, Object> requestParam;

	public Map<String, Object> getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(Map<String, Object> requestParam) {
		this.requestParam = requestParam;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Map<String, String>> getDataHeader() {
		return dataHeader;
	}

	public void setDataHeader(List<Map<String, String>> dataHeader) {
		this.dataHeader = dataHeader;
	}

	public List<Map<String, Object>> getDataFields() {
		return dataFields;
	}

	public void setDataFields(List<Map<String, Object>> dataFields) {
		this.dataFields = dataFields;
	}

}
