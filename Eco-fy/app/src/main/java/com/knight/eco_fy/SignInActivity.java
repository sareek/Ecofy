package com.knight.eco_fy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SignInActivity extends Activity {
    private Toolbar toolbar;
    EditText name, email, password;
    String res;
    Button signin, signup;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Eco-fy");
        name = (EditText) findViewById(R.id.input_name);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        signup = (Button) findViewById(R.id.btn_signup);
        pref = getSharedPreferences("UserPref", MODE_PRIVATE);
        edit = pref.edit();
        if(isLogged())
        {
            Intent intent = new Intent(SignInActivity.this,Menu.class);
            startActivity(intent);
            SignInActivity.this.finish();
        }
    }
    public void setLogin(boolean isLoggedIn) {

        edit.putBoolean("Lstatus", isLoggedIn);
        edit.putString("username", name.getText().toString());
        edit.putString("password", password.getText().toString());
        edit.commit();
        // commit changes



    }
    public void signup(View view) {
        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
        startActivity(intent);
        SignInActivity.this.finish();
    }

    public boolean isLogged(){


        return pref.getBoolean("Lstatus", false);
    }

    public void signin(View view) {

        new AsyncLogin().execute(name.getText().toString(),password.getText().toString());

    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(SignInActivity.this);
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
                url = new URL("http://192.168.0.113/Ecofy/esignin.php");

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
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
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
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */

                //Storing Data using SharedPreferences

                 setLogin(true);

                Intent intent = new Intent(SignInActivity.this,Menu.class);
                startActivity(intent);
                SignInActivity.this.finish();

            } else if (result.equalsIgnoreCase("failed")) {

                // If username and password does not match display a error message
                Toast.makeText(SignInActivity.this, "Username or Password does not match", Toast.LENGTH_LONG).show();

            }


        }
    }
}

