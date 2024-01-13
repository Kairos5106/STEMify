package com.example.stemify.ui.moduleA;

import androidx.annotation.NonNull;
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
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stemify.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class SubtopicLibrary extends AppCompatActivity {
    SubtopicAdapter subtopicAdapter;
    RecyclerView recyclerView;
    List<Subtopic> listOfItems;
    String selectedSubjectTitle;
    String selectedGrade;
    String selectedTopic;
    DatabaseReference database;
    TextView TVGradeLevel, TVSubjectWithTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtopic_library);

        // Assign sample data for RecyclerView usage
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBSubtopicLibrary);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Subtopics");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVSubtopicLibrary);
        recyclerView.setLayoutManager(new LinearLayoutManager(SubtopicLibrary.this));
        subtopicAdapter = new SubtopicAdapter(SubtopicLibrary.this, listOfItems);
        subtopicAdapter.setOnItemClickListener(new MaterialAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent goToSectionLibrary = new Intent(SubtopicLibrary.this, SectionLibrary.class);

                // Get selected subject title and grade level
                goToSectionLibrary.putExtra("selectedSubject", selectedSubjectTitle);
                goToSectionLibrary.putExtra("selectedGrade", selectedGrade);

                // Get selected subtopic
                Subtopic selectedSubtopic = listOfItems.get(position);

                // Pack title of selected subtopic
                String subtopicTitle = selectedSubtopic.getTitle();
                goToSectionLibrary.putExtra("subtopicTitle", subtopicTitle);

                // Unpack section objects contained within selected subtopic to be passed onto section library page
                List<Section> listOfSections = selectedSubtopic.getListOfSections();

                // Wrap list of sections to be sent
                Parcelable listOfSectionsParcel = Parcels.wrap(listOfSections);
                goToSectionLibrary.putExtra("listOfSections", listOfSectionsParcel);

                startActivity(goToSectionLibrary);
            }
        });
        recyclerView.setAdapter(subtopicAdapter);
        subtopicAdapter.notifyDataSetChanged();

        // Adapt subtopic information on top of page
        TVGradeLevel = findViewById(R.id.TVGradeLevel);
        TVGradeLevel.setText(selectedGrade);

        TVSubjectWithTopic = findViewById(R.id.TVSubjectWithTopic);
        TVSubjectWithTopic.setText(selectedSubjectTitle + ": " + selectedTopic);
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
        // Get data from previous activity's intent
        Intent prevActivityData = getIntent();
        selectedSubjectTitle = prevActivityData.getStringExtra("selectedSubject");
        selectedGrade = prevActivityData.getStringExtra("selectedGrade");
        selectedTopic = prevActivityData.getStringExtra("selectedTopic");

        // Initializing list of subtopic items
        listOfItems = new ArrayList<Subtopic>();

        // Populate list with subtopics from selected topic
        database = FirebaseDatabase.getInstance().getReference("Subjects")
                .child(selectedSubjectTitle)
                .child(selectedGrade)
                .child(selectedTopic);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){ // Addition
                    if(dataSnapshot.getValue() instanceof String){
                        continue;
                    }

                    // Retrieve subtopic and add it to listOfItems to be displayed by RecyclerView
                    Subtopic subtopic = dataSnapshot.getValue(Subtopic.class);

                    // Retrieve sections of the current subtopic
                    ArrayList<Section> listOfSections = new ArrayList<>();
                    for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) { // One Digit, Two Digit
                        if(childSnapshot.getValue() instanceof String){
                            continue;
                        }

                        // Retrieve each section of the current subtopic
                        Section section = childSnapshot.getValue(Section.class);


                        listOfSections.add(section);
                    }
                    subtopic.setListOfSections(listOfSections);
                    listOfItems.add(subtopic);
                }
                subtopicAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        // Populate list with grade items
//        Subtopic subtopic1 = new Subtopic("Subtopic 1");
//        subtopic1.setImageId(R.drawable.sampleimage);
//
//        // Populating subtopic1 with sections
//        // Section 1 material definition
//        Section section1 = new Section("Section 1");
//        VideoLesson material1a = new VideoLesson("Material 1a: Video Lesson");
//        Practice material1b = new Practice("Material 1b: Practice");
//        Quiz material1c = new Quiz("Material 1c: Quiz", 20);
//
//        // Set video lesson details for Material1a
//        material1a.setType("VideoLesson");
//        material1a.setVideoResourceId(R.raw.samplevideo);
//        material1a.setTranscript("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
//                "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
//                "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit " +
//                "anim id est laborum.\n");
//
//        // Set practice questions for Material 1b
//        material1b.setType("Practice");
//
//        MultipleChoice question1 = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question1.addAnswer("Answer 1a");
//        question1.addAnswer("Answer 1b");
//        question1.addAnswer("Answer 1c");
//        question1.addAnswer("Answer 1d");
//        question1.setDiagramId(R.drawable.sampleimage);
//        question1.setCorrectAnswer("Answer 1a");
//        question1.setDiagramDesc("Sample Diagram Description");
//        material1b.addQuestion(question1);
//
//        FillBlank question5 = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        List<String> answer5 = new ArrayList<>();
//        answer5.add("Lorem");
//        answer5.add("ipsum");
//        question5.setCorrectAnswers(answer5);
//        material1b.addQuestion(question5);
//
//        MultipleChoice question2 = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question2.addAnswer("Answer 2a");
//        question2.addAnswer("Answer 2b");
//        question2.addAnswer("Answer 2c");
//        question2.addAnswer("Answer 2d");
//        question2.setDiagramId(R.drawable.sampleimage);
//        question2.setCorrectAnswer("Answer 2a");
//        question2.setDiagramDesc("Sample Diagram Description");
//        material1b.addQuestion(question2);
//
//        FillBlank question3 = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question3.addAnswer("Lorem");
//        question3.addAnswer("ipsum");
//        material1b.addQuestion(question3);
//
//        FillBlank question4 = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question4.addAnswer("Lorem");
//        question4.addAnswer("ipsum");
//        material1b.addQuestion(question4);
//
//        // Setting questions for Material 1c
//        material1c.setType("Quiz");
//
//        MultipleChoice question1alt = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question1alt.addAnswer("Answer 1a");
//        question1alt.addAnswer("Answer 1b");
//        question1alt.addAnswer("Answer 1c");
//        question1alt.addAnswer("Answer 1d");
//        question1alt.setDiagramId(R.drawable.sampleimage);
//        question1alt.setCorrectAnswer("Answer 1a");
//        question1alt.setDiagramDesc("Sample Diagram Description");
//        material1c.addQuestion(question1alt);
//
//        FillBlank question5alt = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        List<String> answer5alt = new ArrayList<>();
//        answer5alt.add("Lorem");
//        answer5alt.add("ipsum");
//        question5alt.setCorrectAnswers(answer5alt);
//        material1c.addQuestion(question5alt);
//
//        MultipleChoice question2alt = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question2alt.addAnswer("Answer 2a");
//        question2alt.addAnswer("Answer 2b");
//        question2alt.addAnswer("Answer 2c");
//        question2alt.addAnswer("Answer 2d");
//        question2alt.setDiagramId(R.drawable.sampleimage);
//        question2alt.setCorrectAnswer("Answer 2a");
//        question2alt.setDiagramDesc("Sample Diagram Description");
//        material1c.addQuestion(question2alt);
//
//        FillBlank question3alt = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question3alt.addAnswer("Lorem");
//        question3alt.addAnswer("ipsum");
//        material1c.addQuestion(question3alt);
//
//        FillBlank question4alt = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question4alt.addAnswer("Lorem");
//        question4alt.addAnswer("ipsum");
//        material1c.addQuestion(question4alt);
//
//        // Adding section 1 materials to list of items
//        section1.addMaterial(material1a);
//        section1.addMaterial(material1b);
//        section1.addMaterial(material1c);
//        subtopic1.addSection(section1);
//
//        // Section 2 material definitions
//        Section section2 = new Section("Section 2");
//        VideoLesson material2a = new VideoLesson("Material 2a: Video Lesson");
//        Practice material2b = new Practice("Material 2b: Practice");
//        Quiz material2c = new Quiz("Material 2c: Quiz", 20);
//
//        // Set video lesson details for Material1a
//        material2a.setType("VideoLesson");
//        material2a.setVideoResourceId(R.raw.samplevideo);
//        material2a.setTranscript("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
//                "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
//                "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit " +
//                "anim id est laborum.\n");
//
//        // Set practice questions for Material 1b
//        material2b.setType("Practice");
//
//        MultipleChoice question1a = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question1a.addAnswer("Answer 1a");
//        question1a.addAnswer("Answer 1b");
//        question1a.addAnswer("Answer 1c");
//        question1a.addAnswer("Answer 1d");
//        question1a.setDiagramId(R.drawable.sampleimage);
//        question1a.setCorrectAnswer("Answer 1a");
//        question1a.setDiagramDesc("Sample Diagram Description");
//        material2b.addQuestion(question1a);
//
//        FillBlank question5a = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        List<String> answer5a = new ArrayList<>();
//        answer5a.add("Lorem");
//        answer5a.add("ipsum");
//        question5a.setCorrectAnswers(answer5a);
//        material2b.addQuestion(question5a);
//
//        MultipleChoice question2a = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question2a.addAnswer("Answer 2a");
//        question2a.addAnswer("Answer 2b");
//        question2a.addAnswer("Answer 2c");
//        question2a.addAnswer("Answer 2d");
//        question2a.setDiagramId(R.drawable.sampleimage);
//        question2a.setCorrectAnswer("Answer 2a");
//        question2a.setDiagramDesc("Sample Diagram Description");
//        material2b.addQuestion(question2a);
//
//        FillBlank question3a = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question3a.addAnswer("Lorem");
//        question3a.addAnswer("ipsum");
//        material2b.addQuestion(question3a);
//
//        FillBlank question4a = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question4a.addAnswer("Lorem");
//        question4a.addAnswer("ipsum");
//        material2b.addQuestion(question4a);
//
//        // Setting questions for Material 1c
//        material2c.setType("Quiz");
//
//        MultipleChoice question1alternate = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question1alternate.addAnswer("Answer 1a");
//        question1alternate.addAnswer("Answer 1b");
//        question1alternate.addAnswer("Answer 1c");
//        question1alternate.addAnswer("Answer 1d");
//        question1alternate.setDiagramId(R.drawable.sampleimage);
//        question1alternate.setCorrectAnswer("Answer 1a");
//        question1alternate.setDiagramDesc("Sample Diagram Description");
//        material2c.addQuestion(question1alternate);
//
//        FillBlank question5alternate = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        List<String> answer5alternate = new ArrayList<>();
//        answer5alternate.add("Lorem");
//        answer5alternate.add("ipsum");
//        question5alternate.setCorrectAnswers(answer5alternate);
//        material2c.addQuestion(question5alternate);
//
//        MultipleChoice question2alternate = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question2alternate.addAnswer("Answer 2a");
//        question2alternate.addAnswer("Answer 2b");
//        question2alternate.addAnswer("Answer 2c");
//        question2alternate.addAnswer("Answer 2d");
//        question2alternate.setDiagramId(R.drawable.sampleimage);
//        question2alternate.setCorrectAnswer("Answer 2a");
//        question2alternate.setDiagramDesc("Sample Diagram Description");
//        material2c.addQuestion(question2alternate);
//
//        FillBlank question3alternate = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question3alternate.addAnswer("Lorem");
//        question3alternate.addAnswer("ipsum");
//        material2c.addQuestion(question3alternate);
//
//        FillBlank question4alternate = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
//        question4alternate.addAnswer("Lorem");
//        question4alternate.addAnswer("ipsum");
//        material2c.addQuestion(question4alternate);
//
//        // Adding section 1 materials to list of items
//        section2.addMaterial(material2a);
//        section2.addMaterial(material2b);
//        section2.addMaterial(material2c);
//        subtopic1.addSection(section2);
//
//        listOfItems.add(subtopic1);
//
//        // Adding subtopic 2
//        Subtopic subtopic2 = new Subtopic("Subtopic 2");
//        subtopic2.setImageId(R.drawable.sampleimage);
//        Section section2a = new Section("Section 1");
//        Section section2b = new Section("Section 2");
//        Section section2c = new Section("Section 3");
//        subtopic2.addSection(section2a);
//        subtopic2.addSection(section2b);
//        subtopic2.addSection(section2c);
//
//        listOfItems.add(subtopic2);
//
//        // Adding subtopic 3
//        Subtopic subtopic3 = new Subtopic("Subtopic 3");
//        subtopic3.setImageId(R.drawable.sampleimage);
//        Section section3a = new Section("Section 1");
//        Section section3b = new Section("Section 2");
//        Section section3c = new Section("Section 3");
//        subtopic3.addSection(section3a);
//        subtopic3.addSection(section3b);
//        subtopic3.addSection(section3c);
//
//        listOfItems.add(subtopic3);
    }
}