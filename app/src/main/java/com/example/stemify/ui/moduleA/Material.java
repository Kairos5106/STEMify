package com.example.stemify.ui.moduleA;

public class Material {
    String title;
    String type; // Can either be "VideoLesson", "Practice" or "Quiz"
    int points, iconId;

    public Material(String title) {
        this.title = title;
        this.type = "";
        this.points = 0;
    }

    public Material(String title, String type, int points) {
        this.title = title;
        this.type = type;
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getMasteryPoints(){
        // Work on the backend logic later
        return "Points";
    }
}
