package com.example.stemify.ui.moduleD;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Button;

import com.example.stemify.CommunityEventAdapter;
import com.example.stemify.CommunityEventData;
import com.example.stemify.R;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Community extends Fragment {

    RecyclerView recyclerView;
    Button btnNearMe;
    String locationName;
    double eventLat, eventLong;
    double userLat, userLong;

    FusedLocationProviderClient fusedLocationProviderClient;
    CommunityEventData[] communityEventData;
    CommunityEventAdapter eventsAdapter;
    private List<Integer> nearbyEventPositions = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);

        // initialize fused location client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        recyclerView = rootView.findViewById(R.id.recyclerViewEvents);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        communityEventData = new CommunityEventData[]{
                new CommunityEventData("World Health Conference", "27 Nov 2023", "9:00 A.M - 13:00 P.M", "Online - Zoom", getString(R.string.event1), R.drawable.event1),
                new CommunityEventData("NeuroScience Seminar on Mental Health", "12 Jan 2024", "2:00 P.M - 4:00 P.M", "Mountview Palms, San Jose", getString(R.string.event2), R.drawable.event2),
                new CommunityEventData("Wellness Technology Forum", "25 Feb 2024", "10:00 A.M - 1:00 P.M", "Dewan Kuliah 2, UMS, Sabah", getString(R.string.event3), R.drawable.event3),

        };

        eventsAdapter = new CommunityEventAdapter(communityEventData, getContext());
        recyclerView.setAdapter(eventsAdapter);

        //find near me
        btnNearMe = rootView.findViewById(R.id.BtnNearMe);

        btnNearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check conditions
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    //when permission granted call method
                    getCurrentLocation();
                }else {
                    //when permission not granted call method
                    requestPermissions(new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                }
            }
        });


        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //check conditions
        if(requestCode == 100 && (grantResults.length > 0 ) && (grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
            //when permissions granted call method
            getCurrentLocation();
        }else{
            //when permission denied display toast
            Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation(){

        // initialize location manager
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //check condition
        if (locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER)) {


            //when location service enabled get last location
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(
                    new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            //initialize location
                            Location location = task.getResult();

                            //check condition
                            if(location != null){
                                //when location result is not null
                                userLat = location.getLatitude();
                                userLong = location.getLongitude();
                                Log.e("User location", "Lat " + userLat + " Long " +  userLong);
                                //check nearby
                                checkIfNearby(userLat, userLong);

                            }else{
                                // when location result is null, location request
                                LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                        .setInterval(10000)
                                        .setFastestInterval(1000)
                                        .setNumUpdates(1);

                                // initialize call back

                                LocationCallback locationCallback = new LocationCallback() {
                                    @Override
                                    public void onLocationResult(LocationResult locationResult){

                                        super.onLocationResult(locationResult);
                                        LocationServices.getFusedLocationProviderClient(getActivity())
                                                .removeLocationUpdates(this);

                                        if(locationResult != null && locationResult.getLocations().size() > 0){
                                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                                            userLat = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                                            userLong = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                                            Log.e("User location", "Lat " + userLat + " Long " +  userLong);

                                            //check nearby
                                            checkIfNearby(userLat, userLong);
                                        }
                                    }
                                };
                                //request location updated
                                fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                            }
                        }
                    });
        } else{
            //location not enable, open settings
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    //check if any events are nearby user location
    public void checkIfNearby(double userLat, double userLong){

        nearbyEventPositions.clear(); //make list to be clear
        boolean eventFound = false; // if didnt find any events

        for (int i=0; i<communityEventData.length; i++) {

            CommunityEventData eventData = communityEventData[i];
            //get location from cardview
            String place = eventData.getPlace();
            String[] parts = place.split(",");
            locationName = parts[parts.length - 1].trim();
            Log.e("Location retrieve", "location = " + locationName);

            //perform geocoding to get long and lat
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

            try {
                List<Address> addressList = geocoder.getFromLocationName(locationName, 1);
                if (addressList != null && !addressList.isEmpty()) {
                    Address address = addressList.get(0);
                    eventLat = address.getLatitude();
                    eventLong = address.getLongitude();
                    Log.e("Event Location", "Lat = " + eventLat + " Long = " + eventLong);

                    // Calculate distance between the user and the event using their respective latitudes and longitudes
                    float[] distance = new float[1];
                    Location.distanceBetween(userLat, userLong, eventLat, eventLong, distance);

                    //convert meters to km
                    float distanceInKM = distance[0] / 1000;

                    if (distanceInKM <= 100) {
                        nearbyEventPositions.add(i); // Store the positions of nearby events
                        eventFound = true;
                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(!eventFound){
                Toast.makeText(getContext(), "No Nearby Events Found", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "Nearby Events Found!", Toast.LENGTH_SHORT).show();
            }

        }

        //check if nearby event
        eventsAdapter.setNearbyEventPositions(nearbyEventPositions);
        eventsAdapter.notifyDataSetChanged();

    }
}