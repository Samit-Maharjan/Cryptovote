package com.example.cryptovote;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

public class voting_interface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_interface);

        Button vote = findViewById(R.id.vote);
        vote.setOnClickListener(
                v -> nextActivity()
        );
    }

    public void nextActivity(){
        //Intent intent = new Intent(this, VotingForm.class);
        //startActivity(intent);
    }
}