package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.staff_navigation.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class academic_timetable extends AppCompatActivity {

   // PDFView pdfView;
    String clases,section;

    DatabaseReference databaseReference;
    TextView name;
    ImageView download;
    Uri url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_timetable);
        name=findViewById(R.id.timetable_name);
        download=findViewById(R.id.download_timetable);
        clases=getIntent().getStringExtra("class");
        section=getIntent().getStringExtra("section");
        databaseReference= FirebaseDatabase.getInstance().getReference("Staff_Details").child("Academic").child("Timetable").child(clases+"-"+section);
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
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {
                File direct=new File(Environment.getExternalStorageDirectory()+"/Staff_navigation/","babu.txt");
                if(!direct.exists()){
                    direct.mkdir();

                    Log.d("pathh",String.valueOf(direct.mkdir()));
                    if (direct.exists()){
                        Log.d("created",direct.getAbsolutePath().toString());
                    }
                }
               /* DownloadManager dm=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                Uri urll= url;
                DownloadManager.Request request= new DownloadManager.Request(urll);

                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE );

                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //request.setDestinationInExternalFilesDir(getApplicationContext(),"/Staff_navigation/",name.getText().toString()+".pdf");
                request.setDestinationInExternalPublicDir(direct.getAbsolutePath(),name.getText().toString()+".pdf");
                dm.enqueue(request);*/
            }
        });


    }

}