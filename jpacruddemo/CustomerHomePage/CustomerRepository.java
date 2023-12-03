package com.csc340.jpacruddemo.CustomerHomePage;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
