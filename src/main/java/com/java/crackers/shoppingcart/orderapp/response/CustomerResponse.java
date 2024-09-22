package com.java.crackers.shoppingcart.orderapp.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private UUID id;

    private String firstName;

    private String lastName;

    private String orderStatus;



    private List<OrderedProduct> orderedProductList;

//    private String phoneNumber;
//
//    private String emailAddress;
//
//    private String address;
//
//    private long pinCode;


}
