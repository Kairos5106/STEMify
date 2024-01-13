package com.example.stemify;

public class Leaderboard_ScoreData {
    String username;
    String userPfp;
    long score;

    public Leaderboard_ScoreData() {
    }

    public Leaderboard_ScoreData(String username, String userPfp, long score) {
        this.username = username;
        this.userPfp = userPfp;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPfp() {
        return userPfp;
    }

    public void setUserPfp(String userPfp) {
        this.userPfp = userPfp;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
