package com.java.crackers.shoppingcart.orderapp.repository;

import com.java.crackers.shoppingcart.orderapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

//    SubProduct findBySubProductsName(String name);
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId ORDER BY p.ProductPrice")
    List<Product> sortByProductPrice(@Param("categoryId") long categoryId);
}
