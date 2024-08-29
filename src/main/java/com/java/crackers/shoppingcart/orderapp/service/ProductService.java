package com.java.crackers.shoppingcart.orderapp.service;

import com.java.crackers.shoppingcart.orderapp.mapper.ProductMapper;
import com.java.crackers.shoppingcart.orderapp.model.Category;
import com.java.crackers.shoppingcart.orderapp.model.Product;

import com.java.crackers.shoppingcart.orderapp.repository.CategoryRepository;
import com.java.crackers.shoppingcart.orderapp.repository.ProductRepository;

import com.java.crackers.shoppingcart.orderapp.request.ProductRequest;

import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepo;
    @Autowired
    private final CategoryRepository categoryRepo;

    @Autowired
    private ProductMapper productMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

//    private static Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Transactional
    public ResponseEntity<String> createProduct(String name, ProductRequest productRequest, MultipartFile image) {
        try {
            Category category = categoryRepo.findByName(name);
            if(category.getName() != null && !image.isEmpty()) {
                String fileName = saveImagetoFile(image, productRequest);
                Product product = Product.builder()
                        .ProductName(productRequest.getProductName())
                        .description(productRequest.getDescription())
                        .orginalPrice(productRequest.getOrginalPrice())
                        .ProductDiscount(productRequest.getProductDiscount())
                        .ProductPrice(productRequest.getProductPrice())
                        .image(fileName)
                        .category(category)
                        .build();

                productRepo.save(product);
                return ResponseEntity.ok("Product Saved Successfully");
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (IllegalArgumentException | IOException e) {
            return ResponseEntity.notFound().build();
        }

    }

    private String saveImagetoFile(MultipartFile image, ProductRequest productRequest) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName=productRequest.getProductName()+"_"+ image.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(image.getInputStream(),filePath);
        return fileName;
    }


    public List<ProductResponse> getProductsById(long id) {
        Category category = categoryRepo.findById(id).orElseThrow();
        if(category.getId() != 0) {
            List<Product> product = category.getProduct();
            return product.stream().map((p) -> productMapper.mapToproductResponse(p)).toList();
        } else {
            throw new IllegalArgumentException("Id not found");
        }

    }

//    private ProductResponse mapToproductResponse(Product p) {
//        if(p.getImage() != null) {
//            String imageUrl = Paths.get(uploadDir).resolve(p.getImage()).toString();
//            p.setImage(imageUrl);
//        }
//        return ProductResponse.builder()
//                .id(p.getProductId())
//                .productName(p.getProductName())
//                .orginalPrice(p.getOrginalPrice())
//                .productPrice(p.getProductPrice())
//                .description(p.getDescription())
//                .productDiscount(p.getProductDiscount())
//                .image(p.getImage())
//                .category(p.getCategory()).build();
//    }

    public List<ProductResponse> sortByPrice(long id) {
        Category category = categoryRepo.findById(id).orElseThrow();
        List<Product> product = productRepo.sortByProductPrice(category.getId());
        return product.stream().map((p) -> productMapper.mapToproductResponse(p)).toList();
    }
}
