package com.io.rye.rye.dto;

public class RateDto {

    public RateDto() {}

    public RateDto(int score, String correctAnswer, String userAnswer) {
        this.score = score;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }

    private int score;
    private String correctAnswer;
    private String userAnswer;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
