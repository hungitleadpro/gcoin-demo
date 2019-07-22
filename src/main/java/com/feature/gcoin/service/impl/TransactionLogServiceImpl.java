package com.feature.gcoin.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.feature.gcoin.common.constant.Constants;
import com.feature.gcoin.common.util.ModelMapperUtil;
import com.feature.gcoin.dto.ServicesDTO;
import com.feature.gcoin.dto.TransactionLogDTO;
import com.feature.gcoin.dto.reponse.TransactionLogReponse;
import com.feature.gcoin.model.User;
import com.feature.gcoin.service.*;
import org.mapstruct.MapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feature.gcoin.dto.request.UserRequest;
import com.feature.gcoin.model.TransactionLog;
import com.feature.gcoin.repository.TransactionLogRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class TransactionLogServiceImpl implements TransactionLogService {

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ServicesService servicesService;

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private GcoinService gcoinService;

    @Override
    public void insertTransfer(Long userSendId, UserRequest req) throws Exception {
        String log = null;
        User userSend = userService.findById(userSendId);
        User userReceive = userService.findByAddress(req.getAddressReceive());
        BigInteger userCoin = gcoinService.getCoin(userSend.getAddress());
        if (userCoin.compareTo(BigInteger.valueOf(req.getTotalCoin())) < 0) {
            throw new Exception("Tai khoan cua user khong du de thuc hien giao dich");
        } else {
            try {
                log = gcoinService.transferCoin(userSend.getAddress(), userReceive.getAddress(), BigInteger.valueOf(req.getTotalCoin()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            TransactionLog transaction = new TransactionLog();
            transaction.setType(Constants.TransactionType.SUBTRACTION_COIN.name());
            transaction.setUserSendId(userSendId);
            transaction.setUserReceiveId(null);
            transaction.setCoin(req.getTotalCoin());
            transaction.setServiceId(null);
            transaction.setTransactionLog(log);
            transaction.setCreatAt(new Date());
            transaction.setUpdateAt(new Date());
            transactionLogRepository.save(transaction);

            TransactionLog transaction2 = new TransactionLog();
            transaction2.setType(Constants.TransactionType.ADD_COIN.name());
            transaction2.setUserSendId(null);
            transaction2.setUserReceiveId(userReceive.getId());
            transaction2.setCoin(req.getTotalCoin());
            transaction2.setServiceId(null);
            transaction2.setTransactionLog(log);
            transaction2.setCreatAt(new Date());
            transaction2.setUpdateAt(new Date());
            transactionLogRepository.save(transaction2);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public TransactionLogReponse search(Long userId) {
        TransactionLogReponse transactionLogReponse = new TransactionLogReponse();
        List<TransactionLogDTO> logsVote = new ArrayList<>();
        List<TransactionLogDTO> logsCoin = new ArrayList<>();
        List<TransactionLogDTO> logs = ModelMapperUtil.maps((List) transactionLogRepository.search(userId, userId), TransactionLogDTO.class);
        for (TransactionLogDTO log : logs) {
            if (log.getServiceId() != null) {
                ServicesDTO service = servicesService.findById(log.getServiceId());
                log.setServiceName(service != null ? service.getName() : "");
            }
            if (log.getUserSendId() != null) {
                User user = userService.findById(log.getUserSendId());
                log.setUserNameReceive(user != null ? user.getName() : "");
            }
            if (log.getUserReceiveId() != null) {
                User user1 = userService.findById(log.getUserReceiveId());
                log.setUserNameReceive(user1 != null ? user1.getName() : "");
            }
            if (log.getType() != null &&
                    (log.getType().equalsIgnoreCase(Constants.TransactionType.ADD_COIN.name())
                            || log.getType().equalsIgnoreCase(Constants.TransactionType.SUBTRACTION_COIN.name()))) {
                logsVote.add(log);
            } else {
                logsCoin.add(log);
            }
        }
        transactionLogReponse.setCoinTransactionLog(logsVote);
        transactionLogReponse.setVoteTransactionLog(logsCoin);
        return transactionLogReponse;
    }
}
