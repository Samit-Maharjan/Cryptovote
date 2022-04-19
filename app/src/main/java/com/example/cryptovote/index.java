package com.example.cryptovote;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.web3j.protocol.core.methods.response.EthBlock;


public class index extends AppCompatActivity {
    Button logout;
    BottomNavigationView bottom_nav;
    IndexFragment indexFragment =  new IndexFragment();
    UserNotRegistered userNotRegistered = new UserNotRegistered();
    election_not_started Election_not_started = new election_not_started();
    ResultFragment resultFragment = new ResultFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        bottom_nav = findViewById(R.id.bottom_navigation);
        Blockchain blockchain = new Blockchain();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, indexFragment).commit();

        int id = ;// To-do
        String address = blockchain.getAddress(id);
        bottom_nav.setOnItemSelectedListener(item -> {

            if(!blockchain.checkRegistered(id) )
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, userNotRegistered).commit();

            else if(!blockchain.checkElectionStart())
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Election_not_started).commit();

            else {
                switch (item.getItemId()) {
                    case R.id.contests:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, indexFragment).commit();
                        return true;

                    case R.id.result:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
                        return true;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
                        return true;
                }
            }
            return false;
        });

    }
}