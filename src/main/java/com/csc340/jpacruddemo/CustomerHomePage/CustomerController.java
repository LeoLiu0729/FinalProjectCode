package com.csc340.jpacruddemo.CustomerHomePage;

import com.csc340.jpacruddemo.user.User;
import com.csc340.jpacruddemo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;
import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;

    @Autowired
    public CustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping("/Customer")
    public String showAccountInformation(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUserName(username);

        Optional<Customer> customerOptional = customerService.getCustomerForLoggedInUser(user);

        if (customerOptional.isPresent()) {
            Customer userCustomer = customerOptional.get();

            model.addAttribute("currentUser", username);
            model.addAttribute("accountNumber", userCustomer.getAccountNumber());
            model.addAttribute("accountBalance", userCustomer.getAccountBalance());
        } else {
            return "403";
        }

        return "Customer";
    }

//    @GetMapping("/Customer")
//    public String showAccountInformation(Model model) { //@RequestParam Long id,
////        Long loggedInUserId = getLoggedInUserId();
////        List<Customer> customerList = customerService.getAllCustomersForLoggedInUser(loggedInUserId);
//        List<Customer> customerList = customerService.getAllCustomers();
//        //Customer userCustomer = customerService.getCustomerById(Id);
//        if (!customerList.isEmpty()) {
//            // For simplicity, assuming the first customer is the user's
//            Customer userCustomer = customerList.get(0);
//
//
//
//            String name = SecurityContextHolder.getContext().getAuthentication().getName();
//            model.addAttribute("currentUser", name);
//            model.addAttribute("accountNumber", userCustomer.getAccountNumber());
//            model.addAttribute("accountBalance", userCustomer.getAccountBalance());
//        }
//        else {
//            return "403";
//        }
//
//        return "Customer";
//    }

//    private Long getLoggedInUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // Check if the user is authenticated
//        if (authentication != null && authentication.isAuthenticated()) {
//            // Assuming your user details implement UserDetails and have a getId method
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            return userDetails.getId();
//        }
//
//        // Handle the case where user is not logged in or user ID is not available
//        return null;
//    }

}
