package com.example.stemify.ui.moduleA;

import java.util.ArrayList;
import java.util.List;

public class Grade {
    String gradeTitle;
    List<ResourceTopic> listOfTopics;

    public Grade(String gradeTitle) {
        this.gradeTitle = gradeTitle;
        this.listOfTopics = new ArrayList<>();
    }

    public Grade(String gradeTitle, List<ResourceTopic> listOfTopics) {
        this.gradeTitle = gradeTitle;
        this.listOfTopics = listOfTopics;
    }

    public String getGradeTitle() {
        return gradeTitle;
    }

    public void setGradeTitle(String gradeTitle) {
        this.gradeTitle = gradeTitle;
    }

    public List<ResourceTopic> getListOfTopics() {
        return listOfTopics;
    }

    public void setListOfTopics(List<ResourceTopic> listOfTopics) {
        this.listOfTopics = listOfTopics;
    }
}
