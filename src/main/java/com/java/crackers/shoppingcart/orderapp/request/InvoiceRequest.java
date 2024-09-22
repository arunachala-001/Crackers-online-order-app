package com.java.crackers.shoppingcart.orderapp.request;

import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import com.java.crackers.shoppingcart.orderapp.response.InvoiceProductResponse;
import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;
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
public class InvoiceRequest {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String address;
    private long pinCode;
    private String emailId;

    List<OrderedProduct> ListofProducts;



}
