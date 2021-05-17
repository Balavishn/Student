package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class academic_study_content extends AppCompatActivity {

    String[] class_list,sub_list;
    DatabaseReference sub_reference,class_ref;
    SharedPreferences preferences;
    String s,clss,subs;
    ArrayAdapter<String> class_arrayAdapter;
    ArrayAdapter<String> sub_arrayAdapter;

        DatabaseReference reference,databaseReference,pdfref;
        //LinearLayouts
        LinearLayout l1,l2,l3,l4,l5;

        EditText uc1,uc2,uc3,uc4,uc5;
        //unit headings
        EditText unit1,unit2,unit3,unit4,unit5;
        Spinner clssec,sub;
        int i=1;
        RecyclerView unit1_rec,unit2_rec,unit3_rec,unit4_rec,unit5_rec;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_academic_study_content);
            clssec = findViewById(R.id.cls);
            sub = findViewById(R.id.sub);
            //Unitcount
            uc1 = findViewById(R.id.unit1_count);
            uc2 = findViewById(R.id.unit2_count);
            uc3 = findViewById(R.id.unit3_count);
            uc4 = findViewById(R.id.unit4_count);
            uc5 = findViewById(R.id.unit5_count);

            //RecyclerView
            unit1_rec = findViewById(R.id.unit1_recycle);
            unit1_rec.setLayoutManager(new LinearLayoutManager(this));
            unit2_rec = findViewById(R.id.unit2_recycle);
            unit2_rec.setLayoutManager(new LinearLayoutManager(this));
            unit3_rec = findViewById(R.id.unit3_recycle);
            unit3_rec.setLayoutManager(new LinearLayoutManager(this));
            unit4_rec = findViewById(R.id.unit4_recycle);
            unit4_rec.setLayoutManager(new LinearLayoutManager(this));
            unit5_rec = findViewById(R.id.unit5_recycle);
            unit5_rec.setLayoutManager(new LinearLayoutManager(this));

            //unit edittext
            unit1 = findViewById(R.id.unit1);
            unit2 = findViewById(R.id.unit2);
            unit3 = findViewById(R.id.unit3);
            unit4 = findViewById(R.id.unit4);
            unit5 = findViewById(R.id.unit5);

            //Unit number
            final String unit1_text="1",unit2_text="2",unit3_text="3",unit4_text="4",unit5_text="5";

            preferences = this.getSharedPreferences("NUMBER", MODE_PRIVATE);
            s = preferences.getString("Register", "default");
            class_ref = FirebaseDatabase.getInstance().getReference("Staff_Details").child(s).child("class");

            class_ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        clss = snapshot.child("class").getValue().toString();
                        subs = snapshot.child("subject").getValue().toString();
                        class_list = clss.split(",");
                        sub_list = subs.split(",");
                        Log.d("ccc", class_list.toString() + sub_list.toString());
                        class_arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, class_list);
                        sub_arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, sub_list);
                        clssec.setAdapter(class_arrayAdapter);
                        sub.setAdapter(sub_arrayAdapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

          uc1.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

              }

              @Override
              public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  if(!uc1.getText().toString().equals("")) {
                      String count = uc1.getText().toString();
                      study_content_adapter study_content_adapter = new study_content_adapter(Integer.parseInt(count), academic_study_content.this, unit1.getText().toString(), unit1_text);
                      unit1_rec.setAdapter(study_content_adapter);
                  }
              }

              @Override
              public void afterTextChanged(Editable editable) {

              }
          });
            uc2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!uc2.getText().toString().equals("")) {
                        String count = uc2.getText().toString();
                        study_content_adapter study_content_adapter = new study_content_adapter(Integer.parseInt(count), academic_study_content.this, unit2.getText().toString(), unit2_text);
                        unit2_rec.setAdapter(study_content_adapter);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            uc3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!uc3.getText().toString().equals("")) {
                        String count = uc1.getText().toString();
                        study_content_adapter study_content_adapter = new study_content_adapter(Integer.parseInt(count), academic_study_content.this, unit3.getText().toString(), unit3_text);
                        unit3_rec.setAdapter(study_content_adapter);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            uc4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!uc4.getText().toString().equals("")) {
                        String count = uc4.getText().toString();
                        study_content_adapter study_content_adapter = new study_content_adapter(Integer.parseInt(count), academic_study_content.this, unit4.getText().toString(), unit4_text);
                        unit4_rec.setAdapter(study_content_adapter);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            uc5.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!uc5.getText().toString().equals("")) {
                        String count = uc5.getText().toString();
                        study_content_adapter study_content_adapter = new study_content_adapter(Integer.parseInt(count), academic_study_content.this, unit5.getText().toString(), unit5_text);
                        unit5_rec.setAdapter(study_content_adapter);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


        }
    public void upload(String subhead_name,String unit,String unit_number){
            if(!unit.equals("")) {
                Intent intent = new Intent(getApplicationContext(), StudyUpload.class);
                intent.putExtra("Unit", "Unit" + unit_number);
                intent.putExtra("ClassSec", clssec.getSelectedItem().toString());
                intent.putExtra("Subject", sub.getSelectedItem().toString());
                intent.putExtra("SubHeading", subhead_name);
                intent.putExtra("UnitName", unit);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),"Enter Unit Name",Toast.LENGTH_SHORT).show();
            }
    }
}
