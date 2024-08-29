package com.java.crackers.shoppingcart.orderapp.service;

import com.java.crackers.shoppingcart.orderapp.request.ProductRequest;
import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductService productService;

    ProductRequest productRequest = new ProductRequest();

    List<ProductResponse> productResponses = new ArrayList<>();
    MultipartFile image;
    String categoryName = null;

    @BeforeEach
    public void init() {
        productRequest.setProductName("Sivakasi Rockets");
        productRequest.setDescription("1 boxes");
        productRequest.setOrginalPrice(3000);
        productRequest.setProductDiscount(75);
        productRequest.setOrginalPrice(1500);

    }

    @Test
    void createProductTest() {
        Mockito.when(productService.createProduct("Rockets",productRequest,image ))
                .thenReturn(ResponseEntity.ok("Product Saved Successfully"));

        Assertions.assertEquals(ResponseEntity.ok("Product Saved Successfully"),
                productService.createProduct("Rockets", productRequest, image));
    }

    @Test
    void testingException() {
        Mockito.when(productService.getProductsById(0)).thenThrow(new IllegalArgumentException("Id not found"));

//        Mockito.doThrow(new IllegalArgumentException("Id not found")).when(productService.getProductsById(0));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            productService.getProductsById(0);
        });

    }
}
