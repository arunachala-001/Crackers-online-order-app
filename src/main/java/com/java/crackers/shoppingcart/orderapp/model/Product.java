package com.java.crackers.shoppingcart.orderapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID ProductId;

    @Column(name = "name")
    private String ProductName;

    @Column(name = "price")
    private int ProductPrice;

    @Column(name = "orginalprice")
    private int orginalPrice;

    private String description;

    @Column(name = "discount")
    private int ProductDiscount;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

//    @ManyToOne
//    @JoinColumn(name = "ordertable_id")
//    @JsonIgnore
//    private OrderTable orderTable;


}
