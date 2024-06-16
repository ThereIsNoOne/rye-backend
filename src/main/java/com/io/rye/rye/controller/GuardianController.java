package com.io.rye.rye.controller;

import com.io.rye.rye.common.TokenUtils;
import com.io.rye.rye.dto.GuardianRegisterForm;
import com.io.rye.rye.dto.KidDto;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Guardian;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.service.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/guardian")
public class GuardianController {
    private final GuardianService guardianService;
    private final TokenUtils tokenUtils;

    @Autowired
    public GuardianController(GuardianService guardianService, TokenUtils tokenUtils) {
        this.guardianService = guardianService;
        this.tokenUtils = tokenUtils;
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
    public @ResponseBody Guardian register(@RequestBody GuardianRegisterForm guardianRegisterForm) throws ResponseStatusException {
        try {
            return guardianService.registerGuardian(guardianRegisterForm);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/kids")
    public @ResponseBody Iterable<KidDto> getKids(@RequestHeader HttpHeaders headers) {
        String id = tokenUtils.getIdFromToken(headers);
        return guardianService.getKids(Integer.parseInt(id));
    }
}
