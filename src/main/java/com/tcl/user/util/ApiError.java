package com.tcl.user.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.servlet.http.HttpServletRequest;


@ApiModel(description = "Uniform error")
public class ApiError {

	private String uri;

	private String method;

	private Integer errorCode;

	private String errorMessage;

	private String moreInfo;

	public ApiError(String uri, String method, Integer errorCode, String errorMessage, String moreInfo){
		this.uri = uri;
		this.method = method;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.moreInfo = moreInfo;
	}

	public ApiError(HttpServletRequest request, Integer errorCode, String errorMessage, String moreInfo){
		this.uri = request.getRequestURI();
		this.method = request.getMethod();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.moreInfo = moreInfo;
	}

	@ApiModelProperty(value = "invoked uri")
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	@ApiModelProperty(value = "invoked method")
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	@ApiModelProperty(value = "ErrorCode")
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	@ApiModelProperty(value = "ErrorMessage")
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getMoreInfo() {
		return moreInfo;
	}
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}


}
