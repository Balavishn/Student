package com.example.student_sides;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.staff_navigation.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VideoUpload extends AppCompatActivity {
    EditText editpdfname;
    String unit,clssec,sub,subheading,unitname;
    Button upload;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_upload);

        editpdfname=(EditText)findViewById(R.id.txtvideoname);
        upload=(Button)findViewById(R.id.videobtnupload);
        storageReference= FirebaseStorage.getInstance().getReference();
        Intent intent1=getIntent();
        unit=intent1.getStringExtra("Unit");
        clssec=intent1.getStringExtra("ClassSec");
        sub=intent1.getStringExtra("Subject");
        subheading=intent1.getStringExtra("SubHeading");
        unitname=intent1.getStringExtra("UnitName");
        editpdfname.setText(subheading);
        databaseReference= FirebaseDatabase.getInstance().getReference("Video");


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editpdfname.getText().toString().equals("")) {
                selectPDFFile();
                }else Toast.makeText(getApplicationContext(),"Enter File name",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selectPDFFile() {
        Intent intent = new Intent();

        intent.setType("video/mp4");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent.createChooser(intent,"Choose Video file"),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            uploadPDFFile(data.getData());
        }
    }

    private void uploadPDFFile(Uri data) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference reference = storageReference.child("Video/").child(clssec).child(sub).child(unit).child(unitname).child(editpdfname.getText().toString() + System.currentTimeMillis() + ".mp4");
            reference.putFile(data)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uri.isComplete()) ;
                            Uri url = uri.getResult();
                            uploadVideoFile uploadtb = new uploadVideoFile(editpdfname.getText().toString(), url.toString());
                            databaseReference.child(clssec).child(sub).child(unit).child(unitname).push().setValue(uploadtb);
                            Toast.makeText(getApplicationContext(), "File uploaded successfully", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();


                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    progressDialog.setMessage("Uploaded  " + (int) progress);

                }
            });
    }


}