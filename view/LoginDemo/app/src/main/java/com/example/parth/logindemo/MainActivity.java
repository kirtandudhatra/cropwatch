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

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private TextView textView;
    private Button button;
    private int counter=5;
    private TextView tvRegister;
    private TextView forgotpass;
    private FirebaseAuth firebaseAuth;
    private boolean ForgotPasswordResult;
    Login_email login_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        login_email=new Login_email();
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Login");
        editText1=(EditText) findViewById(R.id.editText1);
        editText2=(EditText) findViewById(R.id.editText2);
        textView=(TextView) findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        tvRegister=(TextView)findViewById(R.id.tvRegister);
        textView.setText("No of attemps Remaining: "+counter);
        forgotpass=(TextView)findViewById(R.id.forgotpass);
        firebaseAuth=FirebaseAuth.getInstance();

        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(editText1.getText().toString(),editText2.getText().toString());
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(methodForgotPassword(editText1.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"PASSWORD RESET LINK SENT",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(getApplicationContext(),"Restart app or phone",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private void validate(String usr,String pass){

        firebaseAuth.signInWithEmailAndPassword(usr,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                //    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    counter--;
                    textView.setText("No of attemps Remaining: "+counter);
                    if(counter==0){
                        button.setEnabled(false);
                    }
                }
            }
        });
    }
    public void checkEmailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag=firebaseUser.isEmailVerified();
        if(emailflag==true){
        //    Toast.makeText(this,"Verified",Toast.LENGTH_SHORT);
            finish();
            startActivity(new Intent(MainActivity.this,Login_email.class));
        }
        else{
            Toast.makeText(this,"Verify Email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
    public boolean methodForgotPassword(String email){

            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                ForgotPasswordResult = true;
                            } else {
                                ForgotPasswordResult = false;
                            }
                        }
                    });
            return ForgotPasswordResult;
    }
}
