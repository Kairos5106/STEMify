package com.example.stemify;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Counselor_ViewBooking extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Counselor_ViewData> bookingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counselor_list_view);

        //firebase connection set
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Bookings").child(userId);

        //toolbar

        Toolbar toolbar = findViewById(R.id.TBViewCounselling);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Psychiatrist Booking");

        //set up view
        recyclerView = findViewById(R.id.recyclerViewAppointment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //take data from database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String drName = dataSnapshot.child("booking_dr_name").getValue(String.class);
                    String time = dataSnapshot.child("booking_time").getValue(String.class);
                    String date = dataSnapshot.child("booking_date").getValue(String.class);

                    Counselor_ViewData bookingData = new Counselor_ViewData(drName, time, date);
                    bookingData.setBooking_dr_name(drName);
                    bookingData.setBooking_time(time);
                    bookingData.setBooking_date(date);

                    bookingList.add(bookingData);

                    // check in log
                    Log.d("Firebase", "Doctor Name: " + drName + ", Time: " + time + ", Date: " + date);

                }

                updateRecyclerView(bookingList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Firebase", "Error fetching data: " + error.getMessage());
            }
        });

    }

    private void updateRecyclerView(List<Counselor_ViewData> bookingList) {
        Counselor_ViewAdapter bookingAdapter = new Counselor_ViewAdapter(bookingList, Counselor_ViewBooking.this);
        recyclerView.setAdapter(bookingAdapter);
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
