package com.csc340.jpacruddemo.LoanApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public void saveLoan(String full_name, String email, String address, String city, String state, String zip, Integer loanAmount) {
        Loan loan = new Loan(full_name, email, address, city, state, zip, loanAmount);
        loanRepository.save(loan);
    }

}
