package com.csc340.jpacruddemo.CustomerLoanApplication;

import org.springframework.data.jpa.repository.JpaRepository;
public interface LoanRepository extends JpaRepository<Loan, Long>{

}