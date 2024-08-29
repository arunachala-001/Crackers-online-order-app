package com.java.crackers.shoppingcart.orderapp.mapper;

import com.java.crackers.shoppingcart.orderapp.model.Customer;
import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import com.java.crackers.shoppingcart.orderapp.request.OrderRequest;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderMapper {

    public OrderedProduct mapToOrderedProduct(OrderRequest orderRequest, Customer customer) {
        return OrderedProduct.builder()
                .productId(orderRequest.getProductId())
                .productName(orderRequest.getProductName())
                .description(orderRequest.getDescription())
                .orginalPrice(orderRequest.getOrginalPrice())
                .productDiscount(orderRequest.getProductDiscount())
                .productPrice(orderRequest.getProductPrice())
                .customer(customer)
                .build();
    }
}
