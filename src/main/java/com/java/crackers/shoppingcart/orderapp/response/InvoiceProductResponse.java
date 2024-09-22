package com.java.crackers.shoppingcart.orderapp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceProductResponse {

    private UUID productId;
    private String productName;
    private int quantity;
    private int price;
}
