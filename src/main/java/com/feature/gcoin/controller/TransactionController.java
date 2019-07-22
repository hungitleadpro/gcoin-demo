package com.feature.gcoin.controller;

import com.feature.gcoin.common.util.Constants;
import com.feature.gcoin.dto.ServicesDTO;
import com.feature.gcoin.dto.TransactionLogDTO;
import com.feature.gcoin.dto.reponse.InformationUser;
import com.feature.gcoin.dto.reponse.Response;
import com.feature.gcoin.dto.reponse.TransactionLogReponse;
import com.feature.gcoin.model.User;
import com.feature.gcoin.security.TokenHelper;
import com.feature.gcoin.service.ServicesService;
import com.feature.gcoin.service.TransactionLogService;
import com.feature.gcoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
    @Autowired
    private TransactionLogService transactionLogService;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    private UserService userService;

    @RequestMapping(method = GET, value = "/listByUser")
    public ResponseEntity<?> listByUser(HttpServletRequest req) {
        String token = tokenHelper.getToken(req);
        String username = tokenHelper.getUsernameFromToken(token);
        User user = userService.findByUsername(username);
        TransactionLogReponse transactionLogReponse = transactionLogService.search(user.getId());
        return ResponseEntity.ok(new Response(Constants.SUCCESS, "Successful", transactionLogReponse));
    }
}
