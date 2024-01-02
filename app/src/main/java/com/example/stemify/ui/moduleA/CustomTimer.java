package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.stemify.R;

public class CustomTimer {
    long timerDurationInMinutes; // in minutes
    long timerDurationInMillis; // in milliseconds
    long timeLeftInMillis;
    CountDownTimer countDownTimer;
    TextView timerText;
    boolean timerIsRunning;
    int countdownTVId;
    Context context;
    public CustomTimer(long timerDurationInMinutes, TextView timerText) {
        this.timerDurationInMinutes = timerDurationInMinutes;
        timerDurationInMillis = timerDurationInMinutes * 60 * 1000;
        timeLeftInMillis = timerDurationInMillis;
        this.context = context;
        this.countdownTVId = countdownTVId;
        this.timerText = timerText;
        this.timerIsRunning = false; // haven't initiate timer
    }

    public void startTimer(){

        countDownTimer = new CountDownTimer(timerDurationInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;

                // Update countdown text
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerIsRunning = false;

                // Implement logic here when countdown finishes
            }
        }.start(); // starts timer after method is called

        timerIsRunning = true;
    }

    public void updateCountDownText(){
        int minutes = (int) timeLeftInMillis / 1000 / 60; // get the number of minutes from time left
        int seconds = (int) timeLeftInMillis / 1000 % 60; // get the number of seconds from time left

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);

        timerText.setText(timeLeftFormatted);
    }

    public long getTimeLeftInMillis() {
        return timeLeftInMillis;
    }

    public void setTimeLeftInMillis(long timeLeftInMillis) {
        this.timeLeftInMillis = timeLeftInMillis;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    public boolean isTimerIsRunning() {
        return timerIsRunning;
    }

    public void setTimerIsRunning(boolean timerIsRunning) {
        this.timerIsRunning = timerIsRunning;
    }
}
