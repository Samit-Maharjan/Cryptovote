package com.example.cryptovote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add_candidate extends AppCompatActivity implements View.OnClickListener{
    EditText name,candid;
    int m;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);

        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.candName);
        candid = findViewById(R.id.candidateID);

        findViewById(R.id.addCandBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.addCandBtn:
                try {
                    addCandidate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void addCandidate() throws Exception {
        String candName = name.getText().toString();
        String ID = candid.getText().toString();

        if(candName.isEmpty()){
            name.setError("Please provide candidate name");
            name.requestFocus();
            return;
        }
        if(ID.isEmpty()){
            candid.setError("Please provide candidate ID");
            candid.requestFocus();
            return;
        }

        DatabaseReference databaseref = FirebaseDatabase.getInstance().getReference("candidate");

        databaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot s) {
                if(s.exists()) {
                    m = (int) s.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseref.orderByChild("id").equalTo(ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Toast.makeText(add_candidate.this, "Candidate ID already exists", Toast.LENGTH_SHORT).show();
                }
                else{
                    candidate cand = new candidate(candName, ID, m);
                    FirebaseDatabase.getInstance().getReference().child("candidate")
                            .child(ID).setValue(cand).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(add_candidate.this, "Candidate Added Successfully", Toast.LENGTH_SHORT).show();
                                Intent register_act = new Intent(getApplicationContext(), adminIndex.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("name", candName);
                                register_act.putExtras(bundle);
                                startActivity(register_act);
                            }
                            else{
                                Toast.makeText(add_candidate.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}