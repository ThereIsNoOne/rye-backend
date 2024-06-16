package com.io.rye.rye.gamesetup;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmotionService {

    private final EmotionPicker emotionPicker = new EmotionPicker();

    public List<List<String>> fetchUnique(int quantity) {
        List<List<String>> emotions = new LinkedList<>();
        while (emotions.size() < quantity) {
            List<String> random = emotionPicker.getRandoms();
            emotions.add(random);
        }

        return emotions;
    }

    private static class EmotionPicker {
        private final List<String> emotions = Arrays.asList("HAPPY", "CALM", "ANGRY", "SAD", "FEAR");
        private final Random random = new Random();

        public String getRandom() {
            return emotions.get(random.nextInt(emotions.size()));
        }

        public List<String> getRandoms() {
            List<String> randoms = new LinkedList<>();
            // 3 is number of answers
            while (randoms.size() < 3) {
                String random = getRandom();
//                if (emotions.contains(random)) {
//                    continue;
//                }
                randoms.add(random);
            }
            return randoms;
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
