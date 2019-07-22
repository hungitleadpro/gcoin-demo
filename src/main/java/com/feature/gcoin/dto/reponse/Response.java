package com.feature.gcoin.dto.reponse;

public class Response {
    private Integer errCode;//200 la thanh cong
    private String msg;
    private Object result;

    public Response() {
    }

    public Response(Integer errCode, String msg, Object result) {
        this.errCode = errCode;
        this.msg = msg;
        this.result = result;
    }

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
