package com.java.crackers.shoppingcart.orderapp.repository;

import com.java.crackers.shoppingcart.orderapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    @Query("SELECT c FROM Category c where LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))" )
    List<Category> findByNameContainingIgnoreCase(@Param("name") String name);
}
