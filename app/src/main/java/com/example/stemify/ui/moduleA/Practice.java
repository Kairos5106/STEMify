package com.example.stemify.ui.moduleA;

import java.util.ArrayList;
import java.util.List;

public class Practice extends Material{
    List<Question> listOfQuestions;

    public Practice(String title) {
        super(title);
        this.listOfQuestions = new ArrayList<>();
    }

    public Practice(String title, List<Question> listOfQuestions) {
        super(title);
        this.listOfQuestions = listOfQuestions;
    }
}
