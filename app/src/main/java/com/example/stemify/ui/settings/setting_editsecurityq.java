package com.example.stemify.ui.settings;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
 * Use the {@link setting_editsecurityq#newInstance} factory method to
 * create an instance of this fragment.
 */
public class setting_editsecurityq extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public setting_editsecurityq() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment setting_editsecurityq.
     */
    // TODO: Rename and change types and number of parameters
    public static setting_editsecurityq newInstance(String param1, String param2) {
        setting_editsecurityq fragment = new setting_editsecurityq();
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
        return inflater.inflate(R.layout.fragment_setting_editsecurityq, container, false);
    }

    TextView TVForCurrent;
    String chosenSecQ;
    TextView TVForNew;
    String chosenNewSecQ;
    Button BtnSaveSecQ;
    EditText ETCurrentAns;
    EditText ETNewAns;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //dropdown for security questions
        TVForCurrent = view.findViewById(R.id.TVForCurrent);
        String[] SecurityQ = new String[]{
                "Security Question", "What is the name of your favourite teacher?", "What is your favourite food?", "What is your favourite colour?", "What is your favourite city?"
        };

        Spinner CurrentSecurityQues = (Spinner) view.findViewById(R.id.CurrentSecurityQues);
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, SecurityQ);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CurrentSecurityQues.setAdapter(arrAdapter);
        CurrentSecurityQues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Security Question")){
                    TVForCurrent.setText("Please Select A Security Question");
                    TVForCurrent.setTextColor(Color.RED);
                }else{
                    chosenSecQ = (String) parent.getItemAtPosition(position);
                    TVForCurrent.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing to display
            }
        });

        TVForNew = view.findViewById(R.id.TVForNew);
        Spinner NewSecurityQues = (Spinner) view.findViewById(R.id.CurrentSecurityQues);
        NewSecurityQues.setAdapter(arrAdapter);
        NewSecurityQues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Security Question")){
                    TVForNew.setText("Please Select A Security Question");
                    TVForNew.setTextColor(Color.RED);
                }else{
                    chosenNewSecQ = (String) parent.getItemAtPosition(position);
                    TVForNew.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing to display
            }
        });

        //save data after button is clicked
        ETCurrentAns = view.findViewById(R.id.ETCurrentAns);
        ETNewAns = view.findViewById(R.id.ETNewAns);
        BtnSaveSecQ = view.findViewById(R.id.BtnSaveSecQ);
        BtnSaveSecQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentAns = ETCurrentAns.getText().toString();
                String newAns = ETNewAns.getText().toString();

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                // Read from the database
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());

                if(currentUser != null){
                    userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                User user = dataSnapshot.getValue(User.class);
                                String DataSecQ = user.getSecurityques();
                                String DataAns = user.getAnswer();
                                if(DataSecQ.equals(chosenSecQ)){
                                    if(DataAns.equals(currentAns)){
                                        Map<String, Object> updates = new HashMap<>();
                                        updates.put("securityques", chosenNewSecQ);
                                        updates.put("answer", newAns);

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
                                    }else{
                                        Toast.makeText(getActivity(), "Current Answer Mismatch", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getActivity(), "Wrong Security Question", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Handle errors
                            Toast.makeText(getActivity(), "Error in Retrieving Account Data", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}