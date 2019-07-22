package com.feature.gcoin.controller;

import com.feature.gcoin.common.constant.Const;
import com.feature.gcoin.common.util.Constants;
import com.feature.gcoin.dto.ServicesDTO;
import com.feature.gcoin.dto.reponse.InformationUser;
import com.feature.gcoin.dto.reponse.Response;
import com.feature.gcoin.dto.request.ServiceRequest;
import com.feature.gcoin.model.ServiceBuy;
import com.feature.gcoin.model.User;
import com.feature.gcoin.security.TokenHelper;
import com.feature.gcoin.service.GcoinService;
import com.feature.gcoin.service.ServicesBuyService;
import com.feature.gcoin.service.ServicesService;
import com.feature.gcoin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(value = "/services", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServicesController {
    @Autowired
    private ServicesService servicesService;

    @Autowired
    private TokenHelper tokenHelper;

    @Autowired
    UserService userService;

    @RequestMapping(method = GET, value = "/list")
    public ResponseEntity<?> findAllServices() {
        List<ServicesDTO> lst = servicesService.findAll();
        return ResponseEntity.ok(new Response(Constants.SUCCESS, "Successful", lst));
    }

    @RequestMapping(method = POST, value = "/add")
    public ResponseEntity<?> addServices(@RequestBody ServicesDTO servicesDTO) {
        ServicesDTO response = servicesService.saveServices(servicesDTO);
        return ResponseEntity.ok(new Response(Constants.SUCCESS, "Successful", response));
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity<?> findServicesById(@PathVariable Long id) {
        ServicesDTO responseDTO = servicesService.findById(id);
        return ResponseEntity.ok(new Response(Constants.SUCCESS, "Successful", responseDTO));
    }

    @RequestMapping(method = POST, value = "/delete")
    public ResponseEntity<?> deleteServicesById(@RequestBody ServicesDTO servicesDTO) {
        boolean response = servicesService.deleteById(servicesDTO);
        return ResponseEntity.ok(new Response(Constants.SUCCESS, "Successful", response));
    }

    @RequestMapping(method = PUT, value = "/{id}")
    public ResponseEntity<?> updateServices(@PathVariable Long id, @RequestBody ServicesDTO servicesDTO) {
        ServicesDTO response = servicesService.updateServices(id, servicesDTO);
        return ResponseEntity.ok(new Response(Constants.SUCCESS, "Successful", response));
    }

    @RequestMapping(method = POST, value = "/buy")
    public ResponseEntity<?> buyServices(@RequestBody ServiceRequest serviceRequest, HttpServletRequest req) {

        String token = tokenHelper.getToken(req);
        String username = tokenHelper.getUsernameFromToken(token);
        User user = userService.findByUsername(username);
        servicesService.transactionByServices(user.getId(), serviceRequest);

        return ResponseEntity.ok(new Response(Constants.SUCCESS, "Successful", null));
    }

    @RequestMapping(method = GET, value = "/listOwnedServices")
    public ResponseEntity<?> listOwnedServices(HttpServletRequest req) {

        String token = tokenHelper.getToken(req);
        String username = tokenHelper.getUsernameFromToken(token);
        User user = userService.findByUsername(username);
        List<ServicesDTO> responseDTO = servicesService.listOwnedServices(user.getId());
        return ResponseEntity.ok(new Response(Constants.SUCCESS, "Successful", responseDTO));
    }


}