package com.example.stemify.ui.moduleA;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    String subjectTitle;
    List<Grade> listOfGrades;

    public Subject(String subjectTitle) {
        this.subjectTitle = subjectTitle;
        this.listOfGrades = new ArrayList<>();
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public List<Grade> getListOfGrades() {
        return listOfGrades;
    }

    public void setListOfGrades(List<Grade> listOfGrades) {
        this.listOfGrades = listOfGrades;
    }

    public void addGrade(Grade grade){
        listOfGrades.add(grade);
    }
}
