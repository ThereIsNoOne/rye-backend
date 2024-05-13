package com.io.rye.rye.controller;

import com.io.rye.rye.service.MimicPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/mimic")
public class MimicPictureController {

    private final MimicPictureService mimicPictureService;

    @Autowired
    public MimicPictureController(MimicPictureService mimicPictureService) {
        this.mimicPictureService = mimicPictureService;
    }

    @PostMapping("/rate")
    public ResponseEntity<String> ratePicture(@RequestBody MultipartFile file) {
        return mimicPictureService.ratePicture(file);
    }

}
