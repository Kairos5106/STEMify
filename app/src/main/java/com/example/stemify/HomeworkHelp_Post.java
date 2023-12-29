package com.example.stemify;

import android.net.Uri;

import com.google.firebase.database.ServerValue;

import java.util.List;

public class HomeworkHelp_Post {

    private String postKey;
    private String title;
    private String description;
    private String userID;
    private String userPfp;
    private List<String> tags;
    private Object timeStamp;
    private String username;

    public HomeworkHelp_Post(String title, String description, String userID, String userPfp, String username) {
        this.title = title;
        this.description = description;
        this.userID = userID;
        this.userPfp = userPfp;
        this.username = username;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    // If got tags
    public HomeworkHelp_Post(String title, String description, String userID, String userPfp, List<String> tags) {
        this.title = title;
        this.description = description;
        this.userID = userID;
        this.userPfp = userPfp;
        this.tags = tags;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    // Default constructor
    public HomeworkHelp_Post() {
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPfp() {
        return userPfp;
    }

    public void setUserPfp(String userPfp) {
        this.userPfp = userPfp;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
