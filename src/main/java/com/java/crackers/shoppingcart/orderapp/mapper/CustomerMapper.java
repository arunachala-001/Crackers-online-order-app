package com.java.crackers.shoppingcart.orderapp.mapper;

import com.java.crackers.shoppingcart.orderapp.model.Customer;
import com.java.crackers.shoppingcart.orderapp.request.CustomerRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerMapper {


    public Customer mapToCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .emailAddress(customerRequest.getEmailAddress())
                .address(customerRequest.getAddress())
                .phoneNumber(customerRequest.getPhoneNumber())
                .pinCode(customerRequest.getPinCode())
                .orderStatus(customerRequest.getOrderStatus())
                .build();
    }
}
