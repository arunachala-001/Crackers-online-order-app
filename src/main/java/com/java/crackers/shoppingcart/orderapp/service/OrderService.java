package com.java.crackers.shoppingcart.orderapp.service;

import com.java.crackers.shoppingcart.orderapp.mapper.CustomerMapper;
import com.java.crackers.shoppingcart.orderapp.mapper.OrderMapper;
import com.java.crackers.shoppingcart.orderapp.mapper.ProductMapper;
import com.java.crackers.shoppingcart.orderapp.model.Customer;
import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import com.java.crackers.shoppingcart.orderapp.model.Product;
import com.java.crackers.shoppingcart.orderapp.repository.CustomerRepository;
import com.java.crackers.shoppingcart.orderapp.repository.OrderRepository;
import com.java.crackers.shoppingcart.orderapp.repository.ProductRepository;
import com.java.crackers.shoppingcart.orderapp.request.CustomerRequest;
import com.java.crackers.shoppingcart.orderapp.request.OrderRequest;
import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final ProductRepository productRepo;
    @Autowired
    private final CustomerRepository customerRepo;
    @Autowired
    private final OrderRepository orderRepo;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    OrderMapper orderMapper;

    public ProductResponse getProductsById(UUID id) {
        Product product = productRepo.findById(id).orElseThrow();
        return productMapper.mapToproductResponse(product);
    }

    public ResponseEntity<String> saveOrder(UUID id, OrderRequest orderRequest) {
        try {
            Customer customer = customerRepo.findById(id).orElseThrow();
            if(customer.getId() != null) {
                OrderedProduct orderedProduct = orderMapper.mapToOrderedProduct(orderRequest,customer);
                orderRepo.save(orderedProduct);
                return ResponseEntity.ok("Order Placed Successfully");
            } else {
                return ResponseEntity.notFound().build();
            }

        }
        catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Customer not found");
        }

    }

    public ResponseEntity<String> loadCustomer(CustomerRequest customerRequest) {
        try {
            Customer customer = customerMapper.mapToCustomer(customerRequest);
            customerRepo.save(customer);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't save user");
        }
    }

}
