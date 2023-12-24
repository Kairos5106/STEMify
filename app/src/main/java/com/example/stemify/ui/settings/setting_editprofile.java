package com.example.stemify.ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link setting_editprofile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class setting_editprofile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public setting_editprofile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment setting_editprofile.
     */
    // TODO: Rename and change types and number of parameters
    public static setting_editprofile newInstance(String param1, String param2) {
        setting_editprofile fragment = new setting_editprofile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_editprofile, container, false);
    }

    ImageView DisplayProfilePic;
    EditText ETEditFullname;
    EditText ETEditUsername;
    EditText ETEditEmail;
    EditText ETEditOrganization;
    Button BtnSaveProfile;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //setup profile
        DisplayProfilePic = view.findViewById(R.id.DisplayProfilePic);
        ETEditFullname = view.findViewById(R.id.ETEditFullname);
        ETEditUsername = view.findViewById(R.id.ETEditUsername);
        ETEditEmail = view.findViewById(R.id.ETEditEmail);
        ETEditOrganization = view.findViewById(R.id.ETEditOrganization);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        // Read from the database
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());

        if (currentUser != null) {
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);
                        Picasso.get().load(user.getPhotoUrl()).into(DisplayProfilePic);
                        ETEditFullname.setText(user.getFullname());
                        ETEditUsername.setText(user.getUsername());
                        ETEditEmail.setText(user.getEmail());
                        ETEditOrganization.setText(user.getOrganization());
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Handle errors
                    Toast.makeText(getActivity(), "Error in Retrieving Account Data", Toast.LENGTH_SHORT).show();
                }
            });

        }

        //save changes after button is clicked
        BtnSaveProfile = view.findViewById(R.id.BtnSaveProfile);
        BtnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newFullname = ETEditFullname.getText().toString();
                String newUsername = ETEditUsername.getText().toString();
                String newEmail = ETEditEmail.getText().toString();
                String newOrganization = ETEditOrganization.getText().toString();

                //update email in User Authentication Database
                currentUser.updateEmail(newEmail);

                //update user profile in Firebase Realtime Database
                Map<String, Object> updates = new HashMap<>();
                updates.put("fullname", newFullname);
                updates.put("username", newUsername);
                updates.put("email", newEmail);
                updates.put("organization", newOrganization);

                // Perform the update
                userRef.updateChildren(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "User Profile Updated", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Error in Updating User Profile", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}