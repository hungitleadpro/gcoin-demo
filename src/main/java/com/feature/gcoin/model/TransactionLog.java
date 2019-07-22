package com.feature.gcoin.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TRANSACTIONS_LOG")
public class TransactionLog extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id 1

    @Column(name = "type")
    private String type; //2

    @Column(name = "USER_SEND_ID")
    private Long userSendId; //3

    @Column(name = "USER_RECEIVE_ID")
    private Long userReceiveId; //4

    @Column(name = "COIN")
    private Long coin; //5

    @Column(name = "SERVICE_ID")
    private Long serviceId; //6

    @Column(name = "TRANSACTION_LOG")
    private String transactionLog; //7

    public TransactionLog() {
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

}
