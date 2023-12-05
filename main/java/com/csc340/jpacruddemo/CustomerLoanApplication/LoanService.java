package com.csc340.jpacruddemo.CustomerLoanApplication;

import com.csc340.jpacruddemo.user.User;
import com.csc340.jpacruddemo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserService userService;

    public void saveLoan(String full_name, String email, String address, String city, String state, String zip, Integer loanAmount) {
        String username = userService.getCurrentUsername();
        User user = userService.getUserByUserName(username);
        Loan loan = new Loan(full_name, email, address, city, state, zip, loanAmount);
        loan.setUser(user);
        loanRepository.save(loan);
    }

}