package com.example.stemify.ui.moduleA;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    String subjectTitle;

    public Subject(){}

    public Subject(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }
}
