package com.knight.eco_fy;

import android.app.Activity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class ComplaintBox extends Activity {
    private Toolbar toolbar;
    SharedPreferences pref;
    SharedPreferences.Editor edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_box);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Eco-fy");


    }



}

