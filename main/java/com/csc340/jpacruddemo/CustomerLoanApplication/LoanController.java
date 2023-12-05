package com.csc340.jpacruddemo.CustomerLoanApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/apply")
    public String showLoanPage(Model model) {
        model.addAttribute("Loan", new Loan());
        return "LoanApplication";
    }

    @PostMapping("/apply")
    public String applyForLoan(Loan loan) {
        loanService.saveLoan(loan.getFull_name(), loan.getEmail(), loan.getAddress(),
                loan.getCity(), loan.getState(), loan.getZip(), loan.getLoan());
        return "redirect:/"; // Redirect to home or another page after applying for the loan
    }
}