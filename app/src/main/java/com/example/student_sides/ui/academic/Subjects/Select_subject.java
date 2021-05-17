package com.example.student_sides.ui.academic.Subjects;

import android.content.Intent;
import android.os.Bundle;


import com.example.staff_navigation.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Select_subject extends AppCompatActivity {
    String[] below_10={"Tamil","English","Maths","Science","Social Science"};
    String[] above_10={"Tamil","English","Maths","chemistry","Computer Science","Biology"};
    RecyclerView subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_content);
        subject=findViewById(R.id.subject_rec);
        subject.setLayoutManager(new GridLayoutManager(this,2));
        subject_adapter sub=new subject_adapter(Select_subject.this,below_10);
        subject.setAdapter(sub);




/*
        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Subjects.class);
                intent.putExtra("Tamil",tam.getText().toString());
                Toast.makeText(getApplicationContext(),"Tamil",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Subjects.class);
                intent.putExtra("Tamil",eng.getText().toString());
                Toast.makeText(getApplicationContext(),"English",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Subjects.class);
                intent.putExtra("Tamil",sc.getText().toString());
                startActivity(intent);
            }
        });
        social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Subjects.class);
                intent.putExtra("Tamil",ss.getText().toString());
                startActivity(intent);
            }
        });
        maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Subjects.class);
                intent.putExtra("Tamil",mat.getText().toString());
                startActivity(intent);
            }
        });
        computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Subjects.class);
                intent.putExtra("Tamil",cs.getText().toString());
                startActivity(intent);
            }
        });*/

    }

    public void gooto(String subjectt){
        Intent intent=new Intent(getApplicationContext(), Subjects.class);
        intent.putExtra("subject",subjectt);
        startActivity(intent);
    }
}