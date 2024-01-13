package com.example.stemify;

public class Counselor_ViewData {

    String booking_dr_name, booking_time, booking_date;

    public Counselor_ViewData(String booking_dr_name, String booking_time, String booking_date) {
        this.booking_dr_name = booking_dr_name;
        this.booking_time = booking_time;
        this.booking_date = booking_date;
    }

    public String getBooking_dr_name() {
        return booking_dr_name;
    }

    public void setBooking_dr_name(String booking_dr_name) {
        this.booking_dr_name = booking_dr_name;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }
}
