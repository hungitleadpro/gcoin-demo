package com.feature.gcoin.dto;

import com.feature.gcoin.model.BaseEntity;

import javax.persistence.*;

public class TransactionLogDTO extends BaseDTO {

    private Long id; //id 1

    private String type; //2

    private Long userSendId; //3

    private String userNameSend; //3

    private Long userReceiveId; //4

    private String userNameReceive; //4

    private Long coin; //5

    private Long serviceId; //6

    private String serviceName; //6

    private String transactionLog; //7

    public TransactionLogDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserSendId() {
        return userSendId;
    }

    public void setUserSendId(Long userSendId) {
        this.userSendId = userSendId;
    }

    public Long getUserReceiveId() {
        return userReceiveId;
    }

    public void setUserReceiveId(Long userReceiveId) {
        this.userReceiveId = userReceiveId;
    }

    public Long getCoin() {
        return coin;
    }

    public void setCoin(Long coin) {
        this.coin = coin;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getTransactionLog() {
        return transactionLog;
    }

    public void setTransactionLog(String transactionLog) {
        this.transactionLog = transactionLog;
    }

    public String getUserNameSend() {
        return userNameSend;
    }

    public void setUserNameSend(String userNameSend) {
        this.userNameSend = userNameSend;
    }

    public String getUserNameReceive() {
        return userNameReceive;
    }

    public void setUserNameReceive(String userNameReceive) {
        this.userNameReceive = userNameReceive;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
