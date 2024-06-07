package com.io.rye.rye.gamesetup;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmotionService {

    private final EmotionPicker emotionPicker = new EmotionPicker();

    private static class EmotionPicker {
        private final List<String> emotions = Arrays.asList("HAPPY", "CALM", "ANGRY", "SAD", "FEAR");
        private final Random random = new Random();

        public String getRandom() {
            return emotions.get(random.nextInt(emotions.size()));
        }
    }

    public Iterable<String> fetchEmotions(int quantity) {
        List<String> emotions = new LinkedList<>();
        for (int i = 0; i < quantity; i++) {
            emotions.add(emotionPicker.getRandom());
        }
        return emotions;
    }

}
