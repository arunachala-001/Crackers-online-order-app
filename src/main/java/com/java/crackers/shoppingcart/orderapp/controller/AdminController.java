package com.java.crackers.shoppingcart.orderapp.controller;

import com.java.crackers.shoppingcart.orderapp.jwtConfig.CustomUserDetailsService;
import com.java.crackers.shoppingcart.orderapp.jwtConfig.JWTUtils;
import com.java.crackers.shoppingcart.orderapp.jwtConfig.LoginRequest;
import com.java.crackers.shoppingcart.orderapp.jwtConfig.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AdminController {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private CustomUserDetailsService userDetailsService;

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

//        return ResponseEntity.status(401).body("Authentication failed");
    }

//    public static class TokenResponse {
//        private String token;
//
//        public TokenResponse(String token) {
//            this.token = token;
//        }
//
//        public String getToken() {
//            return token;
//        }
//    }

}
