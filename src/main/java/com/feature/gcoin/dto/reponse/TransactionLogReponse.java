package com.feature.gcoin.dto.reponse;

import com.feature.gcoin.dto.TransactionLogDTO;

import java.util.List;

public class TransactionLogReponse {
    private List<TransactionLogDTO> voteTransactionLog;
    private List<TransactionLogDTO> coinTransactionLog;

    public List<TransactionLogDTO> getVoteTransactionLog() {
        return voteTransactionLog;
    }

    public void setVoteTransactionLog(List<TransactionLogDTO> voteTransactionLog) {
        this.voteTransactionLog = voteTransactionLog;
    }

    public List<TransactionLogDTO> getCoinTransactionLog() {
        return coinTransactionLog;
    }

    public void setCoinTransactionLog(List<TransactionLogDTO> coinTransactionLog) {
        this.coinTransactionLog = coinTransactionLog;
    }
}
