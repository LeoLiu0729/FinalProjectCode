package com.csc340.jpacruddemo.CustomerHomePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/home")
    public String showAccountInformation(Model model) {
        List<Customer> customerList = customerService.getAllCustomers();
        if (!customerList.isEmpty()) {
            // For simplicity, assuming the first customer is the user's
            Customer userCustomer = customerList.get(0);
            model.addAttribute("accountNumber", userCustomer.getAccountNumber());
            model.addAttribute("accountBalance", userCustomer.getAccountBalance());
        }

        return "Customer";
    }

//    @GetMapping("/customerData")
//    @ResponseBody
//    public ResponseEntity<Customer> getCustomerData() {
//        List<Customer> customerList = customerService.getAllCustomers();
//        if (!customerList.isEmpty()) {
//            Customer userCustomer = customerList.get(0);
//            return ResponseEntity.ok(userCustomer);
//        }
//        return ResponseEntity.notFound().build();
//    }
}
