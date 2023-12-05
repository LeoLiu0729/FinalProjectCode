package com.csc340.jpacruddemo.CustomerHomePage;

import com.csc340.jpacruddemo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByUser(User user);
}
