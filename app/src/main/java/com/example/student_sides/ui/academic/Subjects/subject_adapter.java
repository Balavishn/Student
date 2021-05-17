package com.example.student_sides.ui.academic.Subjects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.staff_navigation.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class subject_adapter extends RecyclerView.Adapter<subject_adapter.mysubject> {
    String[] below_10,above_10;
    Select_subject selectsubject;
    public subject_adapter(Context cx, String[] below_10) {
        this.below_10=below_10;
        //this.above_10=above_10;
        selectsubject =(Select_subject)cx;
    }

    @NonNull
    @Override
    public mysubject onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_subject,parent,false);
        mysubject s= new mysubject(v);
        return s;
    }

    @Override
    public void onBindViewHolder(@NonNull mysubject holder, int position) {
        holder.subject.setText(below_10[position]);
    }

    @Override
    public int getItemCount() {
        return below_10.length;
    }

    public class mysubject extends RecyclerView.ViewHolder{
        TextView subject;
        CardView sub_select;
        public mysubject(@NonNull View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.single_subject);
            sub_select=itemView.findViewById(R.id.sub_select);
            sub_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectsubject.gooto(subject.getText().toString());
                }
            });
        }
    }
}
