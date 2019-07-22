package com.feature.gcoin.service.impl;

import com.feature.gcoin.service.GcoinService;
import com.feature.gcoin.smartcontract.GcoinUtil;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class GcoinServiceImpl implements GcoinService {

    //add coin to user
    @Override
    public String addCoin(String address, BigInteger coin) throws Exception {
        return GcoinUtil.gcoin.addCoin(address, coin).send().getBlockHash();
    }

    //tranfer coin from user sent to user receive
    @Override
    public String transferCoin(String addressSend, String addressReceive, BigInteger coin) throws Exception {
        return GcoinUtil.gcoin.sendCoin(addressSend, addressReceive, coin).send().getBlockHash();

    }

    //get coins of user by address
    @Override
    public BigInteger getCoin(String address) throws Exception {
        return GcoinUtil.gcoin.getBalance(address).send();
    }

    //minus coins of user by address
    @Override
    public String subtractCoin(String address, BigInteger coin) throws Exception {

        return GcoinUtil.gcoin.reduceCoin(address, coin).send().getBlockHash();
    }

}
