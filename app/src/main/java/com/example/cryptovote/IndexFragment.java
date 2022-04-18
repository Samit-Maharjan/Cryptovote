package com.example.cryptovote;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IndexFragment extends Fragment implements candidateAdapter.CandidateListener {
    RecyclerView recyclerView;
    DatabaseReference database;
    private FirebaseUser user;
    String userID;
    candidateAdapter myAdapter;
    ArrayList<candidate> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =  inflater.inflate(R.layout.fragment_index, container, false);

        recyclerView = view.findViewById(R.id.recycler1);
        database = FirebaseDatabase.getInstance().getReference("candidate");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        myAdapter = new candidateAdapter(getContext(),list, this);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    candidate user = dataSnapshot.getValue(candidate.class);
                    list.add(user);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    @Override
    public void CandidateClick(String s, String i) {

        Intent intent = new Intent(getActivity(),vote.class);
        Bundle bundle = new Bundle();

        bundle.putString("name",s);
        bundle.putString("ID",i);
        intent.putExtras(bundle);
        startActivity(intent);

        /*user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance().getReference();
        userID = user.getUid();*/
       //Blockchain blockchain = new Blockchain(position + 1, userID);

    }
}
