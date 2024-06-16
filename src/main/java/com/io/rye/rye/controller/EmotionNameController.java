package com.io.rye.rye.controller;

import com.io.rye.rye.gamesetup.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/emotion")
public class EmotionNameController {

    private final EmotionService emotionService;

    @Autowired
    public EmotionNameController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @GetMapping("/get")
    public @ResponseBody Iterable<String> getEmotionName(@RequestParam int quantity) {
        // TODO: Generate random
        return emotionService.fetchEmotions(quantity);
    }

    @GetMapping("/getAnswers")
    public @ResponseBody List<List<String>> getAnswers(@RequestParam int quantity) {
        return emotionService.fetchUnique(quantity);
    }
}
