package com.feature.gcoin.service.impl;


import com.feature.gcoin.model.ServiceBuy;
import com.feature.gcoin.repository.ServicesBuyRepository;
import com.feature.gcoin.repository.ServicesRepository;
import com.feature.gcoin.service.ServicesBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesBuyImpl implements ServicesBuyService {
    @Autowired
    private ServicesBuyRepository servicesRepository;

    @Override
    public ServiceBuy saveServiceBuy(ServiceBuy serviceBuy) {
        return servicesRepository.save(serviceBuy);
    }

    @Override
    public List<ServiceBuy> lstServiceBuyByUserId(Long userId) {
        return servicesRepository.findByUserId(userId);
    }
}
