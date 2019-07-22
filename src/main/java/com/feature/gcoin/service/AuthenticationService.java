package com.feature.gcoin.service;

import com.feature.gcoin.common.exception.BusinessException;
import com.feature.gcoin.dto.reponse.UserLoginResponse;
import com.feature.gcoin.dto.request.LoginRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author TienNV
 * @CreatedDate Oct 9, 2017 11:09:36 AM
 */
public interface AuthenticationService {

	UserLoginResponse authenticate(LoginRequest loginRequest, HttpServletRequest request) throws BusinessException;

}
