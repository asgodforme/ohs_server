package com.jiangjie.ohs.exception;

public class OhsException extends Exception {

	private static final long serialVersionUID = 311686806685628671L;

	private String message;
	
	public OhsException() {
		super();
	}

	public OhsException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
