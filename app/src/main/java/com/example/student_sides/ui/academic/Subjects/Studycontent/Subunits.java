package com.example.student_sides.ui.academic.Subjects.Studycontent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.staff_navigation.R;
import com.example.student_sides.ui.academic.Subjects.Studycontent.pdf.pdf;
import com.example.student_sides.ui.academic.Subjects.Studycontent.video.video;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class Subunits extends AppCompatActivity {
TextView Cls,Subject,units;
SharedPreferences Sh;
    ListView myPDFListView;
    DatabaseReference databaseReference;
    Button pdff,videoo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subunits);
        Cls=findViewById(R.id.textcl1);
        Subject=findViewById(R.id.textsub1);
        units=findViewById(R.id.textunis1);
        pdff=findViewById(R.id.pdf_subject);
        videoo=findViewById(R.id.video_subject);
        Sh=getApplicationContext().getSharedPreferences("STUDY", MODE_PRIVATE);
        Intent intent=getIntent();
        final String clases=getIntent().getStringExtra("class");
        final String section=getIntent().getStringExtra("section");
        final String subject_select=getIntent().getStringExtra("subject");
        final String Unit=intent.getStringExtra("UNITS");
       // Log.d("abcdefg",clases+" "+sujects);
        Cls.setText(clases+"-"+section);
        Subject.setText(subject_select);
        units.setText(Unit);
        pdff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Subunits.this, pdf.class);
                intent.putExtra("class",clases);
                intent.putExtra("section",section);
                intent.putExtra("subject",subject_select);
                intent.putExtra("UNITS",Unit);
                startActivity(intent);
            }
        });
        videoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Subunits.this, video.class);
                intent.putExtra("class",clases);
                intent.putExtra("section",section);
                intent.putExtra("subject",subject_select);
                intent.putExtra("UNITS",Unit);
                startActivity(intent);
            }
        });
      /*  myPDFListView=findViewById(R.id.myListView);
         uploadPDFs=new ArrayList<>();
        viewAllFiles();
        myPDFListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
    }

    private void viewAllFiles() {
        String classes=Cls.getText().toString();
        String subs=Subject.getText().toString();
        String ut=units.getText().toString();
        databaseReference= FirebaseDatabase.getInstance().getReference("PdfUpload").child(classes).child(subs).child(ut);
      /*  databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot:snapshot.getChildren()){
                 //   uploadPDF uploadPDF=postSnapshot.getValue(uploadPDF.class);
                 //   uploadPDFs.add(uploadPDF);


                }
                String[] uploads=new String[uploadPDFs.size()];
                for(int i=0;i<uploads.length;i++){
                    uploads[i]=uploadPDFs.get(i).getName();

                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view=super.getView(position,convertView,parent);
                        TextView myText=(TextView)view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.WHITE);


                        return view;

                    }
                };
                myPDFListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/



    }
}