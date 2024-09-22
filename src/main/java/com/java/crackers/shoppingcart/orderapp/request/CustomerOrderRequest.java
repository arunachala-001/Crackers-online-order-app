package com.java.crackers.shoppingcart.orderapp.request;

import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderRequest {
    private UUID id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    private String address;

    private long pinCode;

    private String orderStatus;

    private List<OrderedProduct> orderedProductList;
}
