package com.java.crackers.shoppingcart.orderapp.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String productName;
    private UUID productId;
    private int productPrice;
    private int orginalPrice;
    private String description;
    private int productDiscount;
}
