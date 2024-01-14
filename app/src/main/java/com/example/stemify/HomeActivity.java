package com.example.stemify;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stemify.ui.moduleA.HomePageA;
import com.example.stemify.ui.moduleB.HomePageB;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


import com.example.stemify.databinding.ActivityHomeBinding;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    TextView TVUserName, TVUserEmail;
    ImageView IVUserImage;
    SharedPreferences userPrefs;

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up side navigation drawer & app bar
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homePageA)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Set up bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        // Get user info to change header info
        userPrefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userName = userPrefs.getString("userName", "Username");
        String userEmail = userPrefs.getString("userEmail", "Email");
        String userPhotoUrl = userPrefs.getString("userPhotoUrl", null);

        // Change header to fit user information in side navigation bar
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        TVUserName = headerView.findViewById(R.id.TVUsername);
        TVUserName.setText(userName);
        TVUserEmail = headerView.findViewById(R.id.TVUserEmail);
        TVUserEmail.setText(userEmail);

        IVUserImage = headerView.findViewById(R.id.IVUserImage);
        Picasso.get().load(userPhotoUrl).into(IVUserImage); // change user icon depending on user identity
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}