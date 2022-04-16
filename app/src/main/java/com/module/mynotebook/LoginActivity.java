package com.module.mynotebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
EditText phone,pw;
TextView gotosignup,forgetpassword;
AppCompatButton login;
String phonestr,pwstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.etphone);
        pw = findViewById(R.id.etpw);
        gotosignup = findViewById(R.id.gotosignup);
        login = findViewById(R.id.loginbtn);
        forgetpassword=findViewById(R.id.forgetpassword);

        phonestr =getIntent().getStringExtra("phone");
        pwstr =getIntent().getStringExtra("pw");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!phone.getText().toString().equals("1234567890"))
                {
                    phone.setError("Doesn't Exists");
                    phone.requestFocus();
                    return;
                }
                if (!pw.getText().toString().equals("pass"))
                {
                    pw.setError("Doesn't Match");
                    pw.requestFocus();
                    return;
                }
                else {
                    startActivity(new Intent(getApplicationContext(),HomeScreenActivity.class));
                }
            }
        });

        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));

            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Work In Progress", Toast.LENGTH_SHORT).show();
            }
        });

        login.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(getApplicationContext(),HomeScreenActivity.class));

                return false;
            }
        });
    }
}