package com.csc340.jpacruddemo.LoanApplication;

import org.springframework.data.jpa.repository.JpaRepository;
public interface LoanRepository extends JpaRepository<Loan, Long>{

}
