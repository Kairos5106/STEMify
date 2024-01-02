package com.example.stemify.ui.moduleA;

import java.util.ArrayList;
import java.util.List;

public class ResourceTopic {
    String title;
    List<Subtopic> subtopics;

    public ResourceTopic(String title) {
        this.title = title;
        this.subtopics = new ArrayList<Subtopic>();
    }

    public ResourceTopic(String title, List<Subtopic> subtopics) {
        this.title = title;
        this.subtopics = subtopics;
    }
}
