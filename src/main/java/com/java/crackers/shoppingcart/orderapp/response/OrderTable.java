package com.java.crackers.shoppingcart.orderapp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTable {
//    private UUID orderId;
    private String customerName;
    private String address;
    private String productName;
    private int quantity;
    private int price;
    private int total;
    private Date createdDate;
    private String status;
}
