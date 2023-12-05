package com.csc340.jpacruddemo.CreditCardApplication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/CreditCard")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/apply")
    public String showCreditCardPage(Model model) {
        model.addAttribute("creditCard", new CreditCard());

        return "CreditCard";
    }

    @PostMapping("/apply")
    public String applyForCreditCard(String full_name, String email, String address, String city, String state, String zip, Integer credit_limit) {//@RequestParam(required = false) Integer creditLimit
        creditCardService.saveCreditCard(full_name, email, address, city, state, zip, credit_limit);
        return "redirect:/";
    }

/*
    @PostMapping("/apply")
    public String applyForCreditCard(CreditCard creditCard) {//@RequestParam(required = false) Integer creditLimit
        creditCardService.saveCreditCard(creditCard);
        return "redirect:/CreditCard/success"; // Redirect to a success page
    }
/
/
    @PostMapping("/applied")
    public String applyForCreditCard(@ModelAttribute CreditCard creditCard) {
        // Use the creditCard object directly, which is automatically populated with form data
        creditCardService.saveCreditCard(creditCard);
        return "redirect:/CreditCard/success"; // Redirect to a success page
    }
    */
}