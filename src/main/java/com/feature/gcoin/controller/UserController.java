package com.feature.gcoin.controller;

import com.feature.gcoin.common.constant.Constants;
import com.feature.gcoin.common.constant.GcoinException;
import com.feature.gcoin.dto.UserDTO;
import com.feature.gcoin.dto.reponse.Response;
import com.feature.gcoin.dto.request.UserRequest;
import com.feature.gcoin.model.User;
import com.feature.gcoin.security.TokenHelper;
import com.feature.gcoin.service.GcoinService;
import com.feature.gcoin.service.TransactionLogService;
import com.feature.gcoin.service.UserService;
import com.feature.gcoin.smartcontract.GcoinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController
{

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionLogService transactionLogService;

    @Autowired
    private TokenHelper tokenHelper;
    @Autowired
    private GcoinService gcoinService;

//    @Autowired
//    private GcoinService gcoinService;

    @RequestMapping(method = GET, value = "")
    public User loadById(HttpServletRequest req)
    {
        String token = tokenHelper.getToken(req);
        String username = tokenHelper.getUsernameFromToken(token);
        return userService.findByUsername(username);
    }

    @RequestMapping(method = GET, value = "/createMoney")
    public String createCoin() throws Exception
    {
        GcoinUtil.loadWeb3j();
        GcoinUtil.deloyGcoin();

        gcoinService.addCoin("0x692a70d2e424a56d2c6c27aa97d1a86395877b3a", BigInteger.valueOf(1000));
        gcoinService.addCoin("0xbbf289d846208c16edc8474705c748aff07732db", BigInteger.valueOf(1000));
        gcoinService.addCoin("0x0dcd2f752394c41875e259e00bb44fd505297caf", BigInteger.valueOf(1000));
        gcoinService.addCoin("0x08970fed061e7747cd9a38d680a601510cb659fb", BigInteger.valueOf(1000));
        gcoinService.addCoin("0xdc04977a2078c8ffdf086d618d1f961b6c546222", BigInteger.valueOf(1000));
        gcoinService.addCoin("0xef55bfac4228981e850936aaf042951f7b146e41", BigInteger.valueOf(1000));
        gcoinService.addCoin("0x8c1ed7e19abaa9f23c476da86dc1577f1ef401f5", BigInteger.valueOf(1000));
        return "coin";
    }

//    @RequestMapping(method = GET, value = "/all")
//    public List<User> loadAll() {
//        return this.userService.findAll();
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/transferCoin")
//    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> transferCoin(@RequestBody UserRequest req, HttpServletRequest httpServletRequest) throws GcoinException
    {
        try
        {
            String token = tokenHelper.getToken(httpServletRequest);
            String username = tokenHelper.getUsernameFromToken(token);
            User user = userService.findByUsername(username);
            transactionLogService.insertTransfer(user.getId(), req);
            Response response = new Response(Constants.ResponseCode.OK.getValue(), Constants.ResponseCode.OK.getDisplay(), null);
            return ResponseEntity.ok(response);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new GcoinException(Constants.ExceptionCode.Unknown.getValue(), ex.toString());
        }
    }

    @RequestMapping(value = "/getCoins", method = GET)
    public UserDTO getCoins(HttpServletRequest req)
    {

        String token = tokenHelper.getToken(req);
        String username = tokenHelper.getUsernameFromToken(token);

        User user = userService.findByUsername(username);

        return userService.getCoins(user);
    }


}
