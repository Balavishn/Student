package com.example.student_sides.ui.academic.Subjects.Studycontent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.staff_navigation.R;
import com.google.firebase.database.DatabaseReference;

import androidx.appcompat.app.AppCompatActivity;

public class Unites extends AppCompatActivity {
    TextView Unit1,Unit2,Unit3,Unit4,Unit5;
    DatabaseReference reference;
    TextView t1,t2;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unites);
        t1=findViewById(R.id.textsub2);
        t2=findViewById(R.id.textcl2);

        final String classss=getIntent().getStringExtra("class");
        final String section=getIntent().getStringExtra("section");
        final String subject_select=getIntent().getStringExtra("subject");
      //  sharedPreferences=getApplicationContext().getSharedPreferences("STUDY", MODE_PRIVATE);
      //  final String clases=sharedPreferences.getString("Classes","default");
      //  final String sujects=sharedPreferences.getString("Subjects","default");
      //  Log.d("abcdefg",clases+" "+sujects);
        t2.setText(classss+"-"+section);
        t1.setText(subject_select);
        Unit1=findViewById(R.id.unit1);
        Unit2=findViewById(R.id.unit2);
        Unit3=findViewById(R.id.unit3);
        Unit4=findViewById(R.id.unit4);
        Unit5=findViewById(R.id.unit5);
        Unit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(Unites.this,Subunits.class);
                intent.putExtra("class",classss);
                intent.putExtra("section",section);
                intent.putExtra("subject",subject_select);
                    intent.putExtra("UNITS","Unit1");
                    startActivity(intent);
            }
        });
        Unit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Unites.this,Subunits.class);
                intent.putExtra("UNITS","Unit2");
                intent.putExtra("class",classss);
                intent.putExtra("section",section);
                intent.putExtra("subject",subject_select);
                startActivity(intent);
            }
        });

        Unit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Unites.this,Subunits.class);
                intent.putExtra("UNITS","Unit3");
                intent.putExtra("class",classss);
                intent.putExtra("section",section);
                intent.putExtra("subject",subject_select);
                startActivity(intent);
            }
        });

        Unit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Unites.this,Subunits.class);
                intent.putExtra("UNITS","Unit4");
                intent.putExtra("class",classss);
                intent.putExtra("section",section);
                intent.putExtra("subject",subject_select);
                startActivity(intent);
            }
        });

        Unit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Unites.this,Subunits.class);
                intent.putExtra("UNITS","Unit5");
                intent.putExtra("class",classss);
                intent.putExtra("section",section);
                intent.putExtra("subject",subject_select);
                startActivity(intent);
            }
        });




    }
}