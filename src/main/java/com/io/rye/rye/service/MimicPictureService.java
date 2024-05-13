package com.io.rye.rye.service;

import com.io.rye.rye.dto.RateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MimicPictureService {

    public MimicPictureService() {

    }


    public RateDto ratePicture(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no file to rate");
        }

        // TODO: Process file with AWS
        try {
            Path path = Paths.get("./src/main/resources/images", file.getOriginalFilename());
            Files.write(path, file.getBytes());

            return new RateDto(0, "HAPPY", "SAD");  // TODO: Fill with data from AWS
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to rate photo");
        }
    }
}
