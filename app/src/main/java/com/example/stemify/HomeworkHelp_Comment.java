package com.example.stemify;

import com.google.firebase.database.ServerValue;

public class HomeworkHelp_Comment {

    private String content;
    private String commenterID;
    private String commenterUsername;
    private String commenterPfp;
    private Object commenterTimestamp;

    public HomeworkHelp_Comment() {
    }

    public HomeworkHelp_Comment(String content, String commenterID, String commenterUsername, String commenterPfp) {
        this.content = content;
        this.commenterID = commenterID;
        this.commenterUsername = commenterUsername;
        this.commenterPfp = commenterPfp;
        this.commenterTimestamp = ServerValue.TIMESTAMP;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommenterID() {
        return commenterID;
    }

    public void setCommenterID(String commenterID) {
        this.commenterID = commenterID;
    }

    public String getCommenterUsername() {
        return commenterUsername;
    }

    public void setCommenterUsername(String commenterUsername) {
        this.commenterUsername = commenterUsername;
    }

    public String getCommenterPfp() {
        return commenterPfp;
    }

    public void setCommenterPfp(String commenterPfp) {
        this.commenterPfp = commenterPfp;
    }

    public Object getCommenterTimestamp() {
        return commenterTimestamp;
    }

    public void setCommenterTimestamp(Object commenterTimestamp) {
        this.commenterTimestamp = commenterTimestamp;
    }
}
