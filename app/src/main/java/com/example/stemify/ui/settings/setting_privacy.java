package com.example.stemify.ui.settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
 * Use the {@link setting_privacy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class setting_privacy extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public setting_privacy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment setting_privacy.
     */
    // TODO: Rename and change types and number of parameters
    public static setting_privacy newInstance(String param1, String param2) {
        setting_privacy fragment = new setting_privacy();
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
        return inflater.inflate(R.layout.fragment_setting_privacy, container, false);
    }

    TextView TVUsernameChange;
    CheckBox CBAnonymous;
    boolean anonymousStatus;
    RelativeLayout ClickableEditPassword;
    RelativeLayout ClickableEditSecurityQ;

    String name = "";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        TVUsernameChange = view.findViewById(R.id.TVUsernameChange);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        // Read from the database
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());

        if (currentUser != null) {

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);
                        name = user.getUsername();
                        anonymousStatus = user.isCheckAnonymous();
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Handle errors
                    Toast.makeText(getActivity(), "Error in Retrieving Account Data", Toast.LENGTH_SHORT).show();
                }
            });

        }

        int length = name.length();

        if (length >= 3) {
            String replacement = "";
            for (int i = 1; i < name.length()-1; i++) {
                replacement+="x";
            }
            name = name.substring(0, 1) + replacement + name.substring(name.length()-1);
        }else{
            String replacement = "";
            for (int i = 0; i < name.length(); i++) {
                replacement+="x";
            }
            name = replacement;
        }
        TVUsernameChange.setText(new StringBuilder().append("Your username will appear as ").append(name).toString());

        //change displayed username if checked
        CBAnonymous = view.findViewById(R.id.CBAnonymous);
        CBAnonymous.setChecked(anonymousStatus);
        if(CBAnonymous.isChecked()){
            //change displayName data
            Map<String, Object> updates = new HashMap<>();
            updates.put("displayName", name);

            // Perform the update
            userRef.updateChildren(updates)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Display Name Updated", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Error in Updating Display Name", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        //navigate to other fragments
        ClickableEditPassword = view.findViewById(R.id.ClickableEditPassword);
        ClickableEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity(), setting_editpassword.class);
                startActivity(nextScreen);
            }
        });

        ClickableEditSecurityQ = view.findViewById(R.id.ClickableEditSecurityQ);
        ClickableEditSecurityQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity(), setting_editsecurityq.class);
                startActivity(nextScreen);
            }
        });
    }
}