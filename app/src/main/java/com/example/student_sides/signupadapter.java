package com.example.student_sides;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staff_navigation.R;


public class signupadapter extends RecyclerView.Adapter<signupadapter.Myview> {
    private  String[] classdata;
    private  String[] secdata;
    private  String[] subjdata;
    Context cx;
    int count;
    Myview my;
    // SignUpStaffSide signUpStaffSide;
    public signupadapter(Context c,int count) {
        cx=c;
        // signUpStaffSide=(SignUpStaffSide)c;
        this.count=count;
        this.classdata=new String[count];
        this.secdata=new String[count];
        this.subjdata=new String[count];
    }

    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.spinners,parent,false);
        Myview viewHold=new Myview(v);
        return viewHold;
    }

    @Override
    public void onBindViewHolder(@NonNull final Myview holder, final int position) {
        my=holder;
        holder.class_next.setAdapter(holder.class_arrayAdapter);
        holder.section.setAdapter(holder.sec_arrayAdapter);
        holder.subject_select.setAdapter(holder.sub_arrayAdapter);
        classdata[position]=holder.class_next.getSelectedItem().toString();
        secdata[position]=holder.section.getSelectedItem().toString();
        secdata[position]=holder.subject_select.getSelectedItem().toString();
        if(position==count-1){
            //   signUpStaffSide.register.setVisibility(View.VISIBLE);
        }
        holder.class_next.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {

                    classdata[position]=holder.class_next.getSelectedItem().toString();
                    Log.d("spinner","called"+" "+holder.class_next.getSelectedItem().toString());
                    if (holder.class_next.getSelectedItem().toString().equals("11")||holder.class_next.getSelectedItem().toString().equals("12")){
                        holder.subject_select.setAdapter(holder.sub_arrayAdapter2);
                    }
                }
                catch (Exception e){
                    Log.d("spinnercatch","called"+" "+holder.class_next.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                secdata[position]=holder.section.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.subject_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subjdata[position]=holder.subject_select.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class Myview extends RecyclerView.ViewHolder {
        View v;
        Spinner class_next,section,subject_select;
        String[] class_list={"1","2","3","4","5","6","7","8","9","10","11","12"};
        String[] section_list={"A","B","C"};
        String[] subject_list_10={"Tamil","English","Maths","Science","Social Science"};
        String[] subject_list_12={"Tamil","English","Maths","Biology","Computer Science"};
        ArrayAdapter<String> class_arrayAdapter;
        ArrayAdapter<String> sec_arrayAdapter;
        ArrayAdapter<String> sub_arrayAdapter;
        ArrayAdapter<String> sub_arrayAdapter2;


        public Myview(@NonNull View itemView) {
            super(itemView);
            class_next=itemView.findViewById(R.id.class_next);
            subject_select = itemView.findViewById(R.id.subject_select);
            section = itemView.findViewById(R.id.section);
            class_arrayAdapter=new ArrayAdapter<>(itemView.getContext(),R.layout.spinner_dropdown_textview,class_list);
            sec_arrayAdapter=new ArrayAdapter<>(itemView.getContext(),R.layout.spinner_dropdown_textview,section_list);
            sub_arrayAdapter=new ArrayAdapter<>(itemView.getContext(),R.layout.spinner_dropdown_textview,subject_list_10);
            sub_arrayAdapter2=new ArrayAdapter<>(itemView.getContext(),R.layout.spinner_dropdown_textview,subject_list_12);
        }
    }
    public String[] getclassdata(){
        return classdata;
    }
    public String[] getSecdata(){
        return secdata;
    }
    public String[] getSubjdata(){
        return subjdata;
    }
}
