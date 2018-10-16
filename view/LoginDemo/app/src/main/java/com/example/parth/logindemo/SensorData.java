package com.example.parth.logindemo;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SensorData extends AppCompatActivity {
    Button btnMakeArrayRequest;
    TextView txtResponse;
    ProgressDialog pDialog;
    String jsonResponse;
    String urlJsonArry = "http://192.168.0.113:3000/fetch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);
        getSupportActionBar().setTitle("Live Data");
         btnMakeArrayRequest = (Button) findViewById(R.id.btnArrayRequest);
        txtResponse = (TextView) findViewById(R.id.txtResponse);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {

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

        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            // Parsing json array response
                            // loop through each json object
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject data = (JSONObject) response
                                        .get(i);

                                String temp = data.getString("temperature_data");
                                String hum = data.getString("humidity_data");
                                String moisture = data
                                        .getString("moisture_data");
                                String time = data.getString("date_time");
                                if(moisture.equals("1")){
                                    moisture = "Yes";
                                }
                                else{
                                    moisture = "No";
                                }


                                jsonResponse += "Date-Time: " + time + "\t\n";
                                jsonResponse += "Humidity: " + hum + "\t";
                                jsonResponse += "Temperature: " + temp + "\t";
                                jsonResponse += "Moisture: " + moisture + "\t\n\n";


                            }

                            txtResponse.setText(jsonResponse);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
        {}//pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
