package com.io.rye.rye.controller;


import com.io.rye.rye.common.TokenUtils;
import com.io.rye.rye.dto.KidRegisterForm;
import com.io.rye.rye.dto.LoginForm;
import com.io.rye.rye.entity.Kid;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.service.KidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/kid")
public class KidController {
    private final KidService kidService;
    private final TokenUtils tokenUtils;

    @Autowired
    public KidController(KidService kidService, TokenUtils tokenUtils) {
        this.kidService = kidService;
        this.tokenUtils = tokenUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        String token = kidService.kidLogin(loginForm);
        if (token == null) {
            return new ResponseEntity<>("Wrong login or password", HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Kid register(@RequestBody KidRegisterForm kidRegisterForm, @RequestHeader HttpHeaders httpHeaders) throws ResponseStatusException {
        int id = Integer.parseInt(tokenUtils.getIdFromToken(httpHeaders));
        System.out.println(id);
        kidRegisterForm.setParentId(id);
        try {
            return kidService.registerKid(kidRegisterForm);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}
