package com.example.cryptovote;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class index extends AppCompatActivity {
    BottomNavigationView bottom_nav;
    IndexFragment indexFragment =  new IndexFragment();
    ResultFragment resultFragment = new ResultFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        bottom_nav = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, indexFragment).commit();

        bottom_nav.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
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
            return false;
        });
    }
}