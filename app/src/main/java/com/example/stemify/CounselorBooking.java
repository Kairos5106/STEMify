package com.example.stemify;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.stemify.ui.moduleD.Counseling;
import com.example.stemify.ui.moduleD.HealthGuidance;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CounselorBooking extends AppCompatActivity {

    TextView drName, drInfo, drExp, drEmail;
    ImageView detailImage;

    Button time8am, time10am, time12pm, time2pm, time4pm, time6pm, btnBook, btnCancel;
    CalendarView calendarView;
    Calendar calendar;
    String selectedTime, selected_date, emailUser, emailDr, nameDr;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counselor_booking);

        //database set to node Bookings
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Bookings").child(userId);

        //toolbar

        Toolbar toolbar = findViewById(R.id.TBCounselling);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Psychiatrist Booking");

        //get details from counselors page to booking page
        drName = findViewById(R.id.textName);
        drInfo = findViewById(R.id.textInfo);
        drExp = findViewById(R.id.textExp);
        drEmail = findViewById(R.id.textEmail);
        detailImage = findViewById(R.id.imageviewDetail);

        Intent intent = getIntent();
        if (intent != null){
            nameDr = intent.getStringExtra("Name");
            String exp = intent.getStringExtra("Experience");
            String info = intent.getStringExtra("Information");
            emailDr = intent.getStringExtra("Email");
            int image = intent.getIntExtra("Image", R.drawable.female);

            drName.setText(nameDr);
            drInfo.setText(info);
            drExp.setText(exp);
            drEmail.setText(emailDr);
            Log.d("EmailDr", "Doctor's Email: " + emailDr);

            detailImage.setImageResource(image);
        }

        //getting users email address
        if (currentUser != null){
            // Read from the database
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        emailUser = currentUser.getEmail();
                        Log.d("EmailRetrieval", "User Email: " + emailUser);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError e) {
                    Log.e("Email", "User email not received from database" + e.getMessage());
                }
            });
        }


        //for calender picker
        calendarView = findViewById(R.id.calenderView);
        calendar = Calendar.getInstance();

        setDate(1, 1, 2024); //date to be displayed

        getDate();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(CounselorBooking.this, dayOfMonth +"/" +month+1 +"/"+year, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(getApplicationContext(), "8 am selected", Toast.LENGTH_SHORT).show();
            }
        });
        time8am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "8 am selected", Toast.LENGTH_SHORT).show();
                selectedTime = "8:00 AM";
            }
        });
        time10am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "10 am selected", Toast.LENGTH_SHORT).show();
                selectedTime = "10:00 AM";
            }
        });
        time12pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "12 pm selected", Toast.LENGTH_SHORT).show();
                selectedTime = "12:00 PM";
            }
        });
        time2pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "2 pm selected", Toast.LENGTH_SHORT).show();
                selectedTime = "2:00 PM";
            }
        });
        time4pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "4 pm selected", Toast.LENGTH_SHORT).show();
                selectedTime = "4:00 PM";
            }
        });
        time6pm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "6 pm selected", Toast.LENGTH_SHORT).show();
                selectedTime = "6:00 PM";
            }
        });

        //booking
        btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTime != null){
                    //send email to dr and user
                    sendEmail(v);

                    //save data in firebase
                    HashMap<String, String> usermap = new HashMap<>();
                    usermap.put("booking_dr_name", nameDr);
                    usermap.put("booking_time", selectedTime);
                    usermap.put("booking_date", selected_date);

                    DatabaseReference bookingdata = databaseReference.push();

                    bookingdata.setValue(usermap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if (error == null) {
                                // Data was successfully written
                                Log.d("Firebase", "Data saved successfully!");
                            } else {
                                // There was an error writing the data
                                Log.d("Firebase", "Data could not be saved. Error: " + error.getMessage());
                            }
                        }
                    });



                }else {
                    //alert message to choose time
                    openTimeChooseDialog();
                }

            }
        });

        //cancelling
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCancelDialog();
            }
        });

    }

    //send email on book button clicked
    public void sendEmail(View v){
        try {
            String senderEmail = "stemify16@gmail.com";
            String receiverEmail = emailUser;
            String ccDrEmail = emailDr;
            String passwordSender = "vugv didu jlci gups";

            String stringHost = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, passwordSender);
                }
            });

            MimeMessage mimeMessage = new MimeMessage(session);
            InternetAddress[] toAddresses = { new InternetAddress(emailUser) };
            mimeMessage.addRecipients(Message.RecipientType.TO, toAddresses);

            // cc recipient
            InternetAddress[] ccAddresses = { new InternetAddress(emailDr) };
            mimeMessage.addRecipients(Message.RecipientType.CC, ccAddresses);

            mimeMessage.setSubject("STEMify Psychiatrist Booking");
            mimeMessage.setText("Your booking with " + nameDr + " is confirmed at " + selectedTime + "\nPlease wait for the meeting link to arrive. " +
                    "\nThank you for using STEMify ~ your partner in STEM, have a good day!");

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Transport.send(mimeMessage);
                    }catch (MessagingException e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }catch (AddressException e){
            e.printStackTrace();
            Log.e("EmailError", "AddressException: " + e.getMessage());
        }catch (MessagingException e){
            e.printStackTrace();
            Log.e("EmailError", "MessagingException: " + e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            Log.e("EmailError", "OtherException: " + e.getMessage());
        }
        openBookingDialog();
    }

    //open alert dialog box to show to pick time before booking
    private void openTimeChooseDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CounselorBooking.this);
        builder.setTitle("Alert!")
                .setMessage("Please select a time slot before booking")
                .setNegativeButton("OK",null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    //open alert dialog box to show confirm message
    private void openBookingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CounselorBooking.this);
        builder.setTitle("Confirmation")
                .setMessage("Your Booking is Confirmed")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CounselorBooking.this, HealthGuidance.class);
                        startActivity(intent);
                    }
                });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    //open alert message to cancel booking
    private void openCancelDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CounselorBooking.this);
        builder.setTitle("Cancel")
                .setMessage("Confirm Cancel Booking?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //go back to counsellor lists
                        Intent intent = new Intent(CounselorBooking.this, Counseling.class);
                        startActivity(intent);


                    }
                })
                .setNegativeButton("No",null);

        //create and show alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    public void getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        selected_date = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
    }

    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
