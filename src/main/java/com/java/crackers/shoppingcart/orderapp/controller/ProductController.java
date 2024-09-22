package com.java.crackers.shoppingcart.orderapp.controller;

import com.java.crackers.shoppingcart.orderapp.request.ProductRequest;
import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;
import com.java.crackers.shoppingcart.orderapp.service.CategoryService;
import com.java.crackers.shoppingcart.orderapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final ProductService productService;



    @GetMapping("/{id}")
    public List<ProductResponse> fetchAllProductsByCategoryId(@PathVariable long id) {
        return productService.getProductsById(id);
    }

    @GetMapping("/price/sort/{id}")
    public List<ProductResponse> sortByprice(@PathVariable long id) {
        return productService.sortByPrice(id);
    }


}
