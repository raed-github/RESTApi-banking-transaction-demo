package com.rsaad.transaction.repository;

import com.rsaad.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Transaction Repository interface
 * will handle the api database communication
 * @author Raed
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
