package com.java.crackers.shoppingcart.orderapp.mapper;

import com.java.crackers.shoppingcart.orderapp.model.Customer;
import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import com.java.crackers.shoppingcart.orderapp.request.OrderRequest;
import com.java.crackers.shoppingcart.orderapp.response.OrderResponse;
import com.java.crackers.shoppingcart.orderapp.response.OrderTable;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrderMapper {

    public List<OrderedProduct> mapToOrderedProduct(List<OrderRequest> orderRequest, Customer customer, int quantity) {
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
                .map(or -> streamOrder(or, customer, quantity)).toList();

    }

    private OrderedProduct streamOrder(OrderRequest or, Customer customer, int quantity) {
        return OrderedProduct.builder()
                .productId(or.getProductId())
                .productName(or.getProductName())
                .quantity(or.getQuantity())
                .description(or.getDescription())
                .orginalPrice(or.getOrginalPrice())
                .productDiscount(or.getProductDiscount())
                .productPrice(or.getProductPrice())
                .quantity(quantity)
                .total(or.getProductPrice()*quantity)
                .customer(customer)
                .build();
    }

    public OrderResponse mapToOrderedResponse(OrderedProduct o) {
        return OrderResponse.builder()
                .id(o.getId())
                .productId(o.getProductId())
                .productName(o.getProductName())
                .productPrice(o.getProductPrice())
                .description(o.getDescription())
                .orderedDate(o.getOrderedDate())
                .build();
    }

    public OrderTable mapToOrderTable(OrderedProduct o) {
        return OrderTable.builder()
                .customerName(o.getCustomer().getFirstName()+" "+ o.getCustomer().getLastName())
                .productName(o.getProductName())
                .price(o.getProductPrice())
                .quantity(o.getQuantity())
                .total(o.getTotal())
                .createdDate(o.getOrderedDate())
                .status(o.getStatus())
                .build();
    }
}
