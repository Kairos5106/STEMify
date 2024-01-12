package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

public class Tutoring_Calendar extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    private CalendarView CVCalendar;
    private EditText ETCalendarEventTitle;
    private EditText ETCalendarEventDescription;
    private Button BtnSaveEvent;
    private String dateSelected;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoring_calendar);

        // Initiate Firebase components
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBMyCalendar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        setTitle("My Calendar");

        // Binding
        CVCalendar = findViewById(R.id.CVCalendar);
        ETCalendarEventTitle = findViewById(R.id.ETCalendarEventTitle);
        ETCalendarEventDescription = findViewById(R.id.ETCalendarEventDescription);
        BtnSaveEvent = findViewById(R.id.BtnSaveEvent);

        // Initialise databaseReference
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Calendar");

        // CVCalendar Listener
        CVCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateSelected = Integer.toString(year) + Integer.toString(month + 1) + Integer.toString(dayOfMonth);
                calendarClicked(); // to retrieve existing event from database
            }
        });

        // BtnSaveEvent onClickListener
        BtnSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Test all input fields (Title, description), if all fields are filled:
                if (!ETCalendarEventTitle.getText().toString().isEmpty() &&
                        !ETCalendarEventDescription.getText().toString().isEmpty()) {

                    // Create event object
                    Tutoring_CalendarEvent event = new Tutoring_CalendarEvent(dateSelected, ETCalendarEventTitle.getText().toString(),
                            ETCalendarEventDescription.getText().toString(),
                            currentUser.getUid());

                    // Add event object to Firebase database
                    addEvent(event);

                } else {
                    String messageN = "Please fill in all fields before saving event.";
                    Toast.makeText(Tutoring_Calendar.this, messageN, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void addEvent(Tutoring_CalendarEvent event) {

        // Set the event data to the Firebase reference under "Calendar"
        databaseReference.child(currentUser.getUid()).child(dateSelected).setValue(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Event added successfully
                        String message = "Event saved successfully!";
                        Toast.makeText(Tutoring_Calendar.this, message, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to add event
                        String message = "Failed to save event. Please try again.";
                        Toast.makeText(Tutoring_Calendar.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Retrieve existing event from database for a particular date
    private void calendarClicked() {

        databaseReference.child(currentUser.getUid()).child(dateSelected).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {

                    // Display existing event details in UI
                    Tutoring_CalendarEvent event = snapshot.getValue(Tutoring_CalendarEvent.class);
                    if (event != null) {
                        ETCalendarEventTitle.setText(event.getTitle());
                        ETCalendarEventDescription.setText(event.getDescription());

                    }

                } else {
                    // No existing events for the selected date, clear UI fields or set default values
                    ETCalendarEventTitle.setText("");
                    ETCalendarEventDescription.setText("");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}