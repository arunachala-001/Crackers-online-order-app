package com.java.crackers.shoppingcart.orderapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordered_product")
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private int productPrice;

    private int quantity;

    @Column(name = "orginal_price")
    private int orginalPrice;

    private String description;

    @Column(name = "discount")
    private int productDiscount;

    private int total;

    private String status;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @PrePersist
    protected void onCreate() {
        orderedDate=new Date();
    }
}
