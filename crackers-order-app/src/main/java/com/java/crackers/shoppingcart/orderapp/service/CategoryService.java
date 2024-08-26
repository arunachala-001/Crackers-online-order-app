package com.java.crackers.shoppingcart.orderapp.service;

import com.java.crackers.shoppingcart.orderapp.model.Category;
import com.java.crackers.shoppingcart.orderapp.repository.CategoryRepository;

import com.java.crackers.shoppingcart.orderapp.request.CategoryRequest;

import com.java.crackers.shoppingcart.orderapp.response.CategoryResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepo;

    @Value("${file.upload-dir}")
    private String uploadDir;

   //Fetching All Products
    public List<CategoryResponse> getAllcategories() {
        List<Category> category = categoryRepo.findAll();
        return category.stream().map(this::mapTocategoryResponse).toList();
    }

    private CategoryResponse mapTocategoryResponse(Category c) {
        if(c.getImage() != null) {
            String imageUrl = Paths.get(uploadDir).resolve(c.getImage()).toString();
            c.setImage(imageUrl);
        }
        return CategoryResponse.builder()
                .categoryId(c.getId())
                .categoryName(c.getName())
                .product(c.getProduct())
                .image(c.getImage())
                .build();
    }

// ---------------------------------------------------------------------------------------

    //Store All Product
    @Transactional
    public ResponseEntity<String> createCategory(CategoryRequest categoryRequests, MultipartFile imageFile) {
        try {
            if(categoryRequests.getCategoryName() != null && categoryRequests.getCategoryName().length()>1
                    && !imageFile.isEmpty()) {
                String fileName = saveImagetoFile(imageFile, categoryRequests);
                Category category = Category.builder()
                        .name(categoryRequests.getCategoryName())
                        .image(fileName)
                        .build();

                categoryRepo.save(category);

                return ResponseEntity.ok("Product Saved Successfully");
            } else {
                throw new NullPointerException("Product Name must be contain some values");
            }

        }
        catch(IllegalArgumentException | IOException e) {
            return ResponseEntity.badRequest().body("Invalid args passed check datatypes and json syntaxs");
        }

    }

    private String saveImagetoFile(MultipartFile imageFile, CategoryRequest categoryRequest) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName=categoryRequest.getCategoryName()+"_"+ imageFile.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(imageFile.getInputStream(),filePath);
        return fileName;
    }
//-----------------------------------------------------------------------------------------------
    // get all the category by category name
    public CategoryResponse getByCategoryName(String name) {
        Category category = categoryRepo.findByName(name);
        return mapTocategoryResponse(category);
    }
// -----------------------------------------------------------------------------------
    //Search all category by name
    public List<CategoryResponse> searchBycategories(String name) {
        List<Category> category = categoryRepo.findByNameContainingIgnoreCase(name);
        return category.stream().map(this::mapTocategoryResponse).toList();
    }
}
