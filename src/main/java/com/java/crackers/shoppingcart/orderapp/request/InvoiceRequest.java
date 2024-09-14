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
public class InvoiceRequest {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String address;
    private long pinCode;
    private String emailId;

    private UUID productId;
    private String productName;
    private int quantity;
    private int price;


}
