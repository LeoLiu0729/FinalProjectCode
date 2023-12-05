package com.csc340.jpacruddemo.TransferFunds;
import com.csc340.jpacruddemo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.csc340.jpacruddemo.user.User;


import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/transfers")
public class TransferFundsController {
        private final TransferFundsService transferFundsService;
        private final UserService userService;

        @Autowired
        public TransferFundsController(TransferFundsService transferFundsService, UserService userService) {
            this.transferFundsService = transferFundsService;
            this.userService = userService;
        }

        @GetMapping("/form")
        public String showTransferForm(Model model) {
            String username = userService.getCurrentUsername();
            User user = userService.getUserByUserName(username);
            List<TransferFunds> userTransfers = transferFundsService.getTransfersForUser(user);

            model.addAttribute("user", user);
            model.addAttribute("transfers", userTransfers);
            return "TransferFunds"; // This should match the name of your HTML file without the extension
        }

        @PostMapping("/transfer")
        public String transferAmount(@RequestParam String transferFrom, @RequestParam String transferTo, @RequestParam BigDecimal amount) {
            String username = userService.getCurrentUsername();
            User user = userService.getUserByUserName(username);

            transferFundsService.transferAmount(user, transferFrom, transferTo, amount);
            return "redirect:/transfers/form";
        }
    }

