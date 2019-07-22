package com.feature.gcoin.service.impl;

import com.feature.gcoin.common.constant.Const;
import com.feature.gcoin.common.constant.Constants;
import com.feature.gcoin.common.util.ModelMapperUtil;
import com.feature.gcoin.dto.ServicesDTO;
import com.feature.gcoin.dto.request.ServiceRequest;
import com.feature.gcoin.model.ServiceBuy;
import com.feature.gcoin.model.Services;
import com.feature.gcoin.model.TransactionLog;
import com.feature.gcoin.model.User;
import com.feature.gcoin.repository.ServicesBuyRepository;
import com.feature.gcoin.repository.ServicesRepository;
import com.feature.gcoin.repository.TransactionLogRepository;
import com.feature.gcoin.repository.UserRepository;
import com.feature.gcoin.service.GcoinService;
import com.feature.gcoin.service.ServicesBuyService;
import com.feature.gcoin.service.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServicesServiceImpl implements ServicesService {
    private final Logger log = LoggerFactory.getLogger(ServicesServiceImpl.class);

    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private ServicesBuyRepository servicesBuyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesBuyService servicesBuyService;
    @Autowired
    private GcoinService gcoinService;
    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Override
    public List<ServicesDTO> findAll() {
        List<ServicesDTO> servicesDTOS = new ArrayList<>();
        try {
            List<Services> services = servicesRepository.findAll();
            servicesDTOS = ModelMapperUtil.maps((List) services, ServicesDTO.class);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return servicesDTOS;
    }

    @Override
    public ServicesDTO saveServices(ServicesDTO servicesDTO) {
        ServicesDTO response = new ServicesDTO();
        try {
            Services services = ModelMapperUtil.map(servicesDTO, Services.class);
            services = servicesRepository.save(services);
            response = ModelMapperUtil.map(services, ServicesDTO.class);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return response;
    }

    @Override
    public ServicesDTO findById(Long id) {
        ServicesDTO response = new ServicesDTO();
        try {
            Services services = servicesRepository.findById(id);
            response = ModelMapperUtil.map(services, ServicesDTO.class);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return response;
    }

    @Override
    @Transactional
    public boolean deleteById(ServicesDTO servicesDTO) {
        boolean response = false;
        try {
            servicesRepository.deleteServicesById(servicesDTO.getId());
            response = true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return response;
    }

    @Override
    public ServicesDTO updateServices(Long id, ServicesDTO servicesDTO) {
        ServicesDTO resDTO = new ServicesDTO();
        try {
            Services services = servicesRepository.findOne(id);
            services = ModelMapperUtil.map(servicesDTO, Services.class);
            services.setId(id);
            Services resEntity = servicesRepository.save(services);
            resDTO = ModelMapperUtil.map(resEntity, ServicesDTO.class);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return resDTO;
    }

    public void tranferMoneyToCoin(Long userMoney) {

    }

    //mua mot hoac nhieu dich vu, tru coin dua vao ti gia giua tien va coin mat di , luu transaction_log
    @Override
    public void transactionByServices(Long userId, ServiceRequest serviceRequest) {
        try {
            //luong coin hien tai cua user
            User user = userRepository.findById(userId);
            BigInteger userCoin = gcoinService.getCoin(user.getAddress());
            Services services = servicesRepository.findById(serviceRequest.getServiceId());
            if (services == null) {
                throw new Exception("dich vu null");
            }
            //tong luong coin ung voi service
            BigInteger totalCoinsOfService;
            Long temp = null;
            temp = (Long) services.getPrice() * serviceRequest.getTotal() / Const.exchangeRate.longValue();
            totalCoinsOfService = BigInteger.valueOf(temp);
            if (userCoin.compareTo(totalCoinsOfService) < 0) {
                throw new Exception("Tai khoan cua user khong du de thuc hien giao dich");
            } else {
                log.info("Coin truoc khi mua cua user: " + gcoinService.getCoin(user.getAddress()));
                String alog = gcoinService.subtractCoin(user.getAddress(), totalCoinsOfService);
                log.info("Coin sau khi mua cua user: " + gcoinService.getCoin(user.getAddress()));
                ServiceBuy serviceBuy = new ServiceBuy();
                serviceBuy.setServiceId(serviceRequest.getServiceId());
                serviceBuy.setPrice(Const.exchangeRate.longValue());
                serviceBuy.setQuantity(Long.valueOf(serviceRequest.getTotal()));
                serviceBuy.setUserId(userId);
                serviceBuy.setCreatAt(new Date());

                servicesBuyService.saveServiceBuy(serviceBuy);

                TransactionLog transaction2 = new TransactionLog();
                transaction2.setType(Constants.TransactionType.SUBTRACTION_COIN.name());
                transaction2.setUserSendId(userId);
                transaction2.setUserReceiveId(null);
                transaction2.setCoin(totalCoinsOfService.longValue());
                transaction2.setServiceId(serviceRequest.getServiceId());
                transaction2.setTransactionLog(alog);
                transaction2.setCreatAt(new Date());
                transaction2.setUpdateAt(new Date());
                transactionLogRepository.save(transaction2);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public List<ServicesDTO> listOwnedServices(Long userId) {

        List<ServicesDTO> servicesDTOS = new ArrayList<>();
        List<ServiceBuy> services = servicesBuyRepository.findByUserId(userId);
        for (ServiceBuy serviceBuy : services) {
            Services sv = servicesRepository.findById(serviceBuy.getServiceId());
            ServicesDTO servicesDTO = new ServicesDTO();
            servicesDTO = ModelMapperUtil.map(sv, ServicesDTO.class);
            servicesDTO.setBuyDate(serviceBuy.getCreatAt());
            servicesDTOS.add(servicesDTO);
        }
        return servicesDTOS;
    }

}
