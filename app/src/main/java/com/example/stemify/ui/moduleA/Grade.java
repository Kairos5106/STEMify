package com.example.stemify.ui.moduleA;

import java.util.ArrayList;
import java.util.List;

public class Grade {
    String gradeTitle;

    public Grade(String gradeTitle) {
        this.gradeTitle = gradeTitle;
    }

    public Grade(String gradeTitle, List<ResourceTopic> listOfTopics) {
        this.gradeTitle = gradeTitle;
    }

    public String getGradeTitle() {
        return gradeTitle;
    }

    public void setGradeTitle(String gradeTitle) {
        this.gradeTitle = gradeTitle;
    }
}
