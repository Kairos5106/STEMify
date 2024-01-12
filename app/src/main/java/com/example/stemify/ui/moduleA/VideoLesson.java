package com.example.stemify.ui.moduleA;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.stemify.R;

@org.parceler.Parcel
public class VideoLesson extends Material {
    String transcript;
    int videoResourceId;

    public VideoLesson(){}

    public VideoLesson(String title) {
        super(title);
        this.transcript = "No transcript provided";
        this.videoResourceId = 0;
    }

    public VideoLesson(String title, String type, int points, String transcript, int videoResourceId) {
        super(title, type, points);
        this.transcript = transcript;
        this.videoResourceId = videoResourceId;
    }

    public int describeContents() {
        return 0;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public int getVideoResourceId() {
        return videoResourceId;
    }

    public void setVideoResourceId(int videoResourceId) {
        this.videoResourceId = videoResourceId;
    }
}
