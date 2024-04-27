package com.io.rye.rye.service;

import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Admin;
import com.io.rye.rye.repository.AdminRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${jwt.token.key}")
    private String key;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String adminLogin(LoginForm loginForm) {
        Admin admin = adminRepository.findByLogin(loginForm.getLogin()).get();
        String password = admin.getPassword();
        if (!passwordEncoder.matches(loginForm.getPassword(), password)) {
            long timeMillis = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(timeMillis))
                    .expiration(new Date(timeMillis + 15 * 60 * 1000))
                    .claim("id", admin.getId())
                    .claim("role", "ADMIN")
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
            return token;
        } else {
            return null;
        }
    }
}
