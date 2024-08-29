package com.java.crackers.shoppingcart.orderapp.repository;

import com.java.crackers.shoppingcart.orderapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
