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
import com.java.crackers.shoppingcart.orderapp.request.CustomerOrderRequest;
import com.java.crackers.shoppingcart.orderapp.request.CustomerRequest;
import com.java.crackers.shoppingcart.orderapp.request.OrderRequest;
import com.java.crackers.shoppingcart.orderapp.response.CustomerResponse;
import com.java.crackers.shoppingcart.orderapp.response.OrderResponse;
import com.java.crackers.shoppingcart.orderapp.response.OrderTable;
import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public List<CustomerResponse> fetchOrdersByEmailId(String email) {
        List<Customer> customer = customerRepo.findByEmailAddress(email);
        return customer.stream()
                .map(c -> orderMapper.mapToCustomerResponse(c)).toList();
    }

    public ProductResponse getProductsById(UUID id) {
        Product product = productRepo.findById(id).orElseThrow();
        return productMapper.mapToproductResponse(product);
    }

    @Transactional
    public ResponseEntity<String> saveOrder(UUID id, List<OrderRequest> orderRequest) {
        try {
            Customer customer = customerRepo.findById(id).orElseThrow();
            if(customer.getId() != null) {
                List<OrderedProduct> orderedProduct = orderMapper.mapToOrderedProduct(orderRequest,customer);
                orderRepo.saveAll(orderedProduct);
//                System.out.println(orderedProduct.size());
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
            return ResponseEntity.ok("User added successfully, your ID:"+ customer.getId());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't save user");
        }
    }


    //For User
    public List<OrderResponse> getOrderDetailsByCustId(UUID customerId) {
        try {
            Customer customer = customerRepo.findById(customerId).orElseThrow();
            return customer.getOrderedProductList().stream()
                    .map(o -> orderMapper.mapToOrderedResponse(o)).toList();
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }


    public List<ProductResponse> getListOfProductsById(List<UUID> id) {
        List<Product> product =  id.stream()
                .map(productId -> productRepo.findById(productId).orElseThrow())
                .toList();
        return product.stream()
                .map(p -> productMapper.mapToproductResponse(p)).toList();

    }

    public List<OrderResponse> getAllOrderDetails() {
        List<OrderedProduct> orderedProducts = orderRepo.findAll();
        return orderedProducts.stream()
                .map(op -> orderMapper.mapToOrderedResponse(op)).toList();
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customer = customerRepo.findAll();
        return customer.stream()
                .map(c -> orderMapper.mapToCustomerResponse(c)).toList();
    }

    public ResponseEntity<String> updateCustomerOrderStatus(UUID id,
                                                            CustomerOrderRequest customerOrderRequest) {
        try {
            Customer customer = customerRepo.findById(id).orElseThrow();
            customer.setId(customer.getId());
            customer.setFirstName(customer.getFirstName());
            customer.setLastName(customer.getLastName());
            customer.setPhoneNumber(customer.getPhoneNumber());
            customer.setEmailAddress(customer.getEmailAddress());
            customer.setAddress(customer.getAddress());
            customer.setPinCode(customer.getPinCode());
            customer.setOrderStatus(customerOrderRequest.getOrderStatus());
            customer.setOrderedProductList(customer.getOrderedProductList());

            customerRepo.save(customer);
            return ResponseEntity.ok().body("Order Status Updated Successfully");
        }

        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }

    public List<CustomerResponse> getCustomerOrderStatusByOrderStatus(String orderStatus) {
        try {
            List<Customer> customer = customerRepo.findByOrderStatus(orderStatus);
            return customer.stream()
                    .map(c -> orderMapper.mapToCustomerResponse(c)).toList();
        } catch (NullPointerException n) {
            throw new NullPointerException("There is no customer having status as: "+ orderStatus);
        }
    }
}
