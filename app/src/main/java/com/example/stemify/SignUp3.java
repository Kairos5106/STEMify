package com.example.stemify;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
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
                    //from Sign Up 2:
                    //chosenSecQ, ETRegAns, ETRegPassword, ETConfirmRegPassword
                    //from Sign Up 3:
                    //ETRegUsername, ProfilPic
                    Intent nextScreen = new Intent(getActivity(), Congrats.class);
                    startActivity(nextScreen);
                    nextScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().finish();
                }
            }
        });
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

}