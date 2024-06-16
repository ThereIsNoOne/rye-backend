package com.io.rye.rye.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.io.rye.rye.dto.RateDto;
import com.io.rye.rye.dto.ResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AWSService {

    @Value("${aws.url}")
    private String awsURL;

    public RateDto extractEmotion(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/jpeg");

        Resource resource = new InputStreamResource(file.getInputStream());

        HttpEntity<Resource> requestEntity = new HttpEntity<>(resource, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(awsURL, requestEntity, String.class);


        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.getBody());

        JsonNode emotionsNode = rootNode.path("faces").get(0).path("Emotions");

        String topEmotion = extractAnswer(emotionsNode);


        RateDto rate = new RateDto();
        rate.setUserAnswer(topEmotion);
        rate.setCorrectAnswer(file.getOriginalFilename().split("\\.")[0]);
        rate.setScore(rate.getCorrectAnswer().equals(rate.getUserAnswer()) ? 1 : 0);

        return rate;
    }

    private static String extractAnswer(JsonNode emotionsNode) {
        String topEmotion = null;
        double highestConfidence = 0.0;

        for (JsonNode emotionNode : emotionsNode) {
            String type = emotionNode.get("Type").asText();

            double confidence = emotionNode.get("Confidence").asDouble();

            if (confidence > highestConfidence) {
                highestConfidence = confidence;
                topEmotion = type;
            }
        }
        return topEmotion;
    }
}
