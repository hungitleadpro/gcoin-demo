package com.feature.gcoin.service;

import com.feature.gcoin.model.ServiceBuy;

import java.util.List;

public interface ServicesBuyService {

    ServiceBuy saveServiceBuy(ServiceBuy serviceBuy);

    List<ServiceBuy> lstServiceBuyByUserId(Long userId);
}
