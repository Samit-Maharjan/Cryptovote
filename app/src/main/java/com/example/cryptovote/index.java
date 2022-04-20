package com.example.cryptovote;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.web3j.protocol.core.methods.response.EthBlock;


public class index extends AppCompatActivity {
    Button logout;
    BottomNavigationView bottom_nav;
    IndexFragment indexFragment =  new IndexFragment();
    UserNotRegistered userNotRegistered = new UserNotRegistered();
    election_not_started Election_not_started = new election_not_started();
    ResultFragment resultFragment = new ResultFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    private DatabaseReference reference;
    int id;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        bottom_nav = findViewById(R.id.bottom_navigation);
        Blockchain blockchain = new Blockchain();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, indexFragment).commit();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                voterReg userProfile = snapshot.getValue(voterReg.class);
                if(userProfile != null){
                    id = userProfile.getUserID();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String address = blockchain.getAddress(id);
        try {
            if (!blockchain.CheckRegistered(id))
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, userNotRegistered).commit();

            else if (!blockchain.CheckElectionStart())
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Election_not_started).commit();

            else
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, indexFragment).commit();
        }
        catch(Exception e){
            System.out.println(e);
        }
        bottom_nav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.contests:
                    try {
                        if (!blockchain.CheckRegistered(id))
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, userNotRegistered).commit();

                        else if (!blockchain.CheckElectionStart())
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Election_not_started).commit();

                        else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, indexFragment).commit();
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    return true;
                case R.id.result:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
                    return true;

                case R.id.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
                    return true;
            }
                return false;
            });
        }
}