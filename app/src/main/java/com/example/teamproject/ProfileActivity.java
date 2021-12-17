package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    DatabaseHelper databaseHelper;
    ArrayList<String> image, businessNames, emails, phones, postDesc, postImage, postDate;


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
        businessNames = new ArrayList<>();
        emails = new ArrayList<>();
        phones = new ArrayList<>();
        postImage = new ArrayList<>();
        postDesc = new ArrayList<>();
        postDate = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("email");
        String businessName = bundle.getString("userBusinessName");
        String userPic = bundle.getString("userPic");
        String type = bundle.getString("userType");


        View headerView = navigationView.getHeaderView(0);
        TextView navEmail = headerView.findViewById(R.id.navEmail);
        TextView navBusinessName = headerView.findViewById(R.id.navName);
        ImageView navPic = headerView.findViewById(R.id.navPic);
        navEmail.setText(email);
        navBusinessName.setText(businessName);
        navPic.setImageURI(Uri.parse(userPic));

        if (type.equals("Donor")) {
            Menu menu = navigationView.getMenu();
            MenuItem itemList = menu.findItem(R.id.nav_list);
            itemList.setTitle("List of Banks");
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        String userId_user = "";

        Bundle args = new Bundle();
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("userType");
        String email = bundle.getString("email");
        String phone = bundle.getString("userPhone");
        String businessName = bundle.getString("userBusinessName");
        String address = bundle.getString("userAddress");
        String city = bundle.getString("userCity");
        String country = bundle.getString("userCountry");
        String pic = bundle.getString("userPic");

        Cursor cursor3 = databaseHelper.getUserId(email);
        if (cursor3.moveToNext()) {
            int userTypeColumn = cursor3.getColumnIndex("user_id");
            userId_user = cursor3.getString(userTypeColumn);
        }


        switch (item.getItemId()) {
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                args.putString("email", email);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        homeFragment).commit();
                break;
            case R.id.nav_donations:
                postImage.clear();
                postDesc.clear();
                postDate.clear();
                emails.clear();
                phones.clear();
                DonationsFragment donationsFragment = new DonationsFragment();

                if (type.equals("Donor")) {
                    Cursor cursor1 = databaseHelper.getAllDataUserPost(userId_user);

                    if (cursor1.getCount() == 0) {
                        Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
                    }

                    while (cursor1.moveToNext()) {
                        postImage.add(cursor1.getString(2));
                        postDesc.add(cursor1.getString(1));
                        postDate.add(cursor1.getString(3));
                        emails.add(email);
                        phones.add(phone);
                    }

                    args.putStringArrayList("images", postImage);
                    args.putStringArrayList("descs", postDesc);
                    args.putStringArrayList("dates", postDate);
                    args.putStringArrayList("emails", emails);
                    args.putStringArrayList("phones", phones);
                } else {
                    Cursor cursor2 = databaseHelper.getAllDataPost();

                    if (cursor2.getCount() == 0) {
                        Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
                    }

                    while (cursor2.moveToNext()) {
                        postImage.add(cursor2.getString(0));
                        postDesc.add(cursor2.getString(1));
                        postDate.add(cursor2.getString(2));
                        emails.add(cursor2.getString(3));
                        phones.add(cursor2.getString(4));
                    }

                    args.putStringArrayList("images", postImage);
                    args.putStringArrayList("descs", postDesc);
                    args.putStringArrayList("dates", postDate);
                    args.putStringArrayList("emails", emails);
                    args.putStringArrayList("phones", phones);
                }

                args.putString("email", email);
                args.putString("userType", type);
                args.putString("userBusinessName", businessName);
                args.putString("userPhone", phone);
                args.putString("userPic", pic);
                donationsFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        donationsFragment).commit();
                break;
            case R.id.nav_list:
                image.clear();
                businessNames.clear();
                emails.clear();
                phones.clear();
                ListFragment listFragment = new ListFragment();
                Cursor cursor;

                if (type.equals("Donor")) {
                    cursor = databaseHelper.getListBanks();
                } else {
                    cursor = databaseHelper.getListDonors();
                }

                if (cursor.getCount() == 0) {
                    Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
                }

                while (cursor.moveToNext()) {
                    image.add(cursor.getString(9));
                    businessNames.add(cursor.getString(4));
                    emails.add(cursor.getString(2));
                    phones.add(cursor.getString(5));
                }

                args.putStringArrayList("images", image);
                args.putStringArrayList("businessNames", businessNames);
                args.putStringArrayList("emails", emails);
                args.putStringArrayList("phones", phones);
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
                args.putString("userBusinessName", businessName);
                args.putString("userPhone", phone);
                args.putString("userAddress", address);
                args.putString("userCity", city);
                args.putString("userCountry", country);
                args.putString("userPic", pic);
                settingsFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        settingsFragment).detach(settingsFragment).attach(settingsFragment).commit();
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(this, "Logout successful", Toast.LENGTH_SHORT).show();
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