package com.example.cryptovote;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class log_in extends AppCompatActivity {
    EditText email, password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Login Action
        login = findViewById(R.id.log_in_button);
        login.setOnClickListener(
                v -> {
                    email = findViewById(R.id.editTextEmailAddress);
                    password = findViewById(R.id.editTextPassword);
                });

        //Forgot Password
        TextView forgot = findViewById(R.id.forgot_password);
        forgot.setOnClickListener(
                v ->{
                    Intent forgot_pass = new Intent(getApplicationContext(), register.class);
                    startActivity(forgot_pass);
                }
        );

        //Register New User
        TextView register = findViewById(R.id.new_user_lin);
        register.setOnClickListener(
                v -> {
                   Intent register_act = new Intent(getApplicationContext(), register.class);
                   startActivity(register_act);
                }
        );
       //TO-DO -> link SQL Database & Authentication
        //TO-DO -> Input sanitization
    }
}