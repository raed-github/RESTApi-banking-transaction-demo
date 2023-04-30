package com.rsaad.transaction.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TransactionProcessingException extends RuntimeException{
    private String msg;
    public TransactionProcessingException(String msg){
        super(msg);
    }
}
