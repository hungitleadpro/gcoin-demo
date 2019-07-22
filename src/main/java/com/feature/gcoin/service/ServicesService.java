package com.feature.gcoin.service;

import com.feature.gcoin.dto.ServicesDTO;
import com.feature.gcoin.dto.request.ServiceRequest;
import com.feature.gcoin.model.Services;

import java.util.List;

public interface ServicesService {

    List<ServicesDTO> findAll();

    ServicesDTO saveServices(ServicesDTO servicesDTO);

    ServicesDTO findById(Long id);

    boolean deleteById(ServicesDTO servicesDTO);

    ServicesDTO updateServices(Long id, ServicesDTO servicesDTO);

    //mua mot hoac nhieu dich vu, tru coin dua vao ti gia giua tien va coin , luu transaction_log
    void transactionByServices(Long userId, ServiceRequest serviceRequest);

    List<ServicesDTO> listOwnedServices(Long userId);

}
