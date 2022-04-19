package com.example.cryptovote;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class voterAdapter extends BaseAdapter {
    private Context context;
    private List<voterReg> list;
    private List<String> hasVoted;

    public voterAdapter(Context context, List<voterReg> list, List<String> hasVoted) {
        this.context = context;
        this.list = list;
        this.hasVoted = hasVoted;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public String getState(int i){
        return hasVoted.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        voterViewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adminvoter, null);

            holder = new voterViewHolder();
            holder.nameView = (TextView) view.findViewById(R.id.voter_name);
            holder.voteView = (TextView) view.findViewById(R.id.voter_vote);
            view.setTag(holder);
        }
        else{
            holder = (voterViewHolder) view.getTag();
        }

        voterReg voter = (voterReg) getItem(i);
        String state = (String) getState(i);
        String name = voter.getFname()+" "+voter.getLname();
        holder.nameView.setText(name);
        holder.voteView.setText(state);

        return view;
    }

    public void add(voterReg voter,String s){
        list.add(voter);
        hasVoted.add(s);
    }
}

