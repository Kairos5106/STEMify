package com.example.stemify.ui.moduleA;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends Question{
    List<String> listOfAnswers;

    public MultipleChoice() {
        super();
        this.listOfAnswers = new ArrayList<>();
    }

    public List<String> getListOfAnswers() {
        return listOfAnswers;
    }

    public void setListOfAnswers(List<String> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }
}
