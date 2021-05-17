package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.staff_navigation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class academic_syllabus extends AppCompatActivity {
    String clases,section,sub;

    DatabaseReference databaseReference;
    TextView name;
    ImageView download;
    Uri url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_syllabus);
        name=findViewById(R.id.SYllbus_name);
        download=findViewById(R.id.download_syllabus);
        clases=getIntent().getStringExtra("class");
        section=getIntent().getStringExtra("section");
        sub=getIntent().getStringExtra("subject");
        databaseReference= FirebaseDatabase.getInstance().getReference("Staff_Details").child("Academic").child("syllabus").child(clases+"-"+section).child(sub);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                url=Uri.parse(snapshot.child("url").getValue().toString());
                name.setText(snapshot.child("name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager dm=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                Uri urll= url;
                DownloadManager.Request request= new DownloadManager.Request(urll);

                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalFilesDir(getApplicationContext(),DIRECTORY_DOWNLOADS,name.getText().toString()+".pdf");

                dm.enqueue(request);
            }
        });



    }

}
