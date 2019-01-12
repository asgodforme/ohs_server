package com.jiangjie.ohs.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

	@ExceptionHandler(OhsException.class)
	@ResponseBody
	public Result handleOhsException(HttpServletRequest request, Exception ex) {
		return DefaultResult.newFailResult(ex.getMessage());
	}
}
