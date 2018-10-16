package com.example.parth.logindemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String question,branch,correct;
    protected Button show;
    protected TextView textView;
    DatabaseReference reference;
    FirebaseDatabase database;
    List<String> list;
    EditText ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setTitle("Dashboard");
        ip=(EditText)findViewById(R.id.ipadd);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        textView=(TextView)findViewById(R.id.textView);
        database = FirebaseDatabase.getInstance();

        reference=database.getReference("data");
        list=new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                     question=data.child("question").getValue().toString();
                     correct=data.child("correct").getValue().toString();
                     branch=data.child("branch").getValue().toString();
                    System.out.println("Question: "+question);
                    System.out.println("Correct: "+correct);
                    System.out.println("branch: "+branch);

                }
                String temp=question+" "+correct+" "+branch;
                textView.setText(temp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void signoutEvent(View v) {
        firebaseAuth.getInstance().signOut();
        startActivity(new Intent(SecondActivity.this,MainActivity.class));
    }

    public void toast1(View view)
    {
        Intent i = new Intent(this,SensorData.class);
        startActivity(i);
    }
    public void toast2(View view)
    {
        Intent i = new Intent(this,Feedback.class);
        startActivity(i);
    }
    public void toast3(View view)
    {
        Intent i = new Intent(this,Complain.class);
        startActivity(i);
    }
    public void toast4(View view)
    {
        Intent i = new Intent(this,MotorStatus.class);
        startActivity(i);
    }
    public void saveip(View view)
    {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("ip", ip.getText().toString()); // Storing string

        editor.commit(); // commit changes




    }


}
