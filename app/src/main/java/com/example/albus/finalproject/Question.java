package com.example.albus.finalproject;

import java.util.Random;

/**
 * A class to represent each question in one game
 */
public class Question {
    private String question;
    private String correctAnswer;
    private String[] answers;

    Question(String question, String correctAnswer, String[] answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public String[] getAnswers() {
        return this.answers;
    }
    public String[] shuffleAnswers() {
        Random seed = new Random();
        for (int i = answers.length - 1; i > 0; i--)
        {
            int index = seed.nextInt(i + 1);
            String a = answers[index];
            answers[index] = answers[i];
            answers[i] = a;
        }
        return answers;
    }
}
