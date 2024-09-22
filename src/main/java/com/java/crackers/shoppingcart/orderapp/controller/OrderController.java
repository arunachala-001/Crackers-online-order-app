package com.java.crackers.shoppingcart.orderapp.controller;

import com.java.crackers.shoppingcart.orderapp.model.Product;
import com.java.crackers.shoppingcart.orderapp.request.OrderRequest;
import com.java.crackers.shoppingcart.orderapp.response.CustomerResponse;
import com.java.crackers.shoppingcart.orderapp.response.OrderResponse;
import com.java.crackers.shoppingcart.orderapp.response.OrderTable;
import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;
import com.java.crackers.shoppingcart.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService orderService;

//    List Products after clicking Order btn
    @GetMapping("/product/{id}")
    public ProductResponse getProductsByIdForOrder(@PathVariable UUID id) {
        return orderService.getProductsById(id);
    }

    @PostMapping("/product")
    public List<ProductResponse> getProductsByIdsForOrder(@RequestBody List<UUID> id) {
        System.out.println(id == null);
        return orderService.getListOfProductsById(id);
    }

    // Place Order
    @PostMapping("/product/place-order/{id}")
    public ResponseEntity<String> placeOrder(@PathVariable UUID id,@RequestBody List<OrderRequest> orderRequest) {
        orderRequest.stream().forEach(System.out::println);
        return orderService.saveOrder(id,orderRequest);
    }

    //Cart Page
    @GetMapping("/user/{customerId}")
    public List<OrderResponse> fetchOrderDetailsByCustId(@PathVariable UUID customerId) {
        return orderService.getOrderDetailsByCustId(customerId);
    }

    @GetMapping("/history/{email}")
    public List<CustomerResponse> fetchOrderDetailsByCustEmail(@PathVariable String email) {
        return orderService.fetchOrdersByEmailId(email);
    }

    //Admin view

}
