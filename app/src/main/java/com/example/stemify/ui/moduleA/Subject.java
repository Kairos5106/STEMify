package com.example.stemify.ui.moduleA;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    String subjectTitle;
    List<ResourceTopic> resourceTopics;

    public Subject(String subjectTitle) {
        this.subjectTitle = subjectTitle;
        this.resourceTopics = new ArrayList<>();
    }

    public Subject(String subjectTitle, List<ResourceTopic> resourceTopics) {
        this.subjectTitle = subjectTitle;
        this.resourceTopics = resourceTopics;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public List<ResourceTopic> getTopics() {
        return resourceTopics;
    }

    public void setTopics(List<ResourceTopic> resourceTopics) {
        this.resourceTopics = resourceTopics;
    }
}
