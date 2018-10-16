package com.example.parth.logindemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Feedback extends AppCompatActivity {
    EditText feedbackTitle;
    EditText feedbackBody;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setTitle("Feedback");
        feedbackTitle = (EditText) findViewById(R.id.t);
        feedbackBody = (EditText) findViewById(R.id.et2);
        //make button object
        send = (Button) findViewById(R.id.submitbutton2);

    }

    public void sendfeedback(View v)
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode


        String url ="http://";
        url+=pref.getString("ip", null);
        url +="/feedback?u_id=1&t='"+feedbackTitle.getText().toString()+"'&b='"+feedbackBody.getText().toString()+"'";
        final String s2test=url;
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getBaseContext(), "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Fail",
                        Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),s2test,
                        Toast.LENGTH_LONG).show();

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

