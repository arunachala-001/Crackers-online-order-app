package com.java.crackers.shoppingcart.orderapp.controller;

import com.java.crackers.shoppingcart.orderapp.model.Product;
import com.java.crackers.shoppingcart.orderapp.request.OrderRequest;
import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;
import com.java.crackers.shoppingcart.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/product/{id}")
    public ProductResponse getProductsByIdForOrder(@PathVariable UUID id) {
        return orderService.getProductsById(id);
    }

    // Place Order
    @PostMapping("/product/place-order/{id}")
    public ResponseEntity<String> placeOrder(@PathVariable UUID id,
                                                          @RequestBody OrderRequest orderRequest) {
        return orderService.saveOrder(id, orderRequest);
    }
}
