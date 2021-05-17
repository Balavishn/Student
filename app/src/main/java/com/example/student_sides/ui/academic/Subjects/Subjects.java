package com.example.student_sides.ui.academic.Subjects;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.staff_navigation.R;
import com.example.student_sides.Mcq;
import com.example.student_sides.academic_homework;
import com.example.student_sides.academic_syllabus;
import com.example.student_sides.ui.academic.Subjects.Studycontent.Unites;
import com.google.firebase.database.DatabaseReference;

import androidx.appcompat.app.AppCompatActivity;

public class Subjects extends AppCompatActivity {
    ImageView Studys,Homework,Syllabuses,Mcqs;
    TextView Subject,classes;
    DatabaseReference reference;
    private SharedPreferences sharedPreferences,sharedPreferences2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        Subject=findViewById(R.id.textsub);
        Studys=findViewById(R.id.Studys);
        Homework=findViewById(R.id.Homeworks);
        Syllabuses=findViewById(R.id.SYllbus);
        classes=findViewById(R.id.textcl);
        Mcqs=findViewById(R.id.Mcq);
        sharedPreferences = getApplicationContext().getSharedPreferences("NUMBER", MODE_PRIVATE);
        final String ids=sharedPreferences.getString("Register","default");
        sharedPreferences2 = getSharedPreferences("STUDY", MODE_PRIVATE);
        final String classss=sharedPreferences2.getString("Classes","default");
        final String section=sharedPreferences2.getString("section","default");
        final String subject_select=getIntent().getStringExtra("subject");
        Subject.setText(subject_select);

    Homework.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(), academic_homework.class);
            intent.putExtra("class",classss);
            intent.putExtra("section",section);
            intent.putExtra("subject",subject_select);
            startActivity(intent);
        }
    });
    Studys.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(), Unites.class);
            intent.putExtra("class",classss);
            intent.putExtra("section",section);
            intent.putExtra("subject",subject_select);
            startActivity(intent);
        }
    });
    Syllabuses.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(), academic_syllabus.class);
            intent.putExtra("class",classss);
            intent.putExtra("section",section);
            intent.putExtra("subject",subject_select);
            startActivity(intent);

        }
    });
    Mcqs.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(), Mcq.class);
            intent.putExtra("class",classss);
            intent.putExtra("section",section);
            intent.putExtra("subject",subject_select);
            startActivity(intent);

        }
    });
    }
}