package com.io.rye.rye.service;

import com.io.rye.rye.dto.KidRegisterForm;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Kid;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.mappers.KidMapper;
import com.io.rye.rye.repository.KidRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class KidService {

    private final KidRepository kidRepository;
    private final PasswordEncoder passwordEncoder;


    @Value("${jwt.token.key}")
    private String key;

    @Autowired
    public KidService(KidRepository kidRepository, PasswordEncoder passwordEncoder) {
        this.kidRepository = kidRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String kidLogin(LoginForm loginForm) {
        Kid kid = kidRepository.findByUsername(loginForm.getLogin()).get();
        String password = kid.getPassword();
        if (passwordEncoder.matches(loginForm.getPassword(), password)) {
            long timeMillis = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(timeMillis))
                    .expiration(new Date(timeMillis + 15 * 60 * 1000))
                    .claim("id", kid.getId())
                    .claim("role", "KID")
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
            return token;
        } else {
            return null;
        }
    }

    public Kid registerKid(KidRegisterForm kidRegisterForm) throws InvalidInputException {
        Kid kid = KidMapper.fromRegisterForm(kidRegisterForm);
        return kidRepository.save(kid);
    }
}