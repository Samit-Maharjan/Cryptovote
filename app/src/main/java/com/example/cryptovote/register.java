package com.example.cryptovote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class register extends AppCompatActivity implements View.OnClickListener{
    EditText fname, lname, cpass, email, password, adhaar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);

        fname =  findViewById(R.id.FirstName);
        lname = findViewById(R.id.LastName);
        cpass = findViewById(R.id.ConfirmPass);
        password = findViewById(R.id.Pass);
        email = findViewById(R.id.Email);
        adhaar  = findViewById(R.id.Aadhaar);

        findViewById(R.id.register).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        String FirstName = fname.getText().toString();
        String LastName = lname.getText().toString();
        String emaill = email.getText().toString();
        String pass = password.getText().toString();
        String Confpass = cpass.getText().toString();
        String adh = adhaar.getText().toString();

        if(FirstName.isEmpty()){
            fname.setError("Please provide first name");
            fname.requestFocus();
            return;
        }
        if(LastName.isEmpty()){
            lname.setError("Please provide last name");
            lname.requestFocus();
            return;
        }
        if(!adh.matches("^[2-9]{1}[0-9]{11}$")){
            adhaar.setError("Please Enter valid aadhaar number");
            adhaar.requestFocus();
            return;
        }
        if(emaill.isEmpty()){
            email.setError("Please provide Email");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("Please provide Password");
            password.requestFocus();
            return;
        }
        if(Confpass.isEmpty()){
            cpass.setError("Please provide Confirm Password");
            cpass.requestFocus();
            return;
        }
        if(adh.isEmpty()){
            adhaar.setError("Please provide adhaar number");
            adhaar.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        if(!Confpass.equals(pass)) {
            cpass.setError("Passwords doesnot Match");
            cpass.requestFocus();
            return;
        }
        if(!pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
            password.setError("Password should contain at least one special and uppercase character");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emaill,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            voterReg vot = new voterReg(FirstName, LastName, emaill, adh);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(vot).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(register.this, "Successfully Registered. Please verify your email", Toast.LENGTH_LONG).show();
                                        Intent register_act = new Intent(getApplicationContext(), log_in.class);
                                        startActivity(register_act);
                                    } else {
                                        Toast.makeText(register.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}
