package com.java.crackers.shoppingcart.orderapp.repository;


import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderedProduct, UUID> {
}
