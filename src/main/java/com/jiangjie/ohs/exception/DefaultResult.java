package com.jiangjie.ohs.exception;

public class DefaultResult implements Result {

	private int status;

	private String statusText;

	private Object payload;

	public DefaultResult() {
		super();
	}

	public DefaultResult(int status) {
		super();
		this.status = status;
	}

	public DefaultResult(int status, String statusText) {
		super();
		this.status = status;
		this.statusText = statusText;
	}

	public DefaultResult(int status, String statusText, Object payload) {
		super();
		this.status = status;
		this.statusText = statusText;
		this.payload = payload;
	}

	public DefaultResult(String statusText, Object payload) {
		super();
		this.statusText = statusText;
		this.payload = payload;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusText() {
		return statusText;
	}

	public Object getPayload() {
		return payload;
	}

	public static DefaultResult newResult() {
		return new DefaultResult(Result.OK, "请求成功");
	}

	public static DefaultResult newResult(Object object) {
		return new DefaultResult(Result.OK, "请求成功", object);
	}

	public static DefaultResult newFailResult(String errMsg) {
		return new DefaultResult(Result.NotOK, errMsg);
	}

}
