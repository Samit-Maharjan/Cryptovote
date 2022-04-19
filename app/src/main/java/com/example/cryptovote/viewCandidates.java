package com.example.cryptovote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewCandidates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_candidates);

        candidateAdapter2 cad = new candidateAdapter2(this, new ArrayList<candidate>(), new ArrayList<Integer>());
        final ListView candidateView = (ListView) findViewById(R.id.records_view);


        DatabaseReference database = FirebaseDatabase.getInstance().getReference("candidate");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Blockchain blockchain = new Blockchain();
                for(DataSnapshot d: snapshot.getChildren()){
                    candidate user = d.getValue(candidate.class);

                    try {
                        cad.add(user, blockchain.GetCandidate(user.getbID())
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                candidateView.setAdapter(cad);
                cad.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}