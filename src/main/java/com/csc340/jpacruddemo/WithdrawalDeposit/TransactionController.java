package com.csc340.jpacruddemo.WithdrawalDeposit;

import com.csc340.jpacruddemo.user.User;
import com.csc340.jpacruddemo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/withdrawaldeposit")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping("/showForm")
    public String showWithdrawDepositPage(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "withdrawaldeposit";
    }

    @PostMapping("/submitTransaction")
    public String submitTransaction(@ModelAttribute Transaction transaction, Model model) {
        // Retrieve the logged-in user
        String username = userService.getCurrentUsername();
        User user = userService.getUserByUserName(username);

        // Set the user for the transaction
        transaction.setUser(user);

        // Save the transaction
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        model.addAttribute("transaction", savedTransaction);
        return "redirect:/"; // Redirect to the page after form submission
    }
}