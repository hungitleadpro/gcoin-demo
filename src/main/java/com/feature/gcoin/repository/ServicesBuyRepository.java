package com.feature.gcoin.repository;

import com.feature.gcoin.model.ServiceBuy;
import com.feature.gcoin.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesBuyRepository extends JpaRepository<ServiceBuy, Long> {

    ServiceBuy save(ServiceBuy serviceBuy);

    List<ServiceBuy> findByUserId(Long userId);

}
