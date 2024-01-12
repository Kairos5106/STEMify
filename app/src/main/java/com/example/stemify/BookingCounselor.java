package com.example.stemify;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingCounselor extends AppCompatActivity {

    TextView drName, drInfo, drExp, drPatient;
    ImageView detailImage;

    Button time8am, time10am, time12pm, time2pm, time4pm, time6pm, btnBook, btnCancel;
    CalendarView calendarView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counselor_booking);

        //for calender picker
        calendarView = findViewById(R.id.calenderView);
        calendar = Calendar.getInstance();

        setDate(1, 1, 2024); //date to be displayed

        getDate();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(BookingCounselor.this, dayOfMonth +"/" +month+1 +"/"+year, Toast.LENGTH_SHORT).show();

            }
        });

        //for time chosen
        time8am = findViewById(R.id.btnTime1);
        time10am = findViewById(R.id.btnTime2);
        time12pm = findViewById(R.id.btnTime3);
        time2pm = findViewById(R.id.btnTime4);
        time4pm  = findViewById(R.id.btnTime5);
        time6pm = findViewById(R.id.btnTime6);

        time8am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create toast
            }
        });

    }

    public void getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
    }

    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }
}
