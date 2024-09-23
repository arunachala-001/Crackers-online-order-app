package com.java.crackers.shoppingcart.orderapp.controller;

import com.java.crackers.shoppingcart.orderapp.jwtConfig.JWTUtils;
import com.java.crackers.shoppingcart.orderapp.request.CategoryRequest;
import com.java.crackers.shoppingcart.orderapp.request.CustomerOrderRequest;
import com.java.crackers.shoppingcart.orderapp.request.ProductRequest;
import com.java.crackers.shoppingcart.orderapp.response.CategoryResponse;
import com.java.crackers.shoppingcart.orderapp.response.CustomerResponse;
import com.java.crackers.shoppingcart.orderapp.response.OrderResponse;
import com.java.crackers.shoppingcart.orderapp.response.OrderTable;
import com.java.crackers.shoppingcart.orderapp.service.CategoryService;
import com.java.crackers.shoppingcart.orderapp.service.OrderService;
import com.java.crackers.shoppingcart.orderapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://sivakasi-crackers-online.netlify.app/")
public class AdminController {

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/admin/store/category")
    public ResponseEntity<String> storeCategory(@RequestPart CategoryRequest categoryRequest, @RequestPart("image") MultipartFile image) {
        return categoryService.createCategory(categoryRequest, image);
    }

    @GetMapping("/admin/category")
    public List<CategoryResponse> fetchAllCategory() {
        return categoryService.getAllcategories();
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestParam String username, @RequestParam String password) {
        try {
            System.out.println(username+""+password);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtUtils.generateToken(username);
        return ResponseEntity.ok(token);

    }

    @DeleteMapping("/admin/category/delete/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable long id) {
        return categoryService.deleteCategory(id);
    }

    //Store products
    @PostMapping("/admin/store/{name}")
    public ResponseEntity<String> storeProducts(@PathVariable String name,
                                                @RequestPart ProductRequest productRequest,
                                                @RequestPart MultipartFile image) {
        return productService.createProduct(name, productRequest, image);
    }

    @DeleteMapping("/admin/product/delete/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable UUID productId) {
        return productService.deleteProduct(productId);
    }

    @GetMapping("/admin/customers")
    public List<CustomerResponse> fetchAllOrderDetails() {
        return orderService.getAllCustomers();
    }

    @PutMapping("/admin/customer-order/status/{id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable UUID id,
                                                    @RequestBody CustomerOrderRequest customerOrderRequest) {
        return orderService.updateCustomerOrderStatus(id, customerOrderRequest );
    }

    @GetMapping("/admin/customer-order/get/status/{orderStatus}")
    public List<CustomerResponse> fetchCustomerByOrderStatus(@PathVariable String orderStatus) {
        return orderService.getCustomerOrderStatusByOrderStatus(orderStatus);
    }

    @GetMapping("/admin/all-orders")
    public List<OrderResponse> fetchOrderDetailsByCustId() {
        return orderService.getAllOrderDetails();
    }


}
