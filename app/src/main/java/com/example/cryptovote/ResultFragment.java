package com.example.cryptovote;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class ResultFragment extends Fragment{
    int totalCandidates;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        try {
            voterResultAdapter cad = new voterResultAdapter(getContext(), new ArrayList<String>(), new ArrayList<Integer>());
            final ListView candidateView = view.findViewById(R.id.records_view);

            Blockchain blockchain = new Blockchain();

            //give me from Database

            FirebaseDatabase.getInstance().getReference("candidate").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        totalCandidates = (int) snapshot.getChildrenCount();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            ArrayList<Integer> voteCount = new ArrayList<>();
            ArrayList<String> candidates = new ArrayList<>();

            for (int i = 1; i <= totalCandidates; ++i) {
                try {
                    voteCount.add(blockchain.GetCandidate(i));
                    candidates.add(blockchain.GetCandidateName(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Map<String, Integer> records = new LinkedHashMap<>();
            for (int i = 0; i < totalCandidates; ++i)
                records.put(candidates.get(i), voteCount.get(i));

            Set<Map.Entry<String, Integer>> candidateSet = records.entrySet();

            List<Map.Entry<String, Integer>> candidateListEntry = new ArrayList<Map.Entry<String, Integer>>(candidateSet);

            Collections.sort(candidateListEntry,
                    new Comparator<Map.Entry<String, Integer>>() {
                        @Override
                        public int compare(Entry<String, Integer> es1,
                                           Entry<String, Integer> es2) {
                            return es2.getValue().compareTo(es1.getValue());
                        }
                    });

            records.clear();

            for (Map.Entry<String, Integer> map : candidateListEntry) {
                records.put(map.getKey(), map.getValue());
            }

            for (Map.Entry<String, Integer> entry : records.entrySet()) {
                String name = entry.getKey();
                int votes = entry.getValue();
                cad.add(name, votes);
            }
        /*
        TreeMap<Integer, String> records = new TreeMap<>();
        for(int i = 0; i < totalCandidates; ++i)
            records.put(voteCount.get(i), candidates.get(i));

        for(Map.Entry<Integer, String> entry: records.entrySet()){
            String name = entry.getValue();
            int votes = entry.getKey();
            cad.add(name,votes);
        }
         */
            candidateView.setAdapter(cad);
            cad.notifyDataSetChanged();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return view;
    }
}
