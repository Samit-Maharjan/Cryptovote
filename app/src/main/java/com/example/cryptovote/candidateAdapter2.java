package com.example.cryptovote;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class candidateAdapter2 extends BaseAdapter {
    private Context candidateContext;
    private List<candidate> candidateList;

    public candidateAdapter2(Context candidateContext, List<candidate> candidateList) {
        this.candidateContext = candidateContext;
        this.candidateList = candidateList;
    }

    @Override
    public int getCount() {
        return candidateList.size();
    }

    @Override
    public Object getItem(int i) {
        return candidateList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        candidateViewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)
                    candidateContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.admincandidate, null);

            holder = new candidateViewHolder();
            holder.nameView = (TextView) view.findViewById(R.id.candidate_name);
            holder.voteView = view.findViewById(R.id.candidate_votes);
            view.setTag(holder);
        }
        else{
            holder = (candidateViewHolder) view.getTag();
        }

        candidate cand = (candidate) getItem(i);
        holder.nameView.setText(cand.getName());
        holder.voteView.setText(cand.getID());

        return view;
    }

    public void add(candidate cand){
        candidateList.add(cand);
    }
}
