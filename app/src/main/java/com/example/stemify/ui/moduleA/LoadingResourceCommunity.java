package com.example.stemify.ui.moduleA;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoadingResourceCommunity extends Fragment {
    Handler handler;
    String userIdentity = "";
    public LoadingResourceCommunity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading_resource_community, container, false);
    }


    public void getUserIdentity(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    userIdentity = user.getIdentity();
                    Toast.makeText(getActivity(), "in userIdentity: " + userIdentity, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    // Adding appropriate resource community layout for users of different identities
    public Fragment getAppropriateLayout(){
        if(userIdentity.equalsIgnoreCase("Tutor")){ // if user is an Educator, give student fragment
            Toast.makeText(getActivity(), "Educator View", Toast.LENGTH_SHORT).show();
            return new ResourceCommunityEducator();
        }
        else { // if user is anything but an Educator, give Student fragment
            Toast.makeText(getActivity(), "Student View", Toast.LENGTH_SHORT).show();
            return new ResourceCommunity();
        }
    }
}