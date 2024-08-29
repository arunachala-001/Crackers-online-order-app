package com.java.crackers.shoppingcart.orderapp.controller;

import com.java.crackers.shoppingcart.orderapp.request.CustomerRequest;
import com.java.crackers.shoppingcart.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        return orderService.loadCustomer(customerRequest);
    }
}
