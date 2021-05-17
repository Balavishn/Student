package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Performance_Analaysis extends AppCompatActivity {
    DatabaseReference markref;
    List<String> student_name=new ArrayList<>();
    List<Integer> marks=new ArrayList<>();
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance__analaysis);
        String classs=getIntent().getStringExtra("class");
        markref= FirebaseDatabase.getInstance().getReference("Staff_Details").child("Academic").child(classs).child("Mark");
        t1=findViewById(R.id.Student_1);
        t2=findViewById(R.id.student_2);
        t3=findViewById(R.id.student_3);
        markref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount()==0) {
                    Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
                    t1.setText("No data");
                    t2.setVisibility(View.GONE);
                    t3.setVisibility(View.GONE);
                }
                else {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        student_name.add(data.getKey());
                        DataSnapshot student = data;
                        int total = 0;
                        int count = 0;
                        for (DataSnapshot mark : student.getChildren()) {
                            total += Integer.parseInt(mark.child(data.getKey()).getValue().toString());
                            count += 1;
                            if (student.getChildrenCount() == count) {
                                marks.add(total);
                            }

                            Log.d("student", mark.child(data.getKey()).getValue().toString());
                        }

                    }
                /*int max1,max2,max3;
                List<Integer>dm=marks;
                for(int i=0;i<3;i++){
                    Collections.sort(dm);
                    max1=marks.indexOf(dm.get(dm.size()-1));
                    dm.remove(dm.size()-1);
                }*/
                    t1.setText(student_name.get(0).toString() + " " + marks.get(0).toString());
                    t2.setText(student_name.get(1) + " " + marks.get(1).toString());
                    t3.setText(student_name.get(2) + " " + marks.get(2).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}