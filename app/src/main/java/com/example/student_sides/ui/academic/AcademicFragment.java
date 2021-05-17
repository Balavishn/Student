package com.example.student_sides.ui.academic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.example.staff_navigation.R;
import com.example.student_sides.*;
import com.example.student_sides.ui.academic.Subjects.Select_subject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class AcademicFragment extends Fragment {
    ImageView Study,performance,timetable,attendence;
    SharedPreferences sharedPreferences,sharedPreferences2;
    DatabaseReference reference;
    View root;

    String classss,sec,name;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_academic, container, false);
        Study=root.findViewById(R.id.SC);
        performance=root.findViewById(R.id.PA);
        timetable=root.findViewById(R.id.Times);
        attendence=root.findViewById(R.id.Attend);
        sharedPreferences = root.getContext().getSharedPreferences("NUMBER", MODE_PRIVATE);
        final String ids=sharedPreferences.getString("Register","default");
        reference = FirebaseDatabase.getInstance().getReference("STUDENT DETAILS");
        reference.child(ids).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                classss = String.valueOf(dataSnapshot.child("Student_Class").getValue());
                sec=String.valueOf(dataSnapshot.child("Student_Section").getValue());
                name=String.valueOf(dataSnapshot.child("Student_Name").getValue());
                sharedPreferences2 = root.getContext().getSharedPreferences("STUDY", MODE_PRIVATE);
                SharedPreferences.Editor editor2=sharedPreferences2.edit();
                // editor2.putString("Subjects",Subject.getText().toString());
                editor2.putString("Classes",classss);
                editor2.putString("section",sec);
                editor2.commit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Select_subject.class);
                startActivity(intent);
            }
        });
        performance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Perforamce_tab.class);
                intent.putExtra("class",classss);
                intent.putExtra("section",sec);
                intent.putExtra("namee",name);
                startActivity(intent);
            }
        });
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), academic_timetable.class);
                intent.putExtra("class",classss);
                intent.putExtra("section",sec);
                intent.putExtra("namee",name);
                startActivity(intent);
            }
        });
        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Individual_attendance.class);
                intent.putExtra("class",classss);
                intent.putExtra("section",sec);
                intent.putExtra("namee",name);
                startActivity(intent);
            }
        });
        return root;
    }

}