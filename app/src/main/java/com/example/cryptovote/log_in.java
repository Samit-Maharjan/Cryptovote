package com.example.cryptovote;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class log_in extends AppCompatActivity implements View.OnClickListener {
    EditText email, password;
    Button login_btn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_log_in);

        findViewById(R.id.log_in_button).setOnClickListener(this);
        findViewById(R.id.new_user_lin).setOnClickListener(this);
        findViewById(R.id.forgot_password).setOnClickListener(this);

        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.new_user_lin:
                Intent register_act = new Intent(getApplicationContext(), register.class);
                startActivity(register_act);
                break;

            case R.id.forgot_password:
//                Intent register_act = new Intent(getApplicationContext(), .class);
//                startActivity(register_act);
                break;
            case R.id.log_in_button:
                userLogin();
//                Intent register_act = new Intent(getApplicationContext(), register.class);
//                startActivity(register_act);
                break;
        }
    }

    private void userLogin() {
        String emailstr = email.getText().toString();
        String passstr = password.getText().toString();

        if(emailstr.isEmpty()){
            email.setError("Please provide Email");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailstr).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }

        if(passstr.isEmpty()){
            password.setError("Please provide Password");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailstr,passstr)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if(user.isEmailVerified()){
                                Toast.makeText(log_in.this, "Successful Login", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                user.sendEmailVerification();
                                Toast.makeText(log_in.this, "Check your email to verify account", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else{
                            Toast.makeText(log_in.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}