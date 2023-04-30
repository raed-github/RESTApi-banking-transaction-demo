package com.rsaad.transaction.dto;

import lombok.*;


/**
 * POJO TransactionResponse Class which is the response object
 * which will be displayed for client
 * @author Raed
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private String message;
}
