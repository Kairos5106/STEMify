package com.example.stemify;

import com.google.firebase.database.ServerValue;

public class Tutoring_CalendarEvent {
    private String dateSelected;
    private String title;
    private String description;
    private String userID;
    private Object timeStamp;
    private String eventKey;

    public Tutoring_CalendarEvent(String dateSelected, String title, String description, String userID) {
        this.dateSelected = dateSelected;
        this.title = title;
        this.description = description;
        this.userID = userID;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public String getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(String dateSelected) {
        this.dateSelected = dateSelected;
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

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
