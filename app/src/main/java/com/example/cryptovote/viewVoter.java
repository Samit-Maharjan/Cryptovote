package com.example.cryptovote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewVoter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_voter);

        voterAdapter votadapter = new voterAdapter(this, new ArrayList<voterReg>(), new ArrayList<String>());
        final ListView candidateView = (ListView) findViewById(R.id.voters_view);


        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Blockchain blockchain = new Blockchain();
                for(DataSnapshot d: snapshot.getChildren()){
                    voterReg user = d.getValue(voterReg.class);

                    String voted = null;
                    try {
                        int id = user.getUserID();
                        if(!blockchain.CheckRegistered(id))
                            voted = "NR";
                        else
                            voted = (blockchain.checkVoted(user.getUserID()) ? "Yes" : "No");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    votadapter.add(user, voted);
                }
                candidateView.setAdapter(votadapter);
                votadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}