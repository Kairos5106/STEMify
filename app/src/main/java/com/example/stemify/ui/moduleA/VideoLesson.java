package com.example.stemify.ui.moduleA;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.stemify.R;

public class VideoLesson extends Material implements Parcelable {
    String transcript;
    int videoResourceId;

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

    protected VideoLesson(Parcel in) {
        super(in);
        transcript = in.readString();
        videoResourceId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(transcript);
        dest.writeInt(videoResourceId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideoLesson> CREATOR = new Creator<VideoLesson>() {
        @Override
        public VideoLesson createFromParcel(Parcel in) {
            return new VideoLesson(in);
        }

        @Override
        public VideoLesson[] newArray(int size) {
            return new VideoLesson[size];
        }
    };

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
