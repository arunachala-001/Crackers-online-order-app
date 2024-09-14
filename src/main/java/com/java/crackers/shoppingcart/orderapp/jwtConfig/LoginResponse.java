package com.java.crackers.shoppingcart.orderapp.jwtConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String username;
    private String jwtToken;
    private List<String> roles;
}
