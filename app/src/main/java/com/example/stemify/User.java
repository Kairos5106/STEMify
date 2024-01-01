package com.example.stemify;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    //a public class that hold user's attributes to be stored into Firebase Realtime Database
    private String fullname;
    private String identity;
    private String organization;
    private String securityques;
    private String answer;
    private String username;
    private String email;
    private String photoUrl;
    private boolean checkAnonymous;
    private String displayName;
    private String password;
    private long attemptTime;
    private int loginTrial;

    public long getAttemptTime() { return attemptTime; }

    public void setAttemptTime(long attemptTime) { this.attemptTime = attemptTime; }

    public int getLoginTrial() { return loginTrial; }

    public void setLoginTrial(int loginTrial) { this.loginTrial = loginTrial; }


    public boolean isCheckAnonymous() {
        return checkAnonymous;
    }

    public void setCheckAnonymous(boolean checkAnonymous) {
        this.checkAnonymous = checkAnonymous;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getSecurityques() {
        return securityques;
    }

    public void setSecurityques(String securityques) {
        this.securityques = securityques;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
