package com.example.stemify.ui.moduleA;

import java.util.HashMap;
import java.util.Map;

public class FillBlank extends Question{
    Map<Integer, String> correctAnswers;
    public FillBlank() {
        super();
        this.correctAnswers = new HashMap<>();
    }

    public FillBlank(String questionDesc){
        super(questionDesc);
        this.correctAnswers = new HashMap<>();
    }

    public Map<Integer, String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Map<Integer, String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void addAnswer(String answer) {
        int nextKey = getNumberOfAnswers() + 1;
        correctAnswers.put(nextKey, answer);
    }

    public int getNumberOfAnswers(){
        return correctAnswers.size();
    }
}
