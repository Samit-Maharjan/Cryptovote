package com.example.cryptovote;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;


public class candidateAdapter extends RecyclerView.Adapter<candidateAdapter.MyViewHolder> {

    Context context;
    ArrayList<candidate> list;
    private CandidateListener mCandidateListener;

    public candidateAdapter(Context context, ArrayList<candidate> list, CandidateListener candidateListener) {
        this.context = context;
        this.list = list;
        this.mCandidateListener = candidateListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.candidate,parent,false);
        return  new MyViewHolder(v, mCandidateListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        candidate cand = list.get(position);
        holder.Name.setText(cand.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Name;
        CandidateListener candidateListener;

        public MyViewHolder(@NonNull View itemView, CandidateListener candidateListener) {
            super(itemView);
            Name = itemView.findViewById(R.id.Candidatename);
            this.candidateListener = candidateListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            candidate c = list.get(getAdapterPosition());
            String s = c.getName();
            String i = c.getID();
            int bId = c.getbID();
            candidateListener.CandidateClick(s,i,bId);
        }
    }

    public interface CandidateListener{
        void CandidateClick(String s,String i,int bID);
    }
}