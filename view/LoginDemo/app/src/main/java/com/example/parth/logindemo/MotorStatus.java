package com.example.parth.logindemo;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MotorStatus extends AppCompatActivity {

    TextView status;
    String urlJsonArry = "http://";
    Button getstatus;
    Button motoron;
    Button motoroff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_status);
        getSupportActionBar().setTitle("Motor Status");
        status = (TextView)findViewById(R.id.mstatus);
        getstatus = (Button) findViewById(R.id.getstatus);
        motoron = (Button) findViewById(R.id.mon);
        motoroff = (Button) findViewById(R.id.moff);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        urlJsonArry+=pref.getString("ip", null);
        urlJsonArry+="/motorstatus";

        getstatus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // making json array request
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeJsonArrayRequest();
                        handler.postDelayed(this, 2000);
                    }
                }, 5000);  //the time is in miliseconds

            }
        });

    }

    private void makeJsonArrayRequest() {



        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            // Parsing json array response
                            // loop through each json object

                            JSONObject data = (JSONObject) response
                                    .get(0);
                            String statusm = data.getString("status");

                            if(statusm.equals("1"))
                                status.setText("Pump is: ON");
                            else
                                status.setText("Pump is: OFF");




                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    public void motorOff(View view){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode


        String url ="http://";
        url+=pref.getString("ip", null);
        url+="/motoroff";

        final String s2test=url;
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
    public void motorOn(View view){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode


        String url ="http://";
        url+=pref.getString("ip", null);
        url+="/motoron";

        final String s2test=url;
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

}
