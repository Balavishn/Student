package com.example.student_sides;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staff_navigation.R;


class Mark_adapter extends RecyclerView.Adapter<Mark_adapter.MarkView> {
    List<String> student_name;
    Mark_student attendance;
    int click=0;

    public Mark_adapter(List<String> student_name, Mark_student attendance) {
        this.student_name = student_name;
        this.attendance = attendance;
        Log.d("cons","call");
    }

    @NonNull
    @Override
    public MarkView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_mark,parent,false);
        MarkView viewHold=new MarkView(v);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull MarkView holder, int position) {
        Log.d("markname",student_name.get(position));
        holder.name.setText(student_name.get(position));
        attendance.ok.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return student_name.size();
    }

    public class MarkView extends RecyclerView.ViewHolder {
        TextView name;
        EditText mark;
        public MarkView(@NonNull final View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.mark_student_name);
            mark=itemView.findViewById(R.id.enter_mark);
            mark.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    click++;
                    if(!mark.getText().toString().equals("")) {
                        attendance.presentt(name.getText().toString(), student_name.indexOf(name.getText().toString()), mark.getText().toString());
                    }
                    else{
                        Toast.makeText(itemView.getContext(),"Enter Mark",Toast.LENGTH_SHORT).show();
                        attendance.presentt(name.getText().toString(), student_name.indexOf(name.getText().toString()), "0");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (click>=1){
                        attendance.ok.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
