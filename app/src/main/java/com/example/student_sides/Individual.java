package com.example.student_sides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**

 */
public class Individual extends Fragment {
    DatabaseReference markref;
    List<String> subject_name=new ArrayList<>();
    List<String> mark_in=new ArrayList<>();
    RecyclerView invi;
    String cls,sec,name;
    TextView student_name;

    public Individual(String cls, String sec, String name) {
        this.cls=cls;
        this.sec=sec;
        this.name=name;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_individual, container, false);
        student_name=root.findViewById(R.id.indivual_student_name);
        student_name.setText("Babu CSE 2021");
        markref= FirebaseDatabase.getInstance().getReference("Staff_Details").child("Academic");
//        String classsss=classs.getSelectedItem().toString();
        markref= markref.child(cls).child("Mark").child(cls+"-"+sec).child(name);
        invi=root.findViewById(R.id.indivual_subject_list);
        invi.setLayoutManager(new LinearLayoutManager(root.getContext()));
        subject_name.clear();
        mark_in.clear();
        getdatamark();
        return root;

    }
    public void getdatamark(){
        markref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot subject : snapshot.getChildren()) {
                    subject_name.add(subject.getKey().toString());
                    mark_in.add(subject.child(name).getValue().toString());
                }

                indivual_student_adapter p = new indivual_student_adapter(subject_name, mark_in);
                invi.setAdapter(p);
            }



            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }

        });

    }
}