package com.example.student_sides.ui.event;



//import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.staff_navigation.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class CustomAdapterEvent extends RecyclerView.Adapter<CustomAdapterEvent.MyViewHolder> {
    private ArrayList<ModelClassEvent> listData;

    public CustomAdapterEvent(ArrayList<ModelClassEvent> listData) {
        this.listData = listData;
    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_list_event, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelClassEvent ld=listData.get(position);
        holder.date1.setText(ld.getDate());
        holder.title.setText(ld.getTitle());
        holder.time.setText(ld.getTime());
        holder.desc.setText(ld.getDescription());
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView date1,title,time,desc;
        public MyViewHolder(View itemView) {
            super(itemView);
            date1=itemView.findViewById(R.id.date);
            title=itemView.findViewById(R.id.Title);
            time=itemView.findViewById(R.id.Time);
            desc=itemView.findViewById(R.id.Desc);



        }
    }



}