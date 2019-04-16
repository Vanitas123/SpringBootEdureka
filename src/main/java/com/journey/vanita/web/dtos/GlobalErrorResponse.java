package com.journey.vanita.web.dtos;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class GlobalErrorResponse {

	private String errorMessage;
	private HttpStatus errorCode;
	public long timeStamp;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public HttpStatus getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}
	public long getTimeStamp() {
		return System.currentTimeMillis();
	}
	
	
}
