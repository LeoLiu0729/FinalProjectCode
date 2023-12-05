package com.csc340.jpacruddemo.CreditCardApplication;

import com.csc340.jpacruddemo.user.User;
import com.csc340.jpacruddemo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private UserService userService;
    public void saveCreditCard(String full_name, String email, String address, String city, String state, String zip, Integer credit_limit) {
        String username = userService.getCurrentUsername();
        User user = userService.getUserByUserName(username);
        CreditCard creditCard = new CreditCard(full_name, email, address, city, state, zip, credit_limit);
        creditCard.setUser(user);
        creditCardRepository.save(creditCard);

        //CreditCard creditCard = new CreditCard(fullName, email, address, city, state, zip, creditLimit);
        //creditCardRepository.saveCreditCard(creditCard);
    }



}
