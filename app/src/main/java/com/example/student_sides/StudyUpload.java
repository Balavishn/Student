package com.example.student_sides;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import com.example.staff_navigation.R;


public class StudyUpload extends AppCompatActivity {
    String unit,clssec,sub,subheading,heading,unitname;
    TextView cls,subject,units,subhead,unitheading;
    Button pdf,video;

    @Override
    protected void onStart() {
        super.onStart();
        cls.setText(clssec);
        subject.setText(sub);
        units.setText(unit);
        subhead.setText(subheading);
        unitheading.setText(unitname);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_upload);
        cls=findViewById(R.id.clssec);
        subject=findViewById(R.id.sub);

        units=findViewById(R.id.unit);
        subhead=findViewById(R.id.subheading);
        pdf=findViewById(R.id.pdf);
        video=findViewById(R.id.video);
        unitheading=findViewById(R.id.unitheading);


        Intent intent1=getIntent();
        unit=intent1.getStringExtra("Unit");
        clssec=intent1.getStringExtra("ClassSec");
        sub=intent1.getStringExtra("Subject");
        subheading=intent1.getStringExtra("SubHeading");
        unitname=intent1.getStringExtra("UnitName");

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PdfUpload.class);
                intent.putExtra("Unit",unit);
                intent.putExtra("ClassSec",clssec);
                intent.putExtra("Subject",sub);
                intent.putExtra("SubHeading",subheading);
                intent.putExtra("UnitName",unitname);
                startActivity(intent);
            }
        });


        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),VideoUpload.class);
                intent.putExtra("Unit",unit);
                intent.putExtra("ClassSec",clssec);
                intent.putExtra("Subject",sub);
                intent.putExtra("SubHeading",subheading);
                intent.putExtra("UnitName",unitname);
                startActivity(intent);
            }
        });


     }


}