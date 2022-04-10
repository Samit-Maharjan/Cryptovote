package com.example.cryptovote;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOvoter {
    private DatabaseReference databaseReference;
    public DAOvoter(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(voter.class.getSimpleName());
    }
    public Task<Void> add(voter v){
        return databaseReference.push().setValue(v);
    }
}
