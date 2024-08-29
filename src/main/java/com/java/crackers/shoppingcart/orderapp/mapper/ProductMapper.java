package com.java.crackers.shoppingcart.orderapp.mapper;


import com.java.crackers.shoppingcart.orderapp.model.Product;
import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class ProductMapper {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProductResponse mapToproductResponse(Product p) {
        if(p.getImage() != null) {
            String imageUrl = Paths.get(uploadDir).resolve(p.getImage()).toString();
            p.setImage(imageUrl);
        }
        return ProductResponse.builder()
                .id(p.getProductId())
                .productName(p.getProductName())
                .orginalPrice(p.getOrginalPrice())
                .productPrice(p.getProductPrice())
                .description(p.getDescription())
                .productDiscount(p.getProductDiscount())
                .image(p.getImage())
                .category(p.getCategory()).build();
    }
}
