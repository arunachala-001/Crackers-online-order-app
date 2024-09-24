package com.java.crackers.shoppingcart.orderapp.response;

import com.java.crackers.shoppingcart.orderapp.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private UUID id;
    private String productName;
    private int productPrice;
    private int orginalPrice;
    private String description;
    private int productDiscount;
    private Category category;
    private String image;
}
