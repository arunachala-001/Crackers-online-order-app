package com.java.crackers.shoppingcart.orderapp.response;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private UUID id;
    private UUID productId;
    private String productName;
    private int productPrice;
    private int orginalPrice;
    private String description;
    private int productDiscount;
    private Date orderedDate;

}
