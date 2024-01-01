package com.example.stemify.ui.moduleA;

public class VideoLesson extends Material{
    String transcript;
    int videoId;

    public VideoLesson(String title) {
        super(title);
        this.transcript = "No transcript provided";
        this.videoId = 0;
    }

    public VideoLesson(String title, String type, int points, String transcript, int videoId) {
        super(title, type, points);
        this.transcript = transcript;
        this.videoId = videoId;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
