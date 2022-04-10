package com.example.cryptovote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    EditText email;
    Button resetbutton;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_forgot_password);

        resetbutton = findViewById(R.id.forgot_button);

        email = findViewById(R.id.ForgotEmailAddress);

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetpassword();
            }
        });
    }

    private void resetpassword(){
        String emaill = email.getText().toString();

        if(emaill.isEmpty()){
            email.setError("Please provide Email");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(emaill).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotPassword.this, "Check your Email to reset your password", Toast.LENGTH_SHORT).show();
                    Intent forgot_act = new Intent(getApplicationContext(), log_in.class);
                    startActivity(forgot_act);
                }
                else{
                    Toast.makeText(ForgotPassword.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}