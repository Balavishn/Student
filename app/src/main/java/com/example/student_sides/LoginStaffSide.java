package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  LoginStaffSide extends AppCompatActivity {
    EditText regnum,pass;
    TextView register;
    Button login;
    DatabaseReference reference;
    ProgressDialog  progressDoalog;
    Intent intent;
    //  String checkingvalue="0";
    String cls;
    String email;





    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_staff_side);
        regnum=findViewById(R.id.Lusername);
        pass=findViewById(R.id.Lpass);
        login=findViewById(R.id.login);
        intent=getIntent();

        //   checkingvalue=intent.getStringExtra("Register1");
        // email=intent.getStringExtra("email");


        register=findViewById(R.id.lsignin);



        checking();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginStaffSide.this, SignUpStaffSide.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int[] i = {0};
                final String reg, password;
                reg = regnum.getText().toString();
                password = pass.getText().toString();

                if (TextUtils.isEmpty(reg) && TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginStaffSide.this, "Fields Missing..", Toast.LENGTH_SHORT).show();

                } else {
                    progressDoalog = new ProgressDialog(LoginStaffSide.this);

                    progressDoalog.setTitle("Loading...");

                    progressDoalog.show();


                    reference = FirebaseDatabase.getInstance().getReference("STUDENT DETAILS");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                String value = String.valueOf(dataSnapshot.child("Student_Id").getValue());
                                String value1 = String.valueOf(dataSnapshot.child("Student_Password").getValue());

                                if (reg.equals(value) && password.equals(value1)) {
                                    i[0] =1;
                                    SharedPreferences preferences = getSharedPreferences("NUMBER", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("Register", value);
                                    editor.putString("Password", value1);


                                    Intent intent = new Intent(LoginStaffSide.this,MainActivity.class);
                                    // intent.putExtra("Register",email);
                                    finish();



                                    editor.commit();
//
                                    startActivity(intent);
                                    finish();
                                    progressDoalog.dismiss();


                                }


                            }
                            if(i[0]==0){
                                Toast.makeText(LoginStaffSide.this, "User Name Pasword does not Matches", Toast.LENGTH_SHORT).show();
                                progressDoalog.dismiss();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }


                    });

                }
            }
        });

    }




    private void checking() {
        SharedPreferences preferences =getSharedPreferences("NUMBER",MODE_PRIVATE);
        String s=preferences.getString("Register","default");



        if (!s.equals("default"))
        {

            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            // intent.putExtra("Register",s);
            startActivity(intent);

            finish();
        }

    }


}

