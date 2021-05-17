package com.example.student_sides;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staff_navigation.R;


class study_content_adapter extends RecyclerView.Adapter<study_content_adapter.Mystudy> {
    int count;
    academic_study_content ss;
    String Unit,number;

    public study_content_adapter(int count, Context cx,String unit,String number) {
        this.count = count;
        this.ss=(academic_study_content)cx;
        this.Unit=unit;
        this.number=number;
    }

    @NonNull
    @Override
    public Mystudy onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_study_subhead,parent,false);
        Mystudy viewHold=new Mystudy(v);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull Mystudy holder, int position) {

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class Mystudy extends RecyclerView.ViewHolder{
        EditText editText;
        Button upload;
        public Mystudy(@NonNull final View itemView) {
            super(itemView);
            editText=itemView.findViewById(R.id.subheading);
            upload=itemView.findViewById(R.id.upload_subhead);
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!editText.getText().toString().equals("")) {
                        ss.upload(editText.getText().toString(), Unit, number);
                    }
                    else  Toast.makeText(itemView.getContext(),"Enter Heading Name",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
