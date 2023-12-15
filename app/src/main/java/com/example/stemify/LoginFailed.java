package com.example.stemify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginFailed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_failed);

        SharedPreferences pref = this.getSharedPreferences("LoginFail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("ATTEMPT_Time", System.currentTimeMillis());
        editor.commit();

        //return to splash 2 page
        Button BtnBackToSplash2 = findViewById(R.id.BtnBackToSplash2);
        BtnBackToSplash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBack = new Intent(getApplicationContext(), Splash2.class);
                startActivity(returnBack);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                finish();
            }
        });
    }
}