package com.io.rye.rye.controller;

import com.io.rye.rye.dto.GuardianRegisterForm;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Guardian;
import com.io.rye.rye.service.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guardian")
public class GuardianController {
    private final GuardianService guardianService;

    @Autowired
    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        String token = guardianService.guardianLogin(loginForm);
        if (token == null) {
            return new ResponseEntity<>("Wrong login or password", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Guardian register(@RequestBody GuardianRegisterForm guardianRegisterForm) {
        return guardianService.registerGuardian(guardianRegisterForm);
    }
}
