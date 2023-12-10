package com.example.stemify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        //return to Splash 2 if cancel button is clicked
        Button BtnCancelChangePass = (Button) findViewById(R.id.BtnCancelChangePass);
        BtnCancelChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBack = new Intent(getApplicationContext(), Splash2.class);
                startActivity(returnBack);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                finish();
            }
        });


        Button BtnSubmitNewPass = (Button) findViewById(R.id.BtnSubmitNewPass);
        BtnSubmitNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasInput = true;

                //check if all inputs (email + password) are filled
                EditText ETNewPassword = findViewById(R.id.ETNewPassword);
                EditText ETConfirmNewPassword = findViewById(R.id.ETConfirmNewPassword);
                if(isEmpty(ETNewPassword)){
                    ETNewPassword.setError("Enter a new password");
                    hasInput = false;
                }
                if(isEmpty(ETConfirmNewPassword)){
                    ETConfirmNewPassword.setError("Confirm your password");
                    hasInput = false;
                }
                if(hasInput){
                    //override the old password with the new password in the database
                    Intent nextScreen = new Intent(getApplicationContext(), PasswordChangeDone.class);
                    startActivity(nextScreen);
                    finish();
                }
            }
        });
    }
    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}