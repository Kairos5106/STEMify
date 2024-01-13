package com.example.stemify.ui.moduleA;

public class SelectHistory {
    String selectedSubject, selectedGrade, selectedTopic, selectedSubtopic;

    public SelectHistory() {
        this.selectedSubject = "";
        this.selectedGrade = "";
        this.selectedTopic = "";
        this.selectedSubtopic = "";
    }

    public SelectHistory(String selectedSubject, String selectedGrade, String selectedTopic, String selectedSubtopic) {
        this.selectedSubject = selectedSubject;
        this.selectedGrade = selectedGrade;
        this.selectedTopic = selectedTopic;
        this.selectedSubtopic = selectedSubtopic;
    }

    public String getSelectedSubject() {
        return selectedSubject;
    }

    public void setSelectedSubject(String selectedSubject) {
        this.selectedSubject = selectedSubject;
    }

    public String getSelectedGrade() {
        return selectedGrade;
    }

    public void setSelectedGrade(String selectedGrade) {
        this.selectedGrade = selectedGrade;
    }

    public String getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(String selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    public String getSelectedSubtopic() {
        return selectedSubtopic;
    }

    public void setSelectedSubtopic(String selectedSubtopic) {
        this.selectedSubtopic = selectedSubtopic;
    }

    @Override
    public String toString() {
        return "SelectHistory{" +
                "selectedSubject='" + selectedSubject + '\'' +
                ", selectedGrade='" + selectedGrade + '\'' +
                ", selectedTopic='" + selectedTopic + '\'' +
                ", selectedSubtopic='" + selectedSubtopic + '\'' +
                '}';
    }
}
