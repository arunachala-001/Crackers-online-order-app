package com.java.crackers.shoppingcart.orderapp.request;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private String ProductName;
    private int ProductPrice;
    private int orginalPrice;
    private String description;
    private int ProductDiscount;

}
