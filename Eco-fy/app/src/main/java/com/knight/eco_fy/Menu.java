package com.knight.eco_fy;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class Menu extends Activity {
    private Toolbar toolbar;
    SharedPreferences pref;
    SharedPreferences.Editor edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Eco-fy");
        pref = getSharedPreferences("UserPref", MODE_PRIVATE);
        edit = pref.edit();

        ActivityCompat.requestPermissions(Menu.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                1);

    }
    public void rate(View view) {
        Intent intent = new Intent(Menu.this,dashboard.class);
        startActivity(intent);

    }
    public void logout(View view) {
        edit.putBoolean("Lstatus", false);
        edit.commit();
        Intent intent = new Intent(Menu.this,SignInActivity.class);
        startActivity(intent);

    }
    public void complaint(View view) {
        Intent intent = new Intent(Menu.this,ComplaintBox.class);
        startActivity(intent);

    }


}

