package com.feature.gcoin.common.constant;

public class GcoinException extends Exception {
	private Integer errCode;
	private String msg;
	
	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GcoinException(Integer errCode, String msg) {
		super();
		this.errCode = errCode;
		this.msg = msg;
	}
}
