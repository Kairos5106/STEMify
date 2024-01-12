package com.example.stemify.ui.settings;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link setting_editpassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class setting_editpassword extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public setting_editpassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment setting_editpassword.
     */
    // TODO: Rename and change types and number of parameters
    public static setting_editpassword newInstance(String param1, String param2) {
        setting_editpassword fragment = new setting_editpassword();
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
        return inflater.inflate(R.layout.fragment_setting_editpassword, container, false);
    }

    EditText ETCurrentPassword;
    EditText ETNewPassword;
    Button BtnSavePassword;
    boolean hasInput = true;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ETCurrentPassword = view.findViewById(R.id.ETCurrentPassword);
        ETNewPassword = view.findViewById(R.id.ETNewPassword);

        //save new password after button is clicked
        BtnSavePassword = view.findViewById(R.id.BtnSavePassword);
        BtnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(ETCurrentPassword)){
                    Toast.makeText(getActivity(), "Enter Your Password", Toast.LENGTH_SHORT).show();
                    hasInput = false;
                }
                if(isEmpty(ETNewPassword)){
                    Toast.makeText(getActivity(), "Enter Your New Password", Toast.LENGTH_SHORT).show();
                    hasInput = false;
                }
                if(hasInput){
                    //override the old password with the new password in the database
                    String newPassword = ETNewPassword.getText().toString();
                    if(newPassword.length()>=6){
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (currentUser != null) {

                            // Read from the database
                            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
                            userRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        User user = dataSnapshot.getValue(User.class);
                                        String currentPass = user.getPassword();
                                        if(currentPass.equals(ETCurrentPassword.getText().toString())){
                                            currentUser.updatePassword(newPassword)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                // Password updated successfully
                                                                Map<String, Object> updates = new HashMap<>();
                                                                updates.put("password", newPassword);
                                                                userRef.updateChildren(updates);
                                                                Toast.makeText(getActivity(), "Password Updated", Toast.LENGTH_SHORT).show();
                                                            } else {
                                                                // Fail to update password
                                                                Toast.makeText(getActivity(), "Failed to update password", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        }else{
                                            Toast.makeText(getActivity(), "Password Mismatch", Toast.LENGTH_SHORT).show();
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
                    }else{
                        TextView TVWeakNewEditPass = view.findViewById(R.id.TVWeakNewEditPass);
                        TVWeakNewEditPass.setTextColor(Color.RED);
                    }
                }
            }
        });
    }
    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}