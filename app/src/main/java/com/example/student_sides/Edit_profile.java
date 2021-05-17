package com.example.student_sides;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.example.staff_navigation.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Edit_profile extends AppCompatActivity {
    EditText name,phnenum,pass,id,email;
    Button ok;
    Spinner classs,section;
    DatabaseReference reference;
    String semail;
    String names,phnenums,passs,ids,emails;

    DatePickerDialog datePickerDialog;

    DatabaseReference ref,clsref;


    String user;

    CircleImageView profile;
    Uri uri;
    StorageReference profileref;
    StorageTask mUploadTask;

    TextView dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name=findViewById(R.id.edit_username);
        id=findViewById(R.id.edit_staff_id);
        phnenum=findViewById(R.id.edit_contact_no);
        pass=findViewById(R.id.edit_password);
        email=findViewById(R.id.edit_email);
        ok=findViewById(R.id.edit_button);
        profile=findViewById(R.id.imageView3);
        classs=findViewById(R.id.edit_Class);
        section=findViewById(R.id.edit_Section);
        dob=findViewById(R.id.edit_studentdob);

        profileref= FirebaseStorage.getInstance().getReference("STUDENT DETAILS").child("Profile");

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

        final SharedPreferences preferences =getSharedPreferences("NUMBER",MODE_PRIVATE);
        final String[] s = {preferences.getString("Register", "default")};
        reference= FirebaseDatabase.getInstance().getReference("STUDENT DETAILS");
        //Database
        user=preferences.getString("Register","default");
        ref= FirebaseDatabase.getInstance().getReference("STUDENT DETAILS");
        clsref=FirebaseDatabase.getInstance().getReference("STUDENT DETAILS");


        reference.child(s[0]).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    name.setText(snapshot.child("Student_Name").getValue().toString());
                    phnenum.setText(snapshot.child("Student_Number").getValue().toString());
                    pass.setText(snapshot.child("Student_Password").getValue().toString());
                    id.setText(snapshot.child("Student_Id").getValue().toString());
                    email.setText(snapshot.child("Student_Email").getValue().toString());
                    Picasso.get().load(snapshot.child("profile_img").getValue().toString()).into(profile);
                    dob.setText(snapshot.child("Student_dob").getValue().toString());
                    uri=Uri.parse(snapshot.child("profile_img").getValue().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Edit_profile.this,
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



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s[0] =preferences.getString("Register","default");
                names=name.getText().toString();
                phnenums=phnenum.getText().toString();
                passs=pass.getText().toString();
                ids=id.getText().toString();
                emails=email.getText().toString();
             //   subs=sub.getSelectedItem().toString();
                if (!TextUtils.isEmpty(names)
                        && !TextUtils.isEmpty(ids)
                        && !TextUtils.isEmpty(phnenums)
                        && !TextUtils.isEmpty(passs)
                        && !TextUtils.isEmpty(emails)) {

                            if (passs.length() >= 8) {
                                final HashMap<String, String> map = new HashMap<>();
                                map.put("Student_Name", names);
                                map.put("Student_Id", ids);
                               // map.put("Student_dob",Sdob);
                                map.put("Student_Class",classs.getSelectedItem().toString());
                                map.put("Student_Section",section.getSelectedItem().toString());
                                map.put("Student_Number", phnenums);
                                map.put("Student_Email", emails);
                                map.put("Student_Password", passs);
                                mUploadTask = profileref.child(ids).child("profile.jpg").putFile(uri);
                                        profileref.child(ids).child("profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                map.put("profile_img", uri.toString());
                                                reference.child(ids).setValue(map);
                                                ref = FirebaseDatabase.getInstance().getReference("Staff_Details").child(names);
                                                if (!ids.equals(s[0])) {
                                                    reference.child(s[0]).removeValue();
                                                    profileref.child(s[0]).delete();
                                                }
                                                SharedPreferences preferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
                                                SharedPreferences.Editor editor = preferences.edit();
                                                editor.putString("Register", ids);
                                                editor.putString("Password", passs);
                                                editor.commit();

                                              //  Toast.makeText(getApplicationContext(), "Edit Sucess", Toast.LENGTH_SHORT).show();
                                            }


                                        });

                            } else
                                Toast.makeText(getApplicationContext(), "Enter Valid 10 digit Number", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Enter All fields", Toast.LENGTH_SHORT).show();
             //   Intent intent = new Intent(getApplicationContext(), My_Account__Fragment.class);
             //   startActivity(intent);
            }
        });

    }

 /*   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //bar.setVisibility(View.VISIBLE);
        uri=data.getData();
        profile.setImageURI(uri);
    }*/


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void choose(){
        CropImage.activity().start(Edit_profile.this);
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