package com.io.rye.rye.controller;


import com.io.rye.rye.dto.RateDto;
import com.io.rye.rye.dto.ResultDto;
import com.io.rye.rye.service.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/aws")
public class AWSController {

    private final AWSService awsService;

    @Autowired
    public AWSController(AWSService awsService) {
        this.awsService = awsService;
    }

    @PostMapping("/extractEmotion")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody RateDto getEmotion(@RequestBody MultipartFile file) throws ResponseStatusException {
        try {
            RateDto result = awsService.extractEmotion(file);
            if (result == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Emotion");
            } else {
                return result;
            }
        } catch (RuntimeException | IOException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }

    }
}
