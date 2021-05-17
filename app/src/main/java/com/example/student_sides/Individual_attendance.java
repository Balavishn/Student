package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Individual_attendance extends AppCompatActivity {

    String name,cls,sec;
    DatabaseReference markref;
    int present_count=0,absent_count=0;
    String percentage_string;

    TextView percentage_view,absent_view;
    ListView list_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_attendance);

        cls=getIntent().getStringExtra("class");
        sec=getIntent().getStringExtra("section");
        name=getIntent().getStringExtra("namee");
        percentage_view=findViewById(R.id.Percentage);
        absent_view=findViewById(R.id.absent_count);

        Log.d("details",name+cls+sec);

       // name="Babu CSE 2021";

        markref= FirebaseDatabase.getInstance().getReference("Staff_Details").child("Academic").child(cls).child("Attendance").child(cls+"-"+sec).child(name);

        markref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot date) {
                int count=0;
                for (DataSnapshot present:date.getChildren()){
                    Log.d("Date refer",date.getRef().toString());
                    if(present.child(name).getValue().toString().equals("present")){
                        //  present_count += Integer.parseInt(present.child(present.getKey()).getValue().toString());
                        Log.d("present",present.child(date.getKey()).getValue().toString());
                        present_count+=1;
                    }
                    else{
                        //  absent_count += Integer.parseInt(present.child(present.getKey()).getValue().toString());
                        Log.d("absent",present.child(date.getKey()).getValue().toString());
                        absent_count+=1;
                    }

                    count += 1;
                    if (date.getChildrenCount() == count) {
                        //student_mark.add(total);
                        //student_name.add(new Student(present.getKey(),present_count));
                        Double tpresent_count=Double.valueOf(present_count);
                        double percentage=((double) tpresent_count/date.getChildrenCount())*100;
                        Log.d("totalpresent",String.format("%.2f",percentage));
                        /*student_name.add(date.getKey());*/
                        percentage_string=String.format("%.2f",percentage);
                        percentage_view.setText(percentage_string);
                        absent_view.setText(String.valueOf(absent_count));
                    }

                    Log.d("student m", present.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}