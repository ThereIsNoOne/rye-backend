package com.io.rye.rye.service;

import com.io.rye.rye.dto.KidRegisterForm;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Guardian;
import com.io.rye.rye.entity.Kid;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.mappers.KidMapper;
import com.io.rye.rye.repository.GuardianRepository;
import com.io.rye.rye.repository.KidRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class KidService {

    private final KidRepository kidRepository;
    private final PasswordEncoder passwordEncoder;
    private final GuardianRepository guardianRepository;


    @Value("${jwt.token.key}")
    private String key;

    @Autowired
    public KidService(KidRepository kidRepository, PasswordEncoder passwordEncoder, GuardianRepository guardianRepository) {
        this.kidRepository = kidRepository;
        this.passwordEncoder = passwordEncoder;
        this.guardianRepository = guardianRepository;
    }

    public String kidLogin(LoginForm loginForm) {

        Kid kid = kidRepository.findByUsername(loginForm.getLogin()).get();
        String password = kid.getPassword();

        if (passwordEncoder.matches(loginForm.getPassword(), password)) {
            long timeMillis = System.currentTimeMillis();
            String token = Jwts.builder()
                    .issuedAt(new Date(timeMillis))
                    .expiration(new Date(timeMillis + 24 * 60 * 60 * 1000))
                    .claim("id", kid.getId())
                    .claim("role", "ROLE_KID")
                    .signWith(SignatureAlgorithm.HS256, key)
                    .compact();
            return token;
        } else {
            return null;
        }
    }

    public Kid registerKid(KidRegisterForm kidRegisterForm) throws InvalidInputException {
        Kid kid = KidMapper.fromRegisterForm(kidRegisterForm);

        kid.setPassword(passwordEncoder.encode(kidRegisterForm.getPassword()));
        Guardian guardian = guardianRepository.findGuardianById(kidRegisterForm.getParentId()).orElse(null);
        Set<Guardian> guardians = new HashSet<>();
        guardians.add(guardian);

        kid.setGuardians(guardians);

        return kidRepository.save(kid);
    }
}