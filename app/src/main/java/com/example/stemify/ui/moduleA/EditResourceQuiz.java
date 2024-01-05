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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import java.util.ArrayList;
import java.util.List;

public class EditResourceQuiz extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button BtnSaveChanges;
    ResourceQuestionAdapter resourceQuestionAdapter;
    List<Question> listOfItems;
    RecyclerView recyclerView;
    Spinner durationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resource_quiz);
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBEditResourceQuiz);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Edit Quiz");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);

        // Setup button: save changes to subtopic edits
        BtnSaveChanges = (Button) findViewById(R.id.BtnSaveChanges);
        BtnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditResourceQuiz.this, "Changes applied", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVQuestionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(EditResourceQuiz.this));
        resourceQuestionAdapter = new ResourceQuestionAdapter(EditResourceQuiz.this, listOfItems);
        recyclerView.setAdapter(resourceQuestionAdapter);
        resourceQuestionAdapter.notifyDataSetChanged();

        // Setup spinner options
        durationSpinner = (Spinner) findViewById(R.id.SpinQuizDuration);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(EditResourceQuiz.this, R.array.durationQuiz, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(spinnerAdapter);
        durationSpinner.setOnItemSelectedListener(EditResourceQuiz.this);
    }

    public void initializeData(){
        listOfItems = new ArrayList<Question>();

        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();

        listOfItems.add(question1);
        listOfItems.add(question2);
        listOfItems.add(question3);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Duration selected: " + text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Give action to options in app bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            finish();
            return true;
        }
        if (item.getItemId() == R.id.BtnAddResource) {
            Intent goToAddResourcePage = new Intent(EditResourceQuiz.this, TestActivity.class); // chg ltr
            startActivity(goToAddResourcePage);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Setup add resource button in app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_edit_resource, menu); // change later to add collaboration
        return true;
    }
}