package com.example.stemify;

import com.google.firebase.database.ServerValue;

import java.util.List;

public class HomeworkHelp_Post {

    private String postKey;
    private String title;
    private String description;
    private String userID;
    private String userPfp;
    private List<String> tags;

    public HomeworkHelp_Post(String title, String description, String userID, String userPfp) {
        this.title = title;
        this.description = description;
        this.userID = userID;
        this.userPfp = userPfp;
    }

    public HomeworkHelp_Post() {
    }

    public String getPostKey() {
        return postKey;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserPfp() {
        return userPfp;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserPfp(String userPfp) {
        this.userPfp = userPfp;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
