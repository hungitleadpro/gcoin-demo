/**
 * 
 */
package com.feature.gcoin.common.exceptionhandling;

/**
 * @author tiennv@gmail.com
 *
 */
public class ErrorInfo {
	private String code;
	private String field;
	private String message;

	public ErrorInfo() {
	}

	public ErrorInfo(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ErrorInfo(String code, String field, String message) {
		this.code = code;
		this.field = field;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}