package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    DatabaseHelper databaseHelper;
    ArrayList<String> image, businessName, emails, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        databaseHelper = new DatabaseHelper(this);
        image = new ArrayList<>();
        businessName = new ArrayList<>();
        emails = new ArrayList<>();
        phone = new ArrayList<>();


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Bundle args = new Bundle();
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("userType");
        String email = bundle.getString("email");

        switch (item.getItemId()) {
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                args.putString("email", email);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        homeFragment).commit();
                break;
            case R.id.nav_donations:
                DonationsFragment donationsFragment = new DonationsFragment();
                args.putString("email", email);
                args.putString("userType", type);
                donationsFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        donationsFragment).commit();
                break;
            case R.id.nav_list:
                Cursor cursor;
                ListFragment listFragment = new ListFragment();

                if (type.equals("Donor")) {
                    cursor = databaseHelper.getListBanks();
                } else {
                    cursor = databaseHelper.getListDonors();
                }

                if (cursor.getCount() == 0) {
                    Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
                }

                while (cursor.moveToNext()) {
                    image.add(cursor.getString(11));
                    businessName.add(cursor.getString(4));
                    emails.add(cursor.getString(2));
                    phone.add(cursor.getString(5));
                }

                args.putStringArrayList("images", image);
                args.putStringArrayList("businessNames", businessName);
                args.putStringArrayList("emails", emails);
                args.putStringArrayList("phones", phone);
                listFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        listFragment).commit();
                break;
            case R.id.nav_about:
                AboutFragment aboutFragment = new AboutFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        aboutFragment).commit();
                break;
            case R.id.nav_settings:
                SettingsFragment settingsFragment = new SettingsFragment();
                args.putString("email", email);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        settingsFragment).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}