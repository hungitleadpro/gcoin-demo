package com.feature.gcoin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feature.gcoin.common.exception.BusinessException;
import com.feature.gcoin.common.util.Constants;
import com.feature.gcoin.dto.reponse.UserLoginResponse;
import com.feature.gcoin.dto.request.LoginRequest;
import com.feature.gcoin.dto.UserTokenState;
import com.feature.gcoin.service.AuthenticationService;
import com.feature.gcoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feature.gcoin.common.DeviceProvider;
import com.feature.gcoin.dto.reponse.Response;
import com.feature.gcoin.model.User;
import com.feature.gcoin.security.TokenHelper;
import com.feature.gcoin.service.impl.CustomUserDetailsServiceImpl;


@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private DeviceProvider deviceProvider;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response,
            Device device
    ) throws AuthenticationException, IOException, BusinessException {

        // Perform the security
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
////         Inject into security context
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token creation
        UserLoginResponse userLoginResponse = authenticationService.authenticate(loginRequest, request);
        String jws = tokenHelper.generateToken(userLoginResponse.getUserName(), device);
        Long expiresIn = tokenHelper.getExpiredIn(device);
        // Return the token
        return ResponseEntity.ok(new Response(Constants.SUCCESS, "Successful", new UserTokenState(userLoginResponse, jws, expiresIn)));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAuthenticationToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        String authToken = tokenHelper.getToken( request );

        Device device = deviceProvider.getCurrentDevice(request);

        if (authToken != null) {

            // TODO check user password last update
            String refreshedToken = tokenHelper.refreshToken(authToken, device);
            Long expiresIn = tokenHelper.getExpiredIn(device);

            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.accepted().body(userTokenState);
        }
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<Response> changePassword(@RequestBody PasswordChanger passwordChanger) {
        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        Map<String, String> result = new HashMap<>();
        result.put("result", "Successful");
        Response response = new Response();
        response.setResult(result);
        return ResponseEntity.accepted().body(response);
    }

    @RequestMapping(value = "/sign-out", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> signOut( HttpServletRequest request) {
        String authToken = tokenHelper.getToken( request );

        Device device = deviceProvider.getCurrentDevice(request);

        if (authToken != null) {

            // TODO check user password last update
            String refreshedToken = tokenHelper.refreshToken(authToken, device);
            Long expiresIn = tokenHelper.getExpiredIn(device);

            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.accepted().body(userTokenState);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Response> updateProfile(@RequestBody ProfileChange profileChange){
        userDetailsService.updateProfile(profileChange.name, profileChange.phone);
        Map<String, String> result = new HashMap<>();
        result.put("result", "Successful");
        Response response = new Response();
        response.setResult(result);
        return ResponseEntity.accepted().body(response);
    }

    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }

    static class ProfileChange{
        public String name;
        public String phone;
    }
}