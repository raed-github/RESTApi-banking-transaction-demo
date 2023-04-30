package com.rsaad.transaction.model;

import lombok.*;

import javax.persistence.*;

/**
 * Entity Transaction class that reflect database fields
 * this class is mapped to database"
 * @author Raed
 */
@Entity
@Table(name = "t_transactions")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @Column(name = "trx_currency", nullable = false)
    private String transactionCurrency;

    @Column(name = "trx_amount", nullable = false)
    private Double transactionAmount;

    @Column(name = "trx_account_id", nullable = false)
    private String transactionAccountId;
}
