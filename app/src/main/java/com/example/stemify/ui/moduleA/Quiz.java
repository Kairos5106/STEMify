package com.example.stemify.ui.moduleA;

import org.parceler.Parcel;

@Parcel
public class Quiz extends Practice{
    int duration; // in minutes
    int completedQuestions;

    public Quiz(){
        duration = 0;
        completedQuestions = 0;
    }
    public Quiz(String title, int duration) {
        super(title);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCompletedQuestions() {
        return completedQuestions;
    }

    public void setCompletedQuestions(int completedQuestions) {
        this.completedQuestions = completedQuestions;
    }
}
