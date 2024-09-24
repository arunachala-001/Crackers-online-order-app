package com.java.crackers.shoppingcart.orderapp.controller;

import com.java.crackers.shoppingcart.orderapp.model.Product;
import com.java.crackers.shoppingcart.orderapp.request.CategoryRequest;
import com.java.crackers.shoppingcart.orderapp.request.ProductRequest;
import com.java.crackers.shoppingcart.orderapp.response.CategoryResponse;
import com.java.crackers.shoppingcart.orderapp.service.CategoryService;
import com.java.crackers.shoppingcart.orderapp.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final ProductService productService;

    @GetMapping("check")
    public String ApiChecking() {
        return "API Working fine as expected";
    }

    @GetMapping("/category")
    public List<CategoryResponse> fetchAllProduct() {
        return categoryService.getAllcategories();
    }


    //Store category items
    @PostMapping("/store/category")
    public ResponseEntity<String> storeCategory(@RequestPart CategoryRequest categoryRequest, @RequestPart("image") MultipartFile image) {
        return categoryService.createCategory(categoryRequest, image);
    }


    //Get category by CategoryName
    @GetMapping("/category/{name}")
    public CategoryResponse selectByCategory(@PathVariable String name) {
        return categoryService.getByCategoryName(name);
    }

    //find category by search
    @GetMapping("/category/search")
    public List<CategoryResponse> searchByCategory(@RequestParam String name) {
        return categoryService.searchBycategories(name);
    }
}
