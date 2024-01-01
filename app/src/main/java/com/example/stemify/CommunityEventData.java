package com.example.stemify;

public class CommunityEventData {

    private String title, date, time, place, details;
    private int image;

    public CommunityEventData(String title, String date, String time, String place, String details, int image) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.place = place;
        this.details = details;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
