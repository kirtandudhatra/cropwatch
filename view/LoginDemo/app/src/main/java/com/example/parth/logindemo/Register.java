package com.example.parth.logindemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText reg_name;
    private EditText reg_password;
    private EditText reg_confirm;
    private EditText reg_email;
    private Button reg_button;
    private TextView reg_login;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupView();
        getSupportActionBar().setTitle("Register");

        firebaseAuth=FirebaseAuth.getInstance();

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //db work
                    final String regname=reg_name.getText().toString().trim();
                    final String email=reg_email.getText().toString().trim();
                    String password=reg_password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                                DatabaseReference databaseReference=firebaseDatabase.getReference("users");
                                DatabaseReference databaseReference1=databaseReference.child(FirebaseAuth.getInstance().getUid());
                                databaseReference1.child("Name").setValue(regname);
                                databaseReference1.child("Email").setValue(email);
                                sendEmailVerification();
                            }
                            else{
                                Toast.makeText(Register.this, "Registration FAILED", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }
            }
        });
        reg_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,MainActivity.class));
            }
        });
    }

    private void setupView(){
        reg_name=(EditText)findViewById(R.id.reg_name);
        reg_password=(EditText)findViewById(R.id.reg_password);
        reg_email=(EditText)findViewById(R.id.reg_email);
        reg_login=(TextView) findViewById(R.id.reg_login);
        reg_button=(Button) findViewById(R.id.reg_button);
        reg_confirm=(EditText)findViewById(R.id.reg_confirm);

    }
    private boolean validate(){
         Boolean result=false;
         String name=reg_name.getText().toString();
         String email=reg_email.getText().toString();
         String password=reg_password.getText().toString();
         String confirm=reg_confirm.getText().toString();
         if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()){
             Toast.makeText(this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
         }
         if(!(password.equals(confirm))){
             Toast.makeText(this, "Password do not Match", Toast.LENGTH_SHORT).show();
         }
         else{
             result=true;
         }
         return result;
    }
    private void sendEmailVerification(){
        final FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Mail sent",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Register.this,MainActivity.class));
                    }
                    else
                        Toast.makeText(getApplicationContext(),"ERRORRRRR",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
