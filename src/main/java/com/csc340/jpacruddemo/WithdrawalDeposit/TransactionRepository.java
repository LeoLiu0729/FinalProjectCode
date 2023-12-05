package com.csc340.jpacruddemo.WithdrawalDeposit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}