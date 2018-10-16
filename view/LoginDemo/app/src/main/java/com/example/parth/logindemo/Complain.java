package com.example.parth.logindemo;

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

import java.text.BreakIterator;

public class Complain extends AppCompatActivity {

    EditText complainTitle;
    EditText complainBody;
    Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);
        getSupportActionBar().setTitle("Complain");
        //create variables for storing message & button objects

        complainTitle = (EditText) findViewById(R.id.t4);
        complainBody = (EditText) findViewById(R.id.et);
        //make button object
        sendButton = (Button) findViewById(R.id.submitbutton);

    }
    public void sendComplain(View v)
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.0.113:3000/complain?u_id=1&t='"+complainTitle.getText().toString()+"'&b='"+complainBody.getText().toString()+"'";
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
