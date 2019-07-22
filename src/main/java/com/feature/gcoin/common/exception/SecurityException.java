package com.feature.gcoin.common.exception;
/**
 * 
 * @author tiennv@gmail.com
 *
 */
public class SecurityException extends Exception{

	private static final long serialVersionUID = 1L;

	private String mesage;
	private String code;
	
	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code= code;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
