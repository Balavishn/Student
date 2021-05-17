package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class academic_homework extends AppCompatActivity{

    RecyclerView pdf;
    DatabaseReference databaseReference;
    List<String> name,url;
    String clases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_homework);

        pdf=findViewById(R.id.homework_rec);
        pdf.setLayoutManager(new LinearLayoutManager(this));
        Intent intent=getIntent();
        name=new ArrayList<>();
        url=new ArrayList<>();
        clases=getIntent().getStringExtra("class");
        final String section=getIntent().getStringExtra("section");
        final String subject_select=getIntent().getStringExtra("subject");
        clases="1";
        // final String Unit=intent.getStringExtra("UNITS");
        Log.d("abcdses",clases+"-"+section+" "+subject_select);
        databaseReference= FirebaseDatabase.getInstance().getReference("Staff_Details").child("Academic").child("Homework").child(clases+"-"+section).child(subject_select);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s:snapshot.getChildren()){
                    Log.d("refrence",s.getRef().toString());
                    Log.d("refrence",s.child("name").getValue().toString());
                    name.add(s.child("name").getValue().toString());
                    url.add(s.child("url").getValue().toString());
                }
                pdf_adapter pdf_video_adapter= new pdf_adapter(name,url);
                pdf.setAdapter(pdf_video_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
