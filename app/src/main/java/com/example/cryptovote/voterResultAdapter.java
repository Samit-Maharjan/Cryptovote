package com.example.cryptovote;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class voterResultAdapter extends BaseAdapter {
    private Context candidateContext;
    private List<String> candidateList;
    private List<Integer> voteCount;

    public voterResultAdapter(Context candidateContext, List<String> candidateList, List<Integer> voteCount) {
        this.candidateContext = candidateContext;
        this.candidateList = candidateList;
        this.voteCount = voteCount;
    }


    @Override
    public int getCount() {
        return candidateList.size();
    }

    @Override
    public String getItem(int i) {
        return candidateList.get(i);
    }

    public int getvoteCount(int i){
        return voteCount.get(i);
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
            view = inflater.inflate(R.layout.votercandidate, null);

            holder = new candidateViewHolder();
            holder.nameView = (TextView) view.findViewById(R.id.resultCandName);
            holder.voteView = view.findViewById(R.id.resultCandVote);
            view.setTag(holder);
        }
        else{
            holder = (candidateViewHolder) view.getTag();
        }

        String name = getItem(i);
        String count = Integer.toString(getvoteCount(i));
        holder.nameView.setText(name);
        holder.voteView.setText(count);

        return view;
    }

    public void add(String c, int count){
        candidateList.add(c);
        voteCount.add(count);
    }
}
