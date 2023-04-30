package com.rsaad.transaction.service;

import com.rsaad.transaction.dto.TransactionRequest;
import com.rsaad.transaction.dto.TransactionResponse;
public interface TransactionService {
    public TransactionResponse processTransaction(TransactionRequest transactionRequest);
}
