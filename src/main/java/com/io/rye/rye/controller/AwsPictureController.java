package com.io.rye.rye.controller;

import com.io.rye.rye.gamesetup.PictureService;
import com.io.rye.rye.dto.PictureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/pic")
public class AwsPictureController {

    private final PictureService pictureService;

    @Autowired
    public AwsPictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/singlePic")
    public @ResponseBody PictureDto getSinglePicture() {
        try {
            return pictureService.fetchRandom();
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot fetch picture");
        }
    }

    @GetMapping("/get")
    public @ResponseBody Iterable<PictureDto> getPicture(@RequestParam int quantity) {
        try {
            return pictureService.fetch(quantity);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot fetch picture");
        }
    }

}
