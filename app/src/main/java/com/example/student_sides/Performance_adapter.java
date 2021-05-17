package com.example.student_sides;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staff_navigation.R;


class Performance_adapter extends RecyclerView.Adapter<Performance_adapter.myperformance> {
    String[] name;
    String[]  no;

    public Performance_adapter(String[] name, String[] no) {
        this.name = name;
        this.no = no;
    }

    @NonNull
    @Override
    public myperformance onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_leaderboard,parent,false);
        myperformance viewHold=new myperformance(v);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull myperformance holder, int position) {
        holder.rank.setText(no[position]);
        holder.name.setText(String.valueOf(position+1)+"."+name[position]);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class myperformance extends RecyclerView.ViewHolder{
        TextView rank,name;
        public myperformance(@NonNull View itemView) {
            super(itemView);
            rank=itemView.findViewById(R.id.single_rank_no);
            name=itemView.findViewById(R.id.single_rank_name);
        }
    }
}
