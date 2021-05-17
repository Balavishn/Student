package com.example.student_sides;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.staff_navigation.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class indivual_student_adapter extends RecyclerView.Adapter<indivual_student_adapter.indivualview> {
    List<String> sub,mark;

    public indivual_student_adapter(List<String> subject_name, List<String> mark_in) {
        this.sub=subject_name;
        mark=mark_in;
    }

    @NonNull
    @Override
    public indivualview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.indivi_single_sub,null);
        indivualview viewHold=new indivualview(v);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull indivualview holder, int position) {
        holder.name.setText(sub.get(position));
        holder.percent.setText(mark.get(position)+"%");
        holder.circularProgressBar.setProgressWithAnimation(Float.parseFloat(mark.get(position)),(long)2000);
    }

    @Override
    public int getItemCount() {
        return sub.size();
    }

    public class indivualview extends RecyclerView.ViewHolder {
        TextView name,percent;
        CircularProgressBar circularProgressBar;
        public indivualview(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.subject_singlee_mark);
            percent=itemView.findViewById(R.id.percent_mark);
            circularProgressBar=itemView.findViewById(R.id.circularProgressBar_sub);

        }
    }
}
