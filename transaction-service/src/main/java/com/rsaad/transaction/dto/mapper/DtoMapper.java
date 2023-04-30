package com.rsaad.transaction.dto.mapper;

import com.rsaad.transaction.dto.TransactionRequest;
import com.rsaad.transaction.model.Transaction;

/**
 * Convert Model classes to Dto classes"
 * and vice versa
 * @param Either Dto or model Class
 * @return Either Dto or model Class.
 * @author Raed
 */
public class DtoMapper {
    public static Transaction mapToTransaction(TransactionRequest transactionRequest) {
        return Transaction.builder()
                .transactionAmount(transactionRequest.getAmount())
                .transactionAccountId(transactionRequest.getAccountNumber())
                .transactionCurrency(transactionRequest.getCurrency())
                .build();
    }
}
