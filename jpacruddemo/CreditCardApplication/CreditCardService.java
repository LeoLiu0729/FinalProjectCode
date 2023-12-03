package com.csc340.jpacruddemo.CreditCardApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public void saveCreditCard(String full_name, String email, String address, String city, String state, String zip, Integer credit_limit) {
        CreditCard creditCard = new CreditCard(full_name, email, address, city, state, zip, credit_limit);
        creditCardRepository.save(creditCard);

        //CreditCard creditCard = new CreditCard(fullName, email, address, city, state, zip, creditLimit);
        //creditCardRepository.saveCreditCard(creditCard);
    }



}
