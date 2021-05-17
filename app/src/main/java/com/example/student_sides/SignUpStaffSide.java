package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.staff_navigation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.Calendar;
import java.util.HashMap;

public class SignUpStaffSide extends AppCompatActivity {

    EditText name,phnenum,pass,pass1,id,email;
    Button register;

    //String for Class
    String num;
    //String for Count

    CircleImageView profile;

    DatePickerDialog datePickerDialog;



    //String[] subject1 = getResources().getStringArray(R.array.Subject2);
    private  final static String INTENTVALUE="1" ;

    String s;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference,clsref;
    StorageReference profileref;
    signupadapter signupadapter;

    String[] clsssdata,sectdata,subdata;

    String tcls_sec="",tsubb="";
    final String[] Sname = new String[1];
    StorageTask mUploadTask;
    Uri uri;

    Spinner cls,sec;
    TextView dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_staff_side);
        name=findViewById(R.id.staffname);
        id=findViewById(R.id.staffid);

        cls=findViewById(R.id.Class);
        sec=findViewById(R.id.Section);

        dob=findViewById(R.id.studentdob);
        phnenum=findViewById(R.id.staffnumber);
        pass=findViewById(R.id.staffpass);
        pass1=findViewById(R.id.staffpass1);
        email=findViewById(R.id.staffemail);
        register=findViewById(R.id.register);
        profile=findViewById(R.id.imageView2);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(SignUpStaffSide.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dob.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /* Intent i= new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i,100);*/
                choose();
            }
        });



        reference=FirebaseDatabase.getInstance().getReference("STUDENT DETAILS");
        clsref=FirebaseDatabase.getInstance().getReference("STUDENT DETAILS");
        profileref=FirebaseStorage.getInstance().getReference("STUDENT DETAILS").child("Profile");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Sid, Sphnenum, Spass, Spass1, Semail;
                Sname[0] = name.getText().toString();
                Sid = id.getText().toString();
                Semail = email.getText().toString();

                Sphnenum = phnenum.getText().toString();
                Spass = pass.getText().toString();
                Spass1 = pass1.getText().toString();
                String classs=cls.getSelectedItem().toString();
                String section=sec.getSelectedItem().toString();
                String Sdob=dob.getText().toString();

                if (!TextUtils.isEmpty(Sname[0])
                        && !TextUtils.isEmpty(Sid)
                        && !TextUtils.isEmpty(Sphnenum)
                        && !TextUtils.isEmpty(Sphnenum)
                        && !TextUtils.isEmpty(Spass)
                        && !TextUtils.isEmpty(Spass1)
                        && !TextUtils.isEmpty(Semail)

                ) {


                        if (Spass.length() >= 8 && Spass1.length() >= 8) {
                            if (Spass.equals(Spass1)) {


                                if (Sphnenum.length() == 10) {
                                    final HashMap<String, String> map = new HashMap<>();

                                    map.put("Student_Name", Sname[0]);
                                    map.put("Student_Id", Sid);
                                    map.put("Student_dob",Sdob);
                                    map.put("Student_Class",classs);
                                    map.put("Student_Section",section);
                                    map.put("Student_Number", Sphnenum);
                                    map.put("Student_Email", Semail);
                                    map.put("Student_Password", Spass);

                                    mUploadTask = profileref.child(Sid).child("profile.jpg").putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Task<Uri> urim=taskSnapshot.getStorage().getDownloadUrl();

                                            while (!urim.isComplete()) ;
                                            Uri url = urim.getResult();
                                            map.put("profile_img", url.toString());
                                            reference.child(Sid).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Intent intent = new Intent(getApplicationContext(), LoginStaffSide.class);
                                                        Toast.makeText(SignUpStaffSide.this, "Uploaded Sucessfully...", Toast.LENGTH_SHORT).show();
                                                        finish();

                                                        SharedPreferences preferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
                                                        SharedPreferences.Editor editor = preferences.edit();
                                                        editor.putString("Register", Sid);
                                                        editor.putString("Password", Spass);
                                                       // editor.putString("StaffID", Sname[0]);

                                                        editor.commit();

                                                        startActivity(intent);
                                                        finish();
                                                    } else
                                                        Toast.makeText(SignUpStaffSide.this, "Error", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });

                                } else
                                    Toast.makeText(SignUpStaffSide.this, "Maximum 10-Digit Numbers", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(SignUpStaffSide.this, "Fields Mismatching", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(SignUpStaffSide.this, "Must Have 8 Characters", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(SignUpStaffSide.this, "Constrains missing", Toast.LENGTH_SHORT).show();

                }

        });



    }

    /*protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //bar.setVisibility(View.VISIBLE);
        uri=data.getData();
        profile.setImageURI(uri);
    }*/

   /* public void getdata(){
        clsssdata=signupadapter.getclassdata();
        sectdata=signupadapter.getSecdata();
        subdata=signupadapter.getSubjdata();
        final HashMap<String, String> classmap = new HashMap<>();
        for(int i=0;i<clsssdata.length;i++){
            tcls_sec+=clsssdata[i]+"-"+sectdata[i];
            tsubb+=subdata[i];
            if(!(i==clsssdata.length-1)){
                tcls_sec+=",";
                tsubb+=",";
            }
            Log.d("datataclass",clsssdata[i]+sectdata[i]+subdata[i]);
        }
        classmap.put("class",tcls_sec);
        classmap.put("subject",tsubb);
        Log.d("class",tcls_sec+" "+tsubb);


        clsref.child(Sname[0]).child("class").setValue(classmap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
            }
        });

    }*/

    public void choose(){
        CropImage.activity().start(SignUpStaffSide.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                uri=result.getUri();
                profile.setImageURI(uri);
            }
            else if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Toast.makeText(getApplicationContext(),result.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}



















