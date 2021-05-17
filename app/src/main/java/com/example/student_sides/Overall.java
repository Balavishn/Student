package com.example.student_sides;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;


import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Overall extends Fragment {

    Button get;
    DatabaseReference markref;
    List<Student> student_name=new ArrayList<>();
    String cls,secs,names;
    // List<Integer> student_mark=new ArrayList<>();

    RecyclerView permance_rank;
    String[] name=new String[3];
    String[] mark=new String[3];

    public Overall(String cls, String sec, String name) {
        this.cls=cls;
        this.secs=sec;
        this.names=name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_overall, container, false);
        markref= FirebaseDatabase.getInstance().getReference("Staff_Details").child("Academic");
        permance_rank=v.findViewById(R.id.overall_performance_mark);
        permance_rank.setLayoutManager(new LinearLayoutManager(v.getContext()));
        markref= FirebaseDatabase.getInstance().getReference("Staff_Details").child("Academic");
        markref= markref.child(cls).child("Mark");

      getdatamark();
               /* for (int i=0;i<student_name.size();i++){
                    Log.d("student-name-mark",student_name.get(i)+"-"+student_mark.get(i));
                }*/

        return v;
    }
    public void getdatamark(){
        markref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot class_sec : snapshot.getChildren()) {
                    DataSnapshot classss=class_sec;
                    Log.d("vluesss",classss.toString());
                    for (DataSnapshot data : classss.getChildren()) {
                        // student_name.add(data.getKey());
                        DataSnapshot student = data;
                        int total = 0;
                        int count = 0;
                        for (DataSnapshot mark : student.getChildren()) {
                            total += Integer.parseInt(mark.child(data.getKey()).getValue().toString());
                            count += 1;
                            if (student.getChildrenCount() == count) {
                                //student_mark.add(total);
                                student_name.add(new Student(data.getKey(), total));
                            }

                            Log.d("student m", mark.child(data.getKey()).getValue().toString());

                        }
                    }
                    Collections.sort(student_name, Collections.<Student>reverseOrder());
                    int pos = 0;
                    for (Student s : student_name) {
                        try {
                            name[pos] = s.getName();
                            mark[pos] = String.valueOf(s.getGrade());
                            pos++;
                            Log.d("student-name-mark", s.getName() + " " + String.valueOf(s.getGrade()) + student_name.indexOf(s));
                        }
                        catch (Exception e){
                            break;
                        }

                    }
                    Performance_adapter p = new Performance_adapter(name, mark);
                    permance_rank.setAdapter(p);
                }

            }

            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }

        });

    }

}