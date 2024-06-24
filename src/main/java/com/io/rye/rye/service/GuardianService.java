package com.io.rye.rye.service;

import com.io.rye.rye.dto.GuardianRegisterForm;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Guardian;
import com.io.rye.rye.repository.GuardianRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public GuardianService(GuardianRepository guardianRepository, PasswordEncoder passwordEncoder) {
        this.guardianRepository = guardianRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String guardianLogin(LoginForm loginForm) {
        Optional<Guardian> optionalGuardian = guardianRepository.findByUsername(loginForm.getLogin());
        if (optionalGuardian.isPresent()) {
            Guardian guardian = optionalGuardian.get();
            String password = guardian.getPassword();
            if (passwordEncoder.matches(loginForm.getPassword(), password)) {
                long timeMillis = System.currentTimeMillis();
                return Jwts.builder()
                        .issuedAt(new Date(timeMillis))
                        .expiration(new Date(timeMillis + 15 * 60 * 1000))
                        .claim("id", guardian.getId())
                        .claim("role", "GUARDIAN")
                        //.signWith(SignatureAlgorithm.HS256, key)
                        .compact();
            }
        }
        return null;
    }

    public Guardian registerGuardian(GuardianRegisterForm guardianRegisterForm){
        Guardian guardian = fromRegisterForm(guardianRegisterForm);
        return guardianRepository.save(guardian);
    }

    private Guardian fromRegisterForm(GuardianRegisterForm guardianRegisterForm) {
        String username = guardianRegisterForm.getUsername();
        String password = guardianRegisterForm.getPassword();
        String email = guardianRegisterForm.getEmail();
        String familyMember = guardianRegisterForm.getFamilyMember();
        if (username != null && password !=null && email != null && familyMember != null) {
            Guardian guardian = new Guardian();
            guardian.setUsername(username);
            guardian.setPassword(passwordEncoder.encode(password));
            guardian.setEmail(email);
            guardian.setFamilyMember(familyMember);
            guardian.setKids(null);
            return guardian;
        }
        return null;
    }
}