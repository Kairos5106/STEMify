package com.example.stemify.ui.moduleA;

import java.util.ArrayList;
import java.util.List;

public class ResourceTopic {
    String title, topicImageName;

    public ResourceTopic() {
        this.topicImageName = "sampleimage";
    }

    public String getTopicImageName() {
        return topicImageName;
    }

    public void setTopicImageName(String topicImageName) {
        this.topicImageName = topicImageName;
    }

    public ResourceTopic(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
