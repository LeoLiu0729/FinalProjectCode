package com.csc340.jpacruddemo.CustomerHomePage;

import com.csc340.jpacruddemo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerForLoggedInUser(User user) {
        return customerRepository.findByUser(user);
    }


//    public List<Customer> getAllCustomersForLoggedInUser(Long loggedInUserId) {
//        return customerRepository.findByUserId(loggedInUserId);
//    }


}
