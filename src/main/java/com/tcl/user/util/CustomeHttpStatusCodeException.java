package com.tcl.user.util;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @description note that: all kinds of exception's getMessage value is set to ErrorDesc.
 * so be sure to use ex.getErrorDesc instead of ex.getMessage
 * @author hzy
 *
 */
public class CustomeHttpStatusCodeException extends RestClientException {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_CHARSET = "UTF-8";


	private static final String DEFAULT_STATUS_TEXT = "custome error";

	private static final String DEFAULT_ERROR_MESSEGE = "unknow service error";

	private static final int DEFAULT_ERROR_CODE = -1;

	private final int statusCode;

	private final int errorCode;

	private final String statusText;

	private final String errorDesc;

	private final byte[] responseBody;

	private final HttpHeaders responseHeaders;

	private final String responseCharset;
	/**
	 * Construct a new instance of {@code CustomeHttpStatusCodeException} based on an
	 *  HttpStatus.
	 * @param statusCode the status code
	 */
	public CustomeHttpStatusCodeException(int statusCode) {
		this(statusCode, DEFAULT_STATUS_TEXT, DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSEGE, null, null, null);
	}

	public CustomeHttpStatusCodeException(int statusCode, byte[] responseBody) {
		this(statusCode, DEFAULT_STATUS_TEXT, DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSEGE, null, responseBody, null);
	}

	public CustomeHttpStatusCodeException(int statusCode, String errorMeesage, byte[] responseBody) {
		this(statusCode, DEFAULT_STATUS_TEXT, DEFAULT_ERROR_CODE, errorMeesage, null, responseBody, null);
	}

	/**
	 * Construct a new instance of {@code CustomeHttpStatusCodeException} based on an
	 * HttpStatus and status text.
	 * @param statusCode the status code
	 * @param statusText the status text
	 * @param errorCode  the error code
	 */
	public CustomeHttpStatusCodeException(int statusCode, String statusText, int errorCode) {
		this(statusCode, statusText, errorCode, DEFAULT_ERROR_MESSEGE, null, null, null);
	}

	public CustomeHttpStatusCodeException(int statusCode, String errorDesc) {
		this(statusCode, DEFAULT_STATUS_TEXT, DEFAULT_ERROR_CODE, errorDesc, null, null, null);
	}

	public CustomeHttpStatusCodeException(int statusCode, String statusText,
                                          int errorCode, String errorDesc) {
		this(statusCode, statusText, errorCode, errorDesc, null, null, null);
	}

	public CustomeHttpStatusCodeException(int statusCode, String statusText,
                                          String errorDesc) {
		this(statusCode, statusText, DEFAULT_ERROR_CODE, errorDesc, null, null, null);
	}

	public CustomeHttpStatusCodeException(int statusCode, String statusText,
                                          String errorDesc, byte[] responseBody) {
		this(statusCode, statusText, DEFAULT_ERROR_CODE, errorDesc, null, responseBody, null);
	}

	/**
	 * Construct a new instance of {@code CustomeHttpStatusCodeException} based on an
	 * HttpStatus, status text, and response body content.
	 * @param statusCode the status code
	 * @param statusText the status text
	 * @param responseBody the response body content, may be {@code null}
	 * @param responseCharset the response body charset, may be {@code null}
	 */
	protected CustomeHttpStatusCodeException(
        int statusCode, String statusText, byte[] responseBody, Charset responseCharset) {

		this(statusCode, statusText, -1, DEFAULT_ERROR_MESSEGE, null, responseBody, responseCharset);
	}

	/**
	 * Construct a new instance of {@code CustomeHttpStatusCodeException} based on an
	 * HttpStatus, status text, and response body content.
	 * @param statusCode the status code
	 * @param statusText the status text
	 * @param responseHeaders the response headers, may be {@code null}
	 * @param responseBody the response body content, may be {@code null}
	 * @param responseCharset the response body charset, may be {@code null}
	 * @since 3.1.2
	 */
	protected CustomeHttpStatusCodeException(int statusCode, String statusText,
                                             int errorCode, String errorDesc, HttpHeaders responseHeaders,
                                             byte[] responseBody, Charset responseCharset) {

		super(statusCode + " " + statusText);
		this.statusCode = statusCode;
		this.statusText = statusText;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.responseHeaders = responseHeaders;
		this.responseBody = responseBody != null ? responseBody : new byte[0];
		this.responseCharset = responseCharset != null ? responseCharset.name() : DEFAULT_CHARSET;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public byte[] getResponseBody() {
		return responseBody;
	}

	public HttpHeaders getResponseHeaders() {
		return responseHeaders;
	}

	public String getResponseCharset() {
		return responseCharset;
	}

	/**
	 * Return the response body as a string.
	 */
	public String getResponseBodyAsString() {
		try {
			return new String(this.responseBody, this.responseCharset);
		}
		catch (UnsupportedEncodingException ex) {
			// should not occur
			throw new IllegalStateException(ex);
		}
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}
}
