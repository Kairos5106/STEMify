package com.example.stemify.ui.moduleA;

import java.util.List;

public class Question {
    String questionDesc;
    List<String> listOfAnswers; // not required for filBlank type
    String correctAnswer;
    int diagramId; // image to further depict question
    String diagramDesc;
    String type;

    // For general initialization, use this constructor
    public Question(String questionDesc, String type) {
        this.questionDesc = questionDesc;
        this.type = type;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public List<String> getListOfAnswers() {
        return listOfAnswers;
    }

    public void setListOfAnswers(List<String> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
