package com.io.rye.rye.controller;

import com.io.rye.rye.dto.RateDto;
import com.io.rye.rye.service.MimicPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/mimic")
public class RateMimicController {

    private final MimicPictureService mimicPictureService;

    @Autowired
    public RateMimicController(MimicPictureService mimicPictureService) {
        this.mimicPictureService = mimicPictureService;
    }

    @PostMapping("/rate")
    public @ResponseBody RateDto ratePicture(@RequestBody MultipartFile file) {
        return mimicPictureService.ratePicture(file);
    }

}
