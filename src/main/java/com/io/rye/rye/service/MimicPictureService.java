package com.io.rye.rye.service;

import com.io.rye.rye.dto.RateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MimicPictureService {

    @Value("${aws.recognize.url}")
    private String url;


    public MimicPictureService() {

    }


    public RateDto ratePicture(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no file to rate");
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "image/jpeg");

            Resource resource = new InputStreamResource(file.getInputStream());

            HttpEntity<Resource> requestEntity
                    = new HttpEntity<>(resource, headers);


            RestTemplate restTemplate = new RestTemplate();


            // TODO: Parse JSON Response
            ResponseEntity<String> response = restTemplate
                    .postForEntity(url, requestEntity, String.class);

            System.out.println(response.getBody());

            // TODO: Remove this
            Path path = Paths.get("./src/main/resources/images", file.getOriginalFilename());
            Files.write(path, file.getBytes());

            return new RateDto(0, "HAPPY", "SAD");  // TODO: Fill with data from AWS
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to rate photo");
        }
    }
}
