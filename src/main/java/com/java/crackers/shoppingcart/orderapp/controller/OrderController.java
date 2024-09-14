package com.java.crackers.shoppingcart.orderapp.controller;

import com.java.crackers.shoppingcart.orderapp.model.Product;
import com.java.crackers.shoppingcart.orderapp.request.OrderRequest;
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

    //List Products after clicking Order btn
    @GetMapping("/product/{id}")
    public ProductResponse getProductsByIdForOrder(@PathVariable UUID id) {
        return orderService.getProductsById(id);
    }

    // Place Order
    @PostMapping("/product/place-order/{id}/{quantity}")
    public ResponseEntity<String> placeOrder(@PathVariable UUID id, @PathVariable int quantity,
                                                          @RequestBody List<OrderRequest> orderRequest) {
        return orderService.saveOrder(id,quantity, orderRequest);
    }

    //Cart Page
    @GetMapping("/user/{customerId}")
    public List<OrderResponse> fetchOrderDetailsByCustId(@PathVariable UUID customerId) {
        return orderService.getOrderDetailsByCustId(customerId);
    }

    //Admin view
    @GetMapping("/admin/007")
    public List<OrderTable> fetchAllOrderDetails() {
        return orderService.getAllOrderDetails();
    }
}
