package com.example.cryptovote;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ResultFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        int count = 3;
        Blockchain blockchain = new Blockchain();
        TextView txt1 = (TextView) getView().findViewById(R.id.resulttxt1);

        // Extract name and votes from Blockchain
        Blockchain blockchain1 = new Blockchain();

        int totalCandidates = ;//give me from Database
        ArrayList<Integer> voteCount = new ArrayList<>();
        ArrayList<String>  candidates = new ArrayList<>();

        for(int i = 0; i < totalCandidates; ++i){
            try {
                voteCount.add(blockchain.GetCandidate(i) );
                candidates.add(blockchain.GetCandidateName(i) );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        TreeMap<Integer, String> records = new TreeMap<>();
        for(int i = 0; i < totalCandidates; ++i)
            records.put(voteCount.get(i), candidates.get(i));

        for(Map.Entry<Integer, String> entry: records.entrySet()){
            String name = entry.getValue();
            int votes = entry.getKey();
        }
        return view;
    }
//    void viewResult(View v){
//        TableLayout stk = (TableLayout) v.findViewById(R.id.tableResult);
//        TableRow tbrow0 = new TableRow(getContext());
//
//
//        TextView tx0 = new TextView(getContext());
//        tx0.setText("Name");
//
//        tx0.setTextColor(Color.BLACK);
//        tbrow0.addView(tx0);
//
//        stk.addView(tbrow0);
//    }
}
