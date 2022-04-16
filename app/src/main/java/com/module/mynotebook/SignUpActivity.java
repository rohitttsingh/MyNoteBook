package com.module.mynotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
EditText email,phone, pw,pw2;
TextView gotologin;
AppCompatButton Signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email = findViewById(R.id.emailet);
        phone = findViewById(R.id.phoneet);
        pw = findViewById(R.id.pwet);
        pw2 = findViewById(R.id.cnfpw);
        gotologin = findViewById(R.id.gotologin);

        Signupbtn =  findViewById(R.id.signinbtn);

        Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals(""))
                {
                    email.setError("Enter Email");
                    email.requestFocus();
                    return;
                }
                if (phone.length()<10)
                {
                    phone.setError("Enter 10 Digit Phone Number");
                    phone.requestFocus();
                    return;
                }
                if (pw.length()==0)
                {
                    pw.setError("Enter Email");
                    pw.requestFocus();
                    return;
                }
                if (!pw2.getText().toString().equals(pw.getText().toString()))
                {
                    pw2.setError("Should Match The Password");
                    pw2.requestFocus();
                    return;
                }
                else {

                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    intent.putExtra("phone",phone.getText().toString());
                    intent.putExtra("pw",pw2.getText().toString());
                    startActivity(intent);


                }

            }
        });
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}