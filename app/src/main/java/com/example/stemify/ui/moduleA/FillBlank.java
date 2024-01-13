package com.example.stemify.ui.moduleA;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Parcel
public class FillBlank extends Question {
    List<String> correctAnswers;
    public FillBlank() {
        super();
        this.correctAnswers = new ArrayList<>();
    }

    public FillBlank(String questionDesc){
        super(questionDesc);
        this.correctAnswers = new ArrayList<>();
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void addAnswer(String answer) {
        correctAnswers.add(answer);
    }

    public int getNumberOfAnswers(){
        return correctAnswers.size();
    }
}
