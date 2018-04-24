package com.example.albus.finalproject;

/**
 * A class to represent each question in one game
 */
public class Question {
    private String question;
    private String correctAnswer;
    private String[] incorrectAnswers;

    Question(String question, String correctAnswer, String[] incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
