package com.example.stemify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUp1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp1.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp1 newInstance(String param1, String param2) {
        SignUp1 fragment = new SignUp1();
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
        return inflater.inflate(R.layout.fragment_sign_up1, container, false);
    }

    //checker for identity selection
    boolean selectionChecker = false;
    public String chosenIdentity = "";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        //dropdown for identity
        String[] IDENTITY = new String[]{
                "Identity", "Student", "Tutor", "Parent", "Company", "Community", "Psychiatrist"
        };

        Spinner SPIdentity = (Spinner) view.findViewById(R.id.SPIdentity);
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, IDENTITY);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPIdentity.setAdapter(arrAdapter);
        SPIdentity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //check if the selection is "Identity"
                if (parent.getItemAtPosition(position).equals("Identity")){
                    TextView TVSelectIdentity = view.findViewById(R.id.TVSelectIdentity);
                    TVSelectIdentity.setText("Please Select An Identity");
                    TVSelectIdentity.setTextColor(Color.RED);
                }else{
                    chosenIdentity = (String) parent.getItemAtPosition(position);
                    selectionChecker = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TextView TVSelectIdentity = view.findViewById(R.id.TVSelectIdentity);
                TVSelectIdentity.setText("Please Select An Identity");
                TVSelectIdentity.setTextColor(Color.RED);
            }
        });

        //go to next registration page
        Button BtnNextToSU2 = view.findViewById(R.id.BtnNextToSU2);
        BtnNextToSU2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean hasInput = true;

                //check if all inputs are filled
                EditText ETFullname = view.findViewById(R.id.ETFullname);
                EditText ETRegisterEmail = view.findViewById(R.id.ETRegisterEmail);
                EditText ETOrganization = view.findViewById(R.id.ETOrganization);
                if(isEmpty(ETFullname)){
                    ETFullname.setError("Enter your full name");
                    hasInput = false;
                }
                if(isEmail(ETRegisterEmail) == false){
                    ETRegisterEmail.setError("Enter valid email");
                    hasInput = false;
                }
                if(isEmpty(ETOrganization)){
                    if(!chosenIdentity.equals("Parent") || !chosenIdentity.equals("Community")) {
                        ETOrganization.setError("Enter your school/organization");
                        hasInput = false;
                    }
                }

                if(hasInput && selectionChecker){
                    Navigation.findNavController(view).navigate(R.id.first_to_second);
                }
            }
        });

        //return to splash page
        Button BtnCancelRegister = view.findViewById(R.id.BtnCancelRegister);
        BtnCancelRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBack = new Intent(getActivity(), Splash2.class);
                startActivity(returnBack);
                getActivity().finish();
            }
        });
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text){
        CharSequence str = text.getText().toString();
        return (!TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches());
    }
}