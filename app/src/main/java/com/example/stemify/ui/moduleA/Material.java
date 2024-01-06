package com.example.stemify.ui.moduleA;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Material implements Parcelable {
    String title;
    String type; // Can either be "VideoLesson", "Practice" or "Quiz"
    int points, iconId;

    public Material() {
        this.title = "";
        this.type = "";
        this.points = 0;
    }

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

    protected Material(Parcel in) {
        title = in.readString();
        type = in.readString();
        points = in.readInt();
        iconId = in.readInt();
    }

    public static final Creator<Material> CREATOR = new Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel in) {
            return new Material(in);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(type);
        dest.writeInt(points);
        dest.writeInt(iconId);
    }
}
