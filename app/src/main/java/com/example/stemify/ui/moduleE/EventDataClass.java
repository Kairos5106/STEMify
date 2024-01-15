package com.example.stemify.ui.moduleE;

public class EventDataClass {

    private String eventName;
    private String eventDesc;
    private String eventLocation;
    private String eventDate;
    private String eventTime;
    private String eventImage;
    private String key;

    public EventDataClass(){}
    public EventDataClass(String eventName, String eventDesc, String eventLocation, String eventDate, String eventTime, String eventImage) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventImage() {
        return eventImage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
