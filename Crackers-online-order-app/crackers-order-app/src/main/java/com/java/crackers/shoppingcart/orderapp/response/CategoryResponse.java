package com.java.crackers.shoppingcart.orderapp.response;

import com.java.crackers.shoppingcart.orderapp.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private long categoryId;
    private String categoryName;
    private String image;
    private List<Product> product;

}
