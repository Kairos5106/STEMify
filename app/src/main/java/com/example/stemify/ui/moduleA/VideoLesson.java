package com.example.stemify.ui.moduleA;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.stemify.R;

@org.parceler.Parcel
public class VideoLesson extends Material {
    String transcript, videoName;

    public VideoLesson(){
        super();
        this.transcript = "No transcript provided";
        this.videoName = "samplevideo";
    }

    public VideoLesson(String title) {
        super(title);
        this.transcript = "No transcript provided";
        this.videoName = "samplevideo";
    }

    public VideoLesson(String title, String type, int points, String transcript, String videoName) {
        super(title, type, points);
        this.transcript = transcript;
        this.videoName = videoName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    @Override
    public String toString() {
        return "VideoLesson{" +
                "transcript='" + transcript + '\'' +
                ", videoName='" + videoName + '\'' +
                '}';
    }
}