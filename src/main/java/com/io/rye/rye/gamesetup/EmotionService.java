package com.io.rye.rye.gamesetup;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmotionService {

    public Iterable<String> fetchEmotions(int quantity) {
        List<String> emotions = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            emotions.add("HAPPY");
        }
        return emotions;
    }

}
