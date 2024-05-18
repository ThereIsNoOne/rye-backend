package com.io.rye.rye.controller;


import com.io.rye.rye.service.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/aws")
public class AWSController {

    private final AWSService awsService;

    @Autowired
    public AWSController(AWSService awsService) {
        this.awsService = awsService;
    }

    @PostMapping("/extractEmotion")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> getEmotion(@RequestPart MultipartFile file) throws ResponseStatusException {
        try {
            String emotion = awsService.extractEmotion(file);
            if (emotion == null) {
                return new ResponseEntity<>("Invalid request", HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(emotion, HttpStatus.OK);
            }
        } catch (RuntimeException | IOException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }

    }
}
