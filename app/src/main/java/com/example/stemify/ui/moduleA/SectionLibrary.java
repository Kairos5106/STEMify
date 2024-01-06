package com.example.stemify.ui.moduleA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.ProxyFileDescriptorCallback;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionLibrary extends AppCompatActivity {
    SectionAdapter sectionAdapter;
    RecyclerView recyclerView;
    List<Section> listOfItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_library);

        // Assign sample data for RecyclerView usage
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBSectionLibrary);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Sections");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVSectionLibrary);
        recyclerView.setLayoutManager(new LinearLayoutManager(SectionLibrary.this));
        sectionAdapter = new SectionAdapter(SectionLibrary.this, listOfItems);
        recyclerView.setAdapter(sectionAdapter);
        sectionAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeData(){
        // Initializing list of topic items
        listOfItems = new ArrayList<Section>();

        // Populate list with section items
        // Section 1
        Section section1 = new Section("Section 1");
        VideoLesson material1a = new VideoLesson("Material 1a: Video Lesson");
        Practice material1b = new Practice("Material 1b: Practice");
        Quiz material1c = new Quiz("Material 1c: Quiz", 20);

        // Set video lesson details for Material1a
        material1a.setType("VideoLesson");
        material1a.setVideoResourceId(R.raw.samplevideo);
        material1a.setTranscript("Testing with the transcript");
//        material1a.setTranscript("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
//                "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
//                "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit " +
//                "anim id est laborum.\n");

        // Set practice questions for Material 1b
        material1b.setType("Practice");

        MultipleChoice question1 = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question1.addAnswer("Answer 1a");
        question1.addAnswer("Answer 1b");
        question1.addAnswer("Answer 1c");
        question1.addAnswer("Answer 1d");
        question1.setDiagramId(R.drawable.sampleimage);
        question1.setCorrectAnswer("Answer 1a");
        question1.setDiagramDesc("Sample Diagram Description");
        material1b.addQuestion(question1);

        FillBlank question5 = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        List<String> answer5 = new ArrayList<>();
        answer5.add("Lorem");
        answer5.add("ipsum");
        question5.setCorrectAnswers(answer5);
        material1b.addQuestion(question5);

        MultipleChoice question2 = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question2.addAnswer("Answer 2a");
        question2.addAnswer("Answer 2b");
        question2.addAnswer("Answer 2c");
        question2.addAnswer("Answer 2d");
        question2.setDiagramId(R.drawable.sampleimage);
        question2.setCorrectAnswer("Answer 2a");
        question2.setDiagramDesc("Sample Diagram Description");
        material1b.addQuestion(question2);

        FillBlank question3 = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question3.addAnswer("Lorem");
        question3.addAnswer("ipsum");
        material1b.addQuestion(question3);

        FillBlank question4 = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question4.addAnswer("Lorem");
        question4.addAnswer("ipsum");
        material1b.addQuestion(question4);

        // Setting questions for Material 1c
        material1c.setType("Quiz");

        MultipleChoice question1alt = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question1alt.addAnswer("Answer 1a");
        question1alt.addAnswer("Answer 1b");
        question1alt.addAnswer("Answer 1c");
        question1alt.addAnswer("Answer 1d");
        question1alt.setDiagramId(R.drawable.sampleimage);
        question1alt.setCorrectAnswer("Answer 1a");
        question1alt.setDiagramDesc("Sample Diagram Description");
        material1c.addQuestion(question1alt);

        FillBlank question5alt = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        List<String> answer5alt = new ArrayList<>();
        answer5alt.add("Lorem");
        answer5alt.add("ipsum");
        question5alt.setCorrectAnswers(answer5alt);
        material1c.addQuestion(question5alt);

        MultipleChoice question2alt = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question2alt.addAnswer("Answer 2a");
        question2alt.addAnswer("Answer 2b");
        question2alt.addAnswer("Answer 2c");
        question2alt.addAnswer("Answer 2d");
        question2alt.setDiagramId(R.drawable.sampleimage);
        question2alt.setCorrectAnswer("Answer 2a");
        question2alt.setDiagramDesc("Sample Diagram Description");
        material1c.addQuestion(question2alt);

        FillBlank question3alt = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question3alt.addAnswer("Lorem");
        question3alt.addAnswer("ipsum");
        material1c.addQuestion(question3alt);

        FillBlank question4alt = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question4alt.addAnswer("Lorem");
        question4alt.addAnswer("ipsum");
        material1c.addQuestion(question4alt);

        // Adding section 1 materials to list of items
        section1.addMaterial(material1a);
        section1.addMaterial(material1b);
        section1.addMaterial(material1c);
        listOfItems.add(section1);
    }
}