package com.example.stemify.ui.moduleA;

import java.util.List;

public class Question {
    String questionDesc;
    String correctAnswer;
    int diagramId; // image to further depict question
    String diagramDesc;

    // Required empty constructor
    public Question(){
        this.questionDesc = "";
        this.correctAnswer = "";
        this.diagramId = 0;
        this.diagramDesc = "";
    }

    // For general initialization, use this constructor
    public Question(String questionDesc) {
        this.questionDesc = questionDesc;
        this.correctAnswer = "";
        this.diagramId = 0;
        this.diagramDesc = "";
    }

    public Question(String questionDesc, String correctAnswer) {
        this.questionDesc = questionDesc;
        this.correctAnswer = correctAnswer;
    }

    public Question(String questionDesc, String correctAnswer, int diagramId, String diagramDesc) {
        this.questionDesc = questionDesc;
        this.correctAnswer = correctAnswer;
        this.diagramId = diagramId;
        this.diagramDesc = diagramDesc;
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

    public int getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(int diagramId) {
        this.diagramId = diagramId;
    }

    public String getDiagramDesc() {
        return diagramDesc;
    }

    public void setDiagramDesc(String diagramDesc) {
        this.diagramDesc = diagramDesc;
    }
}
