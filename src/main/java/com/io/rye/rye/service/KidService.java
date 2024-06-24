package com.io.rye.rye.service;

import com.io.rye.rye.dto.KidRegisterForm;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Kid;
import com.io.rye.rye.repository.KidRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class KidService {

    private final KidRepository kidRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public KidService(KidRepository kidRepository, PasswordEncoder passwordEncoder) {
        this.kidRepository = kidRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String kidLogin(LoginForm loginForm) {
        Optional<Kid> optionalKid = kidRepository.findByUsername(loginForm.getLogin());
        if (optionalKid.isPresent()) {
            Kid kid = optionalKid.get();
            String password = kid.getPassword();
            if (passwordEncoder.matches(loginForm.getPassword(), password)) {
                long timeMillis = System.currentTimeMillis();
                return Jwts.builder()
                        .issuedAt(new Date(timeMillis))
                        .expiration(new Date(timeMillis + 15 * 60 * 1000))
                        .claim("id", kid.getId())
                        //.claim("role", "KID")
                        //.signWith(SignatureAlgorithm.HS256, key)
                        .compact();
            }
        }
        return null;
    }

    public Kid registerKid(KidRegisterForm kidRegisterForm) {
        Kid kid = fromRegisterForm(kidRegisterForm);
        return kidRepository.save(kid);
    }

    private Kid fromRegisterForm(KidRegisterForm kidRegisterForm) {
        String username = kidRegisterForm.getUsername();
        String password = kidRegisterForm.getPassword();
        if (username != null && password != null) {
            var kid = new Kid();
            kid.setUsername(username);
            kid.setPassword(passwordEncoder.encode(password));
            kid.setGuardians(null);
            kid.setBalance(0);
            kid.setItems(null);
            return kid;
        }
        return null;
    }
}