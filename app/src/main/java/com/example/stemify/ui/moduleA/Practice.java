package com.example.stemify.ui.moduleA;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

public class Practice extends Material {
    List<Question> listOfQuestions;

    public Practice(){
        super();
        listOfQuestions = new ArrayList<>();
    }

    public Practice(String title) {
        super(title);
        this.listOfQuestions = new ArrayList<>();
    }

    public Practice(String title, List<Question> listOfQuestions) {
        super(title);
        this.listOfQuestions = listOfQuestions;
    }

    public List<Question> getListOfQuestions() {
        return listOfQuestions;
    }

    public void setListOfQuestions(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    public void addQuestion(Question question){
        listOfQuestions.add(question);
    }
}
