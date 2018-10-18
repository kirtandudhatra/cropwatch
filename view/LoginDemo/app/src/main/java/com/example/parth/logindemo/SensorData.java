package com.example.parth.logindemo;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
    SpannableStringBuilder builder;

    String urlJsonArry = "http://";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        builder = new SpannableStringBuilder();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);
        getSupportActionBar().setTitle("Live Data");
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode

        urlJsonArry+=pref.getString("ip", null);
        urlJsonArry+="/fetch?u_id=1";
        Toast.makeText(getApplicationContext(),urlJsonArry,Toast.LENGTH_LONG).show();
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
                                String dt = "Date-Time:";
                                SpannableString redSpannable= new SpannableString(dt);
                                redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, dt.length(), 0);
                                builder.append(redSpannable);

                                SpannableString t= new SpannableString(time+"\t\n");
                                builder.append(t);

                                String humi = "Humidity:";
                                SpannableString humi2= new SpannableString(humi);
                                humi2.setSpan(new ForegroundColorSpan(Color.GREEN), 0, humi.length(), 0);
                                builder.append(humi2);

                                SpannableString h= new SpannableString(hum+"\t\n");
                                builder.append(h);

                                String tem = "Temperature:";
                                SpannableString tem2= new SpannableString(tem);
                                tem2.setSpan(new ForegroundColorSpan(Color.BLUE), 0, tem.length(), 0);
                                builder.append(tem2);

                                SpannableString tmp= new SpannableString(temp+"\t\n");
                                builder.append(tmp);

                                String moi = "Moisture:";
                                SpannableString moi2= new SpannableString(moi);
                                moi2.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, moi.length(), 0);
                                builder.append(moi2);

                                SpannableString mois= new SpannableString(moisture+"\t\n\n\n");
                                builder.append(mois);



                            }

                            txtResponse.setText(builder, TextView.BufferType.SPANNABLE);
                            builder.clear();
                            

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
