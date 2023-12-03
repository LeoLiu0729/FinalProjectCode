package com.csc340.jpacruddemo.WithdrawalDeposit;

import com.csc340.jpacruddemo.CreditCardApplication.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
//@RestController
@RequestMapping("/withdrawaldeposit")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/showForm")
    public String showWithdrawDepositPage(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "withdrawaldeposit";
    }

    @PostMapping("/submitTransaction")
    public String submitTransaction(@ModelAttribute Transaction transaction, Model model) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        model.addAttribute("transaction", savedTransaction);
        return "redirect:/"; // Redirect to the page after form submission
    }

//    @GetMapping
//    public String getAllTransactions(Model model) {
//        List<Transaction> transactions = transactionService.getAllTransactions();
//        model.addAttribute("transactions", transactions);
//        return "allTransactions"; // Create a new HTML page to display all transactions
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
//        Optional<Transaction> transaction = transactionService.getTransactionById(id);
//        return transaction.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

//    @PostMapping
//    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
//        Transaction savedTransaction = transactionService.saveTransaction(transaction);
//        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
//    }

}
