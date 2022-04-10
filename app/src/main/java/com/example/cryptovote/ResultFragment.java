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

public class ResultFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        viewResult(view);
        return view;
    }
    void viewResult(View v){
        TableLayout stk = (TableLayout) v.findViewById(R.id.tableResult);
        TableRow tbrow0 = new TableRow(getContext());


        TextView tx0 = new TextView(getContext());
        tx0.setText("Name");

        tx0.setTextColor(Color.BLACK);
        tbrow0.addView(tx0);

        stk.addView(tbrow0);
    }
}
