package com.csc340.jpacruddemo.TransferFunds;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/transfers")
public class TransferFundsController {
    private final TransferFundsService transferFundsService;

    @Autowired
    public TransferFundsController(TransferFundsService transferFundsService) {
        this.transferFundsService = transferFundsService;
    }

    @GetMapping("/form")
    public String showTransferForm(Model model) {
        List<TransferFunds> allTransfers = transferFundsService.getAllTransfers();
        model.addAttribute("TransferFunds", allTransfers);
        return "TransferFunds"; // This should match the name of your HTML file without the extension
    }

    @GetMapping
    public List<TransferFunds> getAllTransfers() {
        return transferFundsService.getAllTransfers();
    }

    @PostMapping("/transfer")
    public String transferAmount(@RequestParam String transferFrom, @RequestParam String transferTo, @RequestParam BigDecimal amount) {
        transferFundsService.transferAmount(transferFrom, transferTo, amount);
        return "redirect:/"; // Redirect to the home page ("/")
    }

}
