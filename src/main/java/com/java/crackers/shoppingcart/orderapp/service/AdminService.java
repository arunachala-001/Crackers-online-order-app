package com.java.crackers.shoppingcart.orderapp.service;

import com.java.crackers.shoppingcart.orderapp.model.Admin;
import com.java.crackers.shoppingcart.orderapp.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    public String findAdminbyName(String name) {
        return null;

    }


}
