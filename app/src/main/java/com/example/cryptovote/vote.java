package com.example.cryptovote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;

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
        String userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                voterReg userProfile = snapshot.getValue(voterReg.class);
                if(userProfile != null){
                    int UID = userProfile.getUserID();
                    Blockchain blockchain = new Blockchain();
                    try {
                        blockchain.voteCandidate(BigInteger.valueOf(bID), UID);
                    } catch (Exception e) {
                        Toast.makeText(vote.this, "The User has already casted their Vote!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        TextView cName = findViewById(R.id.indCandname);
        cName.setText(candName);

        TextView cID = findViewById(R.id.indCandID);
        cID.setText("ID: " + candID);

    }
}