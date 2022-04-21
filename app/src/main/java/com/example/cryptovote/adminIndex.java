package com.example.cryptovote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminIndex extends AppCompatActivity implements View.OnClickListener{
    private final Blockchain blockchain = new Blockchain();
    int slow = 0, fast = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_index);

        findViewById(R.id.indexAddcand).setOnClickListener(this);
        findViewById(R.id.indexViewcand).setOnClickListener(this);
        findViewById(R.id.indexViewvoter).setOnClickListener(this);
        findViewById(R.id.indexStartElection).setOnClickListener(this);
        findViewById(R.id.indexEndElection).setOnClickListener(this);
        findViewById(R.id.adminLogout).setOnClickListener(this);
        findViewById(R.id.indexVerifyAll).setOnClickListener(this);
        try {
            blockchain.initAdmin();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.indexAddcand:
                Intent addcand = new Intent(getApplicationContext(),add_candidate.class);
                startActivity(addcand);
                break;
            case R.id.indexViewcand:
                Intent viewcand = new Intent(getApplicationContext(),viewCandidates.class);
                startActivity(viewcand);
                break;
            case R.id.indexViewvoter:
                Intent viewvoter = new Intent(getApplicationContext(),viewVoter.class);
                startActivity(viewvoter);
                break;
            case R.id.indexStartElection:
                StartElection();
                break;
            case R.id.indexEndElection:
                EndElection();
                break;
            case R.id.adminLogout:
                adminlogout();
                break;
            case R.id.indexVerifyAll:
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
               /* reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                            fast = (int) snapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
                try {
                    fast = blockchain.countVoters();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for(int i = 0; i <= fast; ++i){
                    try {
                        blockchain.VerifyVoter(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                int count = 0;
                try {
                    count = blockchain.countVoters();
                    for(int i = 0; i <= count; ++i)
                        blockchain.setVoterWeight(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "Verified all voters", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void EndElection(){
        try {
            blockchain.endElection();
            Toast.makeText(this, "Election Ended", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Election has not started or already ended", Toast.LENGTH_SHORT).show();
        }
    }

    private void StartElection() {
        try {
            blockchain.startElection();
            Toast.makeText(this, "Election Started", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Election has already started", Toast.LENGTH_SHORT).show();
        }
    }

    private void adminlogout() {
        FirebaseAuth.getInstance().signOut();
        Intent logout_act = new Intent(getApplicationContext(), log_in.class);
        startActivity(logout_act);
    }
}