package com.example.stemify.ui.moduleA;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Question {
    String questionType; // can be "MCQ" or "FillBlank"
    String questionDesc;
    String correctAnswer;
    String diagramName; // image to further depict question
    String diagramDesc;

    // Required empty constructor
    public Question(){
        this.questionType = "";
        this.questionDesc = "";
        this.correctAnswer = "";
        this.diagramName = "";
        this.diagramDesc = "";
    }

    // For general initialization, use this constructor
    public Question(String questionDesc) {
        this.questionType = "MCQ";
        this.questionDesc = questionDesc;
        this.correctAnswer = "";
        this.diagramName = "";
        this.diagramDesc = "";
    }

    public Question(String questionDesc, String correctAnswer) {
        this.questionType = "MCQ";
        this.questionDesc = questionDesc;
        this.correctAnswer = correctAnswer;
        this.diagramName = "";
        this.diagramDesc = "";
    }

    public Question(String questionType, String questionDesc, String correctAnswer, String diagramName, String diagramDesc) {
        this.questionType = questionType;
        this.questionDesc = questionDesc;
        this.correctAnswer = correctAnswer;
        this.diagramName = diagramName;
        this.diagramDesc = diagramDesc;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDiagramName() {
        return diagramName;
    }

    public void setDiagramName(String diagramName) {
        this.diagramName = diagramName;
    }

    public String getDiagramDesc() {
        return diagramDesc;
    }

    public void setDiagramDesc(String diagramDesc) {
        this.diagramDesc = diagramDesc;
    }
}
