package com.example.stemify.ui.settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.Splash2;
import com.example.stemify.User;
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
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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

    ImageView MainProfilePic;
    TextView TVDisplayName;
    TextView TVDisplayIdentity;
    RelativeLayout ClickableEditProfile;
    RelativeLayout ClickableEditEmail;
    RelativeLayout ClickablePrivacy;
    RelativeLayout ClickableLinkedDevices;
    Button BtnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //initial setup to display user profile
        MainProfilePic = view.findViewById(R.id.MainProfilePic);
        TVDisplayName = view.findViewById(R.id.TVDisplayName);
        TVDisplayIdentity = view.findViewById(R.id.TVDisplayIdentity);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {

            // Read from the database
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());

            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);

                        //check if email has changed recently
                        if(!user.getEmail().equals(currentUser.getEmail())){
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("email", currentUser.getEmail());
                            userRef.updateChildren(updates);
                        }

                        //set Profile Icon
                        Picasso.get().load(user.getPhotoUrl()).into(MainProfilePic);

                        //set Display Name
                        TVDisplayName.setText(user.getDisplayName());

                        //set Display Identity
                        String identity = user.getIdentity();
                        if(identity.equals("Parent")){
                            TVDisplayIdentity.setText("Parent");
                        }else{
                            String displayIdentity = user.getIdentity() + " (" + user.getOrganization() + ")";
                            TVDisplayIdentity.setText(displayIdentity);
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

        //navigate to different setting fragments
        ClickableEditProfile = view.findViewById(R.id.ClickableEditProfile);
        ClickableEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.setting_editprofile);
            }
        });

        ClickableEditEmail = view.findViewById(R.id.ClickableEditEmail);
        ClickableEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.setting_editemail);
            }
        });

        ClickablePrivacy = view.findViewById(R.id.ClickablePrivacy);
        ClickablePrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.setting_privacy);
            }
        });

        ClickableLinkedDevices = view.findViewById(R.id.ClickableLinkedDevices);
        ClickableLinkedDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.setting_otherdevices);
            }
        });

        //button to log out
        BtnLogout = view.findViewById(R.id.BtnLogout);
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent nextScreen = new Intent(getActivity(), Splash2.class);
                startActivity(nextScreen);
                getActivity().finish();
            }
        });
    }
}