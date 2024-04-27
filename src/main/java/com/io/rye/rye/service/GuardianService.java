package com.io.rye.rye.service;

import com.io.rye.rye.dto.GuardianRegisterForm;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Guardian;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.mappers.GuardianMapper;
import com.io.rye.rye.repository.GuardianRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${jwt.token.key}")
    private String key;

    @Autowired
    public GuardianService(GuardianRepository guardianRepository, PasswordEncoder passwordEncoder) {
        this.guardianRepository = guardianRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String guardianLogin(LoginForm loginForm) {
        Guardian guardian = guardianRepository.findByUsername(loginForm.getLogin()).get();
        String password = guardian.getPassword();
        if (passwordEncoder.matches(loginForm.getPassword(), password)) {
            long timeMillis = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(timeMillis))
                    .expiration(new Date(timeMillis + 15 * 60 * 1000))
                    .claim("id", guardian.getId())
                    .claim("role", "GUARDIAN")
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
            return token;
        } else {
            return null;
        }
    }

    public Guardian registerGuardian(GuardianRegisterForm guardianRegisterForm) throws InvalidInputException {
        Guardian guardian = GuardianMapper.fromRegisterForm(guardianRegisterForm);
        return guardianRepository.save(guardian);
    }
}