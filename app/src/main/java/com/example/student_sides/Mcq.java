package com.example.student_sides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Mcq extends AppCompatActivity {

    TextView ques;
    RadioButton radioButton,op1,op2,op3,op4;
    Button check;
    DatabaseReference mcq;

    String cls,section,sub,correct;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);
        cls=getIntent().getStringExtra("class");
        section=getIntent().getStringExtra("section");
        sub=getIntent().getStringExtra("subject");
        ques=findViewById(R.id.question_mcq);
        group=findViewById(R.id.all_mcq);
        op1=findViewById(R.id.option1);
        op2=findViewById(R.id.option2);
        op3=findViewById(R.id.option3);
        op4=findViewById(R.id.option4);
        check=findViewById(R.id.Check);
        mcq= FirebaseDatabase.getInstance().getReference("MCQ").child(cls+"-"+section).child(sub);
        mcq.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ques:snapshot.getChildren()){
                    correct=ques.child("Correct").getValue().toString();
                    op1.setText(ques.child("op1").getValue().toString());
                    op2.setText(ques.child("op2").getValue().toString());
                    op3.setText(ques.child("op3").getValue().toString());
                    op4.setText(ques.child("op4").getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos=group.getCheckedRadioButtonId();
                radioButton=findViewById(pos);
                if (correct.equals(radioButton.getText().toString())){
                    Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}