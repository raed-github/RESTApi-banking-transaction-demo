package com.rsaad.transaction.service.impl;

import com.rsaad.transaction.constants.TransactionConstants;
import com.rsaad.transaction.dto.TransactionRequest;
import com.rsaad.transaction.dto.TransactionResponse;
import com.rsaad.transaction.dto.mapper.DtoMapper;
import com.rsaad.transaction.exceptions.TransactionProcessingException;
import com.rsaad.transaction.model.Transaction;
import com.rsaad.transaction.repository.TransactionRepository;
import com.rsaad.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transaction Service class
 * will contain all the business functionality
 * of Transaction api
 * @author Raed
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    /**
     * processTransaction method will process a
     * transactionRequest, validate it and
     * update the database accordingly
     * @param transactionRequest
     * @return
     */
    @Override
    public TransactionResponse processTransaction(TransactionRequest transactionRequest) throws TransactionProcessingException {
        Transaction transaction = DtoMapper.mapToTransaction(transactionRequest);
        transactionRepository.save(transaction);
        return TransactionResponse.builder().message(TransactionConstants.SUCCESS).build();
    }
}
