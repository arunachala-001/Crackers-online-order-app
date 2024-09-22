package com.java.crackers.shoppingcart.orderapp.mapper;

import com.java.crackers.shoppingcart.orderapp.model.Customer;
import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import com.java.crackers.shoppingcart.orderapp.request.OrderRequest;
import com.java.crackers.shoppingcart.orderapp.response.CustomerResponse;
import com.java.crackers.shoppingcart.orderapp.response.OrderResponse;
import com.java.crackers.shoppingcart.orderapp.response.OrderTable;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrderMapper {

    public List<OrderedProduct> mapToOrderedProduct(List<OrderRequest> orderRequest, Customer customer) {
//        return OrderedProduct.builder()
//                .productId(orderRequest.getProductId())
//                .productName(orderRequest.getProductName())
//                .quantity(orderRequest.getQuantity())
//                .description(orderRequest.getDescription())
//                .orginalPrice(orderRequest.getOrginalPrice())
//                .productDiscount(orderRequest.getProductDiscount())
//                .productPrice(orderRequest.getProductPrice())
//                .quantity(quantity)
//                .total(orderRequest.getProductPrice()*quantity)
//                .customer(customer)
//                .build();
        return orderRequest.stream()
                .map(or -> streamOrder(or, customer)).toList();

    }

    private OrderedProduct streamOrder(OrderRequest or, Customer customer) {
        return OrderedProduct.builder()
                .productId(or.getProductId())
                .productName(or.getProductName())
                .quantity(or.getQuantity())
                .description(or.getDescription())
                .orginalPrice(or.getOrginalPrice())
                .productDiscount(or.getProductDiscount())
                .productPrice(or.getProductPrice())
                .quantity(or.getQuantity())
                .total(or.getProductPrice()*or.getQuantity())
                .customer(customer)
                .build();
    }

    public OrderResponse mapToOrderedResponse(OrderedProduct o) {
        return OrderResponse.builder()
                .id(o.getId())
                .productId(o.getProductId())
                .productName(o.getProductName())
                .productPrice(o.getProductPrice())
                .quantity(o.getQuantity())
                .customer(o.getCustomer())
                .total(o.getTotal())
                .description(o.getDescription())
                .orderedDate(o.getOrderedDate())
                .build();
    }

    public CustomerResponse mapToCustomerResponse(Customer c) {
        return CustomerResponse.builder()
                .id(c.getId())
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .orderStatus(c.getOrderStatus())
                .orderedProductList(c.getOrderedProductList())
                .build();
    }

//    public OrderTable mapToOrderTable(OrderedProduct o) {
//        return OrderTable.builder()
//                .customerName(o.getCustomer().getFirstName()+" "+ o.getCustomer().getLastName())
//                .productName(o.getProductName())
//                .price(o.getProductPrice())
//                .quantity(o.getQuantity())
//                .total(o.getTotal())
//                .createdDate(o.getOrderedDate())
//                .status(o.getStatus())
//                .build();
//    }
}
