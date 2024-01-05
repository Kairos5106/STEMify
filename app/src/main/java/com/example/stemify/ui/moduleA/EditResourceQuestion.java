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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import java.util.ArrayList;
import java.util.List;

public class EditResourceQuestion extends AppCompatActivity {
    Button BtnSaveChanges;
    QuestionAnswerAdapter questionAnswerAdapter;
    List<String> listOfItems;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resource_question);
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBEditQuestion);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Edit Question");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);

        // Setup button: save changes to subtopic edits
        BtnSaveChanges = (Button) findViewById(R.id.BtnSaveChanges);
        BtnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditResourceQuestion.this, "Changes applied", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // Setup RecyclerView for answers
        recyclerView = findViewById(R.id.RVAnswer);
        questionAnswerAdapter = new QuestionAnswerAdapter(EditResourceQuestion.this, listOfItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(EditResourceQuestion.this));
        recyclerView.setAdapter(questionAnswerAdapter);
        questionAnswerAdapter.notifyDataSetChanged();
    }

    public void initializeData(){
        listOfItems = new ArrayList<String>();

        String answer1 = "Answer 1";
        String answer2 = "Answer 2";
        String answer3 = "Answer 3";
        String answer4 = "Answer 4";

        listOfItems.add(answer1);
        listOfItems.add(answer2);
        listOfItems.add(answer3);
        listOfItems.add(answer4);
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
            Intent goToAddResourcePage = new Intent(EditResourceQuestion.this, TestActivity.class); // chg ltr
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