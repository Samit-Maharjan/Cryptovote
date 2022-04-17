package com.example.cryptovote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class adminIndex extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_index);

        findViewById(R.id.indexAddcand).setOnClickListener(this);
        findViewById(R.id.indexViewcand).setOnClickListener(this);
        findViewById(R.id.indexViewvoter).setOnClickListener(this);
        findViewById(R.id.adminLogout).setOnClickListener(this);

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
            case R.id.adminLogout:
                adminlogout();
                break;
        }
    }

    private void adminlogout() {
        FirebaseAuth.getInstance().signOut();
        Intent logout_act = new Intent(getApplicationContext(), log_in.class);
        startActivity(logout_act);
    }
}