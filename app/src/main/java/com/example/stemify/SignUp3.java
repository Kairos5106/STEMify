package com.example.stemify;

import static androidx.browser.customtabs.CustomTabsClient.getPackageName;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

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
    private DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();

    ImageView ProfilePic;
    Uri imageUri;
    boolean imageChecker = false;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        //get profile picture
        ProfilePic = view.findViewById(R.id.ProfilePic);
        ProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check permission
                //if granted, pick a photo and save the url to imageUri
                //ProfilePic.setImageUrl(imageUrl) to set image on the Image View
                //change imageChecker = true;
            }
        });

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
                    //retrieving data
                    String fullname = (String) DataManager.getInstance().getData("fullname");
                    String email = (String) DataManager.getInstance().getData("email");
                    String identity = (String) DataManager.getInstance().getData("identity");
                    String organization = (String) DataManager.getInstance().getData("organization");

                    //from Sign Up 2:
                    //chosenSecQ, ETRegAns, ETRegPassword
                    String securityques = (String) DataManager.getInstance().getData("securityques");
                    String answer = (String) DataManager.getInstance().getData("answer");
                    String password = (String) DataManager.getInstance().getData("password");

                    //from Sign Up 3:
                    //ETRegUsername
                    String username = ETRegUsername.getText().toString();

                    User user = new User();
                    user.setFullname(fullname);
                    user.setIdentity(identity);
                    user.setOrganization(organization);
                    user.setSecurityques(securityques);
                    user.setAnswer(answer);
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPassword(password);

                    //save profile picture url
                    if(imageChecker){
                        user.setPhotoUrl(imageUri.toString());
                    }else{
                        String defaultImageUrl = "android.resource://com.example.stemify/drawable/defaultprofilepicture";
                        user.setPhotoUrl(defaultImageUrl);
                    }

                    //saving data in firebase
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String userId = mAuth.getUid();

                                        // Save additional details to the database
                                        userRef.child("users").child(userId).setValue(user);

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

@IgnoreExtraProperties
class User {

    private String fullname;
    private String identity;
    private String organization;
    private String securityques;
    private String answer;
    private String username;
    private String email;
    private String photoUrl;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    // Add getters and setters
    public String getFullname() { return fullname; }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}