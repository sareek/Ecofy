package com.knight.eco_fy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class dashboard extends Activity {
    private Toolbar toolbar;
    TextView location;
    Button locateit;
    String locationofuser=null;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    GPSTracker gps;
    RatingBar b1, b2, b3;
    String s1, s2, s3;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Eco-fy");
        location = (TextView) findViewById(R.id.textView);
        locateit = (Button) findViewById(R.id.button);
        b1 = (RatingBar) findViewById(R.id.ratingBar);
        b2 = (RatingBar) findViewById(R.id.ratingBar1);
        b3 = (RatingBar) findViewById(R.id.ratingBar2);
        useGPS();
    }

    public void rate(View view) {
        s1 = Float.toString(b1.getRating());
        s2 = Float.toString(b2.getRating());
        s3 = Float.toString(b3.getRating());
        pref = getSharedPreferences("UserPref", MODE_PRIVATE);
        String name = pref.getString("username", null);



        new AsyncLogin().execute(locationofuser,name,s1, s2, s3);

    }

    public void useGPS() {
        gps = new GPSTracker(dashboard.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Geocoder geocoder;
            List<Address> addresses;
            try {
                geocoder = new Geocoder(this, Locale.getDefault());

                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                // \n is for new line
                locationofuser = address+","+city;
                location.setText("Location: "+address + " " + city);
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        gps.stopUsingGPS();
    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(dashboard.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://192.168.0.113/Ecofy/rate.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("place",params[0])
                        .appendQueryParameter("username", params[1])
                        .appendQueryParameter("greenery", params[2])
                        .appendQueryParameter("cleanliness", params[3])
                        .appendQueryParameter("facilities", params[4]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method
                return (result.toString());


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();


            if (result.equalsIgnoreCase("success")) {
                Toast.makeText(dashboard.this, "Rating Successful", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("failed")) {

                // If username and password does not match display a error message
                Toast.makeText(dashboard.this, "Error", Toast.LENGTH_LONG).show();

            }


        }

    }

}
