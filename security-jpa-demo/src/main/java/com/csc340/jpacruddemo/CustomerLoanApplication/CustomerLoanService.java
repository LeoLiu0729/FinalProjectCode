package com.csc340.jpacruddemo.CustomerLoanApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerLoanService {
    @Autowired
    private CustomerLoanRepository customerLoanRepository;

    public void saveLoan(String full_name, String email, String address, String city, String state, String zip, Integer loanAmount) {
        CustomerLoan loan = new CustomerLoan(full_name, email, address, city, state, zip, loanAmount);
        customerLoanRepository.save(loan);
    }
}
