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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class adminLogin extends AppCompatActivity implements View.OnClickListener {

    EditText username, password;
    Button adloginbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.adloginbtn).setOnClickListener(this);
        findViewById(R.id.backVoterLogin).setOnClickListener(this);

        username = findViewById(R.id.adminUsername);
        password = findViewById(R.id.adminPassword);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.adloginbtn:
                adminLogin();
                break;
            case R.id.backVoterLogin:
                Intent back_act = new Intent(getApplicationContext(), log_in.class);
                startActivity(back_act);
                break;
        }
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            Intent register_act = new Intent(getApplicationContext(), index.class);
            startActivity(register_act);
        }
    }*/

    private void adminLogin(){
        String usernameStr = username.getText().toString();
        String passStr = password.getText().toString();

        if(usernameStr.isEmpty()){
            username.setError("Please provide Email");
            username.requestFocus();
            return;
        }

        if(passStr.isEmpty()){
            password.setError("Please provide Password");
            password.requestFocus();
            return;
        }

        usernameStr += "@gmail.com";

        mAuth.signInWithEmailAndPassword(usernameStr,passStr)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            Toast.makeText(adminLogin.this, "Successful Login", Toast.LENGTH_SHORT).show();
                            Intent login_act = new Intent(getApplicationContext(), adminIndex.class);
                            startActivity(login_act);
                        } else {
                            Toast.makeText(adminLogin.this, "Invalid Username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
        });
    }
}