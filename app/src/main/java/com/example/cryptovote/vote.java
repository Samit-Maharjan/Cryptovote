package com.example.cryptovote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class vote extends AppCompatActivity {
    Button vote;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String candID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        Bundle bundle = getIntent().getExtras();
        String candName = bundle.getString("name");

        String candID = bundle.getString("ID");

        int bID = bundle.getInt("bID");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
//        int userID = user.getUid();


        TextView cName = findViewById(R.id.indCandname);
        cName.setText(candName);

        TextView cID = findViewById(R.id.indCandID);
        cID.setText("ID: " + candID);

    }
}