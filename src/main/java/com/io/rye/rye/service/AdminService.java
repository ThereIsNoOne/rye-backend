package com.io.rye.rye.service;

import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Admin;
import com.io.rye.rye.repository.AdminRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String adminLogin(LoginForm loginForm) {
        Optional<Admin> optionalAdmin = adminRepository.findByLogin(loginForm.getLogin());
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            String password = admin.getPassword();
            if (passwordEncoder.matches(loginForm.getPassword(), password)) {
                long timeMillis = System.currentTimeMillis();
                return Jwts.builder()
                        .issuedAt(new Date(timeMillis))
                        .expiration(new Date(timeMillis + 15 * 60 * 1000))
                        .claim("id", admin.getId())
                        .claim("role", "ADMIN")
//                        .signWith(SignatureAlgorithm.HS256, key)
                        .compact();
            }
        }
        return null;
    }
}
