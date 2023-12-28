package com.example.stemify;

import android.net.Uri;

import com.google.firebase.database.ServerValue;

import java.util.List;

public class HomeworkHelp_Post {

    private String postKey;
    private String title;
    private String description;
    private String userID;
    private Uri userPfp;
    private List<String> tags;
    private Object timeStamp;

    public HomeworkHelp_Post(String title, String description, String userID, Uri userPfp) {
        this.title = title;
        this.description = description;
        this.userID = userID;
        this.userPfp = userPfp;
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

    public Uri getUserPfp() {
        return userPfp;
    }

    public void setUserPfp(Uri userPfp) {
        this.userPfp = userPfp;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
