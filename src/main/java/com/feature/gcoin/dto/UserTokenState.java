package com.feature.gcoin.dto;

import com.feature.gcoin.dto.reponse.UserLoginResponse;

public class UserTokenState {
    private String access_token;
    private Long expires_in;
    private UserLoginResponse userLoginResponse;

    public UserTokenState() {
        this.access_token = null;
        this.expires_in = null;
        this.userLoginResponse = null;
    }

    public UserTokenState(UserLoginResponse userLoginResponse, String access_token, Long expires_in) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.userLoginResponse = userLoginResponse;
    }

    public UserTokenState(String refreshedToken, Long expiresIn) {
        this.access_token = refreshedToken;
        this.expires_in = expiresIn;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public UserLoginResponse getUserLoginResponse() {
        return userLoginResponse;
    }

    public void setUserLoginResponse(UserLoginResponse userLoginResponse) {
        this.userLoginResponse = userLoginResponse;
    }
}