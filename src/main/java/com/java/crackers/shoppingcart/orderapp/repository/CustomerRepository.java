package com.java.crackers.shoppingcart.orderapp.repository;

import com.java.crackers.shoppingcart.orderapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer> findByEmailAddress(String email);

    List<Customer> findByOrderStatus(String orderStatus);
}
