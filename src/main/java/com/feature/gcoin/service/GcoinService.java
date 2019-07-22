package com.feature.gcoin.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public interface GcoinService {
    //add coin to user
    String addCoin(String address, BigInteger coin) throws Exception;

    //tranfer coin from user sent to user receive
    String transferCoin(String addressSend, String addressReceive, BigInteger coin) throws Exception;

    //get coins of user by address
    BigInteger getCoin(String address) throws Exception;

    //minus coins of user by address
    String subtractCoin(String address, BigInteger coin) throws Exception;



}
