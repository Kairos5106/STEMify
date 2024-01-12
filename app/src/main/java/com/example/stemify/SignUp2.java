package com.example.stemify;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
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
 * Use the {@link SignUp2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUp2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp2.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp2 newInstance(String param1, String param2) {
        SignUp2 fragment = new SignUp2();
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
        return inflater.inflate(R.layout.fragment_sign_up2, container, false);
    }

    boolean selectionChecker = false;

    public String chosenSecQ = "";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        TextView TVSelectQ = view.findViewById(R.id.TVSelectQ);

        //dropdown (spinner) for security questions
        String[] SecurityQ = new String[]{
                "Security Question", "What is the name of your favourite teacher?", "What is your favourite food?", "What is your favourite colour?", "What is your favourite city?"
        };
        Spinner SPSecurityQues = (Spinner) view.findViewById(R.id.SPSecurityQues);
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, SecurityQ);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPSecurityQues.setAdapter(arrAdapter);
        SPSecurityQues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //check if the selection is "Security Question"
                if (parent.getItemAtPosition(position).equals("Security Question")){
                    //inform the user to make a true selection
                    TVSelectQ.setText("Please Select A Security Question");
                    TVSelectQ.setTextColor(Color.RED);
                }else{
                    chosenSecQ = (String) parent.getItemAtPosition(position);
                    selectionChecker = true;
                    TVSelectQ.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing to display
            }
        });

        Button BtnNextToSU3 = view.findViewById(R.id.BtnNextToSU3);
        BtnNextToSU3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean hasInput = true;

                //check if all inputs are filled
                EditText ETRegAns = view.findViewById(R.id.ETRegAns);
                EditText ETRegPassword = view.findViewById(R.id.ETRegPassword);
                EditText ETConfirmRegPassword = view.findViewById(R.id.ETConfirmRegPassword);
                if(isEmpty(ETRegAns)){
                    ETRegAns.setError("Enter an answer");
                    hasInput = false;
                }
                if(isEmpty(ETRegPassword)){
                    ETRegPassword.setError("Enter a password");
                    hasInput = false;
                }
                if(isEmpty(ETConfirmRegPassword)){
                    ETConfirmRegPassword.setError("Confirm your password");
                    hasInput = false;
                }

                if(hasInput && selectionChecker){

                    //check if the password has more than 6 characters
                    if(ETRegPassword.getText().toString().length()>=6){
                        //check if password == confirm password
                        if(ETRegPassword.getText().toString().equals(ETConfirmRegPassword.getText().toString())) {
                            String answer = ETRegAns.getText().toString();
                            String password = ETRegPassword.getText().toString();

                            //using Data Manager class to hold data
                            DataManager.getInstance().putData("answer", answer);
                            DataManager.getInstance().putData("securityques", chosenSecQ);
                            DataManager.getInstance().putData("password", password);

                            //transit to SignUp3
                            Navigation.findNavController(view).navigate(R.id.NavSignUp3);

                        }else{
                            TextView TVWrongPass = view.findViewById(R.id.TVWrongPass);
                            TVWrongPass.setText("Password Mismatch");
                            TVWrongPass.setTextColor(Color.RED);
                        }
                    }else{
                        TextView TVWeakPass = view.findViewById(R.id.TVWeakPass);
                        TVWeakPass.setTextColor(Color.RED);
                    }
                }
            }
        });

        Button BtnBackToSU1 = view.findViewById(R.id.BtnBackToSU1);
        BtnBackToSU1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.NavSignUp1);
            }
        });
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}