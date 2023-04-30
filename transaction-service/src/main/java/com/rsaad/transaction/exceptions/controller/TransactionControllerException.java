package com.rsaad.transaction.exceptions.controller;

import com.rsaad.transaction.exceptions.TransactionProcessingException;
import com.rsaad.transaction.exceptions.validation.ErrorResult;
import com.rsaad.transaction.exceptions.validation.FieldValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

@ControllerAdvice
public class TransactionControllerException{
    @ExceptionHandler(value = TransactionProcessingException.class)
    public ResponseEntity<Object> exception(TransactionProcessingException exception){
        return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResult errorResult = new ErrorResult();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorResult.getFieldErrors().add(
                    FieldValidationError.builder()
                            .message(violation.getMessage())
                            .date(LocalDate.now())
                            .build());
        }
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
}
