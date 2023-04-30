package com.rsaad.transaction.service;

import com.rsaad.transaction.dto.TransactionRequest;
import com.rsaad.transaction.dto.TransactionResponse;
import com.rsaad.transaction.exceptions.TransactionProcessingException;

public interface TransactionService {
    public TransactionResponse processTransaction(TransactionRequest transactionRequest) throws TransactionProcessingException;
}
