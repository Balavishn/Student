package com.example.student_sides.ui.my_account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import com.example.student_sides.Edit_profile;
import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class My_Account__Fragment extends Fragment {
    TextView name,phnenum,pass,id,email,subject,classs;
    ImageView Edit;
    DatabaseReference reference,class_ref;
    String s,os;
    TextView textView;
    CircleImageView profile;
    SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_account, container, false);
        preferences =getContext().getSharedPreferences("NUMBER",MODE_PRIVATE);
        s=preferences.getString("Register","default");
        os=s;
        reference= FirebaseDatabase.getInstance().getReference("STUDENT DETAILS").child(s);
        class_ref=FirebaseDatabase.getInstance().getReference("STUDENT DETAILS").child(s);
        Log.d("register",s);
        textView = root.findViewById(R.id.text_home);
        textView.setText(s);
        name = root.findViewById(R.id.my_account_username);
        phnenum = root.findViewById(R.id.my_account_contact_no);
        pass = root.findViewById(R.id.my_account_password);
        id = root.findViewById(R.id.my_account_staff_id);
        email = root.findViewById(R.id.my_account_email);
        subject = root.findViewById(R.id.my_account_staffsubspinner);
        classs = root.findViewById(R.id.my_account_class_id);
        Edit=root.findViewById(R.id.my_account_button);
        profile=root.findViewById(R.id.imageView4);
        Log.d("count","counts");
        HashMap<String, String> map = new HashMap<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Log.d("call", "call");
                    name.setText(snapshot.child("Student_Name").getValue().toString());
                    phnenum.setText(snapshot.child("Student_Number").getValue().toString());
                    pass.setText(snapshot.child("Student_Password").getValue().toString());
                    id.setText(snapshot.child("Student_Id").getValue().toString());
                    email.setText(snapshot.child("Student_Email").getValue().toString());
                    classs.setText(snapshot.child("Student_Class").getValue().toString());
                    subject.setText(snapshot.child("Student_Section").getValue().toString());
                    Picasso.get().load(Uri.parse(snapshot.child("profile_img").getValue().toString())).into(profile);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        class_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
      //  Log.d("database",reference.getRef().child(s).toString());
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Edit_profile.class));
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        Log.d("myaccount","resume");
        s=preferences.getString("Register","default");
        textView.setText(s);
        reference= FirebaseDatabase.getInstance().getReference("Staff_Details").child(s);
        class_ref=FirebaseDatabase.getInstance().getReference("Staff_Details").child(s).child("class");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Log.d("call", "call");
                    name.setText(snapshot.child("Staff_Name").getValue().toString());
                    phnenum.setText(snapshot.child("Staff_Number").getValue().toString());
                    pass.setText(snapshot.child("Staff_Password").getValue().toString());
                    id.setText(snapshot.child("Staff_Id").getValue().toString());
                    email.setText(snapshot.child("Staff_Email").getValue().toString());
          //          subject.setText(snapshot.child("Staff_Sub").getValue().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        class_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    classs.setText(snapshot.child("class").getValue().toString());
                    subject.setText(snapshot.child("subject").getValue().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
   /*     if(!s.equals(os)){
           reference= FirebaseDatabase.getInstance().getReference("Students").child(os);
            reference.removeValue();
        }*/
        super.onResume();

    }
    public String getClasss_name(){
        return classs.getText().toString();
    }
}