package com.example.student_sides.ui.academic.Subjects.Studycontent.pdf;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import com.example.staff_navigation.R;
import com.example.student_sides.ui.academic.Subjects.Studycontent.pdf_video_adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class pdf extends AppCompatActivity {

    RecyclerView pdf;
    DatabaseReference databaseReference;
    List<String> name,url;
    String clases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdf=findViewById(R.id.pdf_rec);
        pdf.setLayoutManager(new LinearLayoutManager(this));
        Intent intent=getIntent();
        name=new ArrayList<>();
        url=new ArrayList<>();
        clases=getIntent().getStringExtra("class");
        final String section=getIntent().getStringExtra("section");
        final String subject_select=getIntent().getStringExtra("subject");
        clases="1";
        final String Unit=intent.getStringExtra("UNITS");
        Log.d("abcdses",clases+"-"+section+" "+subject_select+" "+Unit);
        databaseReference= FirebaseDatabase.getInstance().getReference("PdfUpload").child(clases+"-"+section).child(subject_select).child(Unit);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s:snapshot.getChildren()){
                    Log.d("refrence",s.getRef().toString());
                    for(DataSnapshot m:s.getChildren()) {
                        Log.d("refrence",m.child("name").getValue().toString());
                        name.add(m.child("name").getValue().toString());
                        url.add(m.child("url").getValue().toString());
                    }
                }
                pdf_video_adapter pdf_video_adapter= new pdf_video_adapter(pdf.this,name,url);
                pdf.setAdapter(pdf_video_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void download(Uri url){

    }
}