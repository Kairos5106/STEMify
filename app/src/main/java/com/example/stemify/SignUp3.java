package com.example.stemify;

import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUp3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp3.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp3 newInstance(String param1, String param2) {
        SignUp3 fragment = new SignUp3();
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
        return inflater.inflate(R.layout.fragment_sign_up3, container, false);
    }

    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        //to check if the user sign in successfully
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent nextScreen = new Intent(getActivity(), Congrats.class);
            startActivity(nextScreen);
            nextScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().finish();
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        mAuth = FirebaseAuth.getInstance();
        Button BtnCreateAcc = view.findViewById(R.id.BtnCreateAcc);
        BtnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean hasInput = true;

                EditText ETRegUsername = view.findViewById(R.id.ETRegUsername);
                if(isEmpty(ETRegUsername)){
                    ETRegUsername.setError("Enter username");
                    hasInput = false;
                }

                if(hasInput) {

                    //save all data from SignUp1 till SignUp3
                    //from Sign Up 1:
                    //ETFullname, ETRegisterEmail, chosenIdentity, ETOrganization
                    //retrieving data using bundle
                    Bundle bundle = getArguments();
                    String fullname = String.valueOf(bundle.getString("fullname"));
                    String email = String.valueOf(bundle.getString("email"));
                    String identity = String.valueOf(bundle.getString("identity"));
                    String organization = String.valueOf(bundle.getString("organization"));

                    //from Sign Up 2:
                    //chosenSecQ, ETRegAns, ETRegPassword, ETConfirmRegPassword
                    String securityques = String.valueOf(bundle.getString("securityques"));
                    String answer = String.valueOf(bundle.getString("answer"));
                    String password = String.valueOf(bundle.getString("password"));

                    //from Sign Up 3:
                    //ETRegUsername, ProfilPic
                    String username = ETRegUsername.getText().toString();
                    //***profile picture to be completed

                    User user = new User();
                    user.setFullname(fullname);
                    user.setIdentity(identity);
                    user.setOrgaization(organization);
                    user.setSecurityques(securityques);
                    user.setAnswer(answer);
                    user.setUsername(username);

                    //saving data in firebase
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String userId = mAuth.getUid();

                                        // Save additional details to the database
                                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
                                        userRef.child("fullname").setValue(fullname);
                                        userRef.child("identity").setValue(identity);
                                        userRef.child("organization").setValue(organization);
                                        userRef.child("securityques").setValue(securityques);
                                        userRef.child("answer").setValue(answer);
                                        userRef.child("username").setValue(username);

                                        //sign in successfully
                                        Intent nextScreen = new Intent(getActivity(), Congrats.class);
                                        startActivity(nextScreen);
                                        nextScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        getActivity().finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getContext(), "Sign in failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

}

class User {
    private String fullname;
    private String identity;
    private String orgaization;
    private String securityques;
    private String answer;
    private String password;
    private String username;

    // Add getters and setters

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getOrgaization() {
        return orgaization;
    }

    public void setOrgaization(String orgaization) {
        this.orgaization = orgaization;
    }

    public String getSecurityques() {
        return securityques;
    }

    public void setSecurityques(String securityques) {
        this.securityques = securityques;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
