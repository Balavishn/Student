package com.example.student_sides.ui.signout;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.staff_navigation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signout extends Fragment {

    private SignoutViewModel mViewModel;

    public static Signout newInstance() {
        return new Signout();
    }
    EditText a, b, c,e,f;
    Button d;
    Spinner cls,sec,sub;
    DatabaseReference reference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View v=inflater.inflate(R.layout.fragment_signout, container, false);
        a = v.findViewById(R.id.hi);
        b = v.findViewById(R.id.hii);
        c = v.findViewById(R.id.hiii);
        d= v.findViewById(R.id.bye);
        e= v.findViewById(R.id.hel);
        f= v.findViewById(R.id.hello);
        cls=v.findViewById(R.id.cls);
        sec=v.findViewById(R.id.sec);
        sub=v.findViewById(R.id.sub);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cls1=cls.getSelectedItem().toString();
                String sec1=sec.getSelectedItem().toString();
                String sub1=sub.getSelectedItem().toString();

                if(!a.getText().toString().equals("") && !b.getText().toString().equals("") && !c.getText().toString().equals("")
                && !d.getText().toString().equals("") && !e.getText().toString().equals("") && !f.getText().toString().equals("")
                ) {

                    reference = FirebaseDatabase.getInstance().getReference("MCQ");
                    HashMap<String, String> map = new HashMap<>();
                    map.put("Question", a.getText().toString());
                    map.put("Correct", b.getText().toString());
                    map.put("Wrong", c.getText().toString());
                    map.put("Wrong", e.getText().toString());
                    map.put("Wrong", f.getText().toString());
                    reference.child(cls1 + sec1).child(sub1).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(v.getContext(), "Success", Toast.LENGTH_SHORT).show();
                                a.setText("");
                                b.setText("");
                                c.setText("");
                                e.setText("");
                                f.setText("");

                            } else
                                Toast.makeText(v.getContext(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else Toast.makeText(v.getContext(),"Enter All fields",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignoutViewModel.class);
        // TODO: Use the ViewModel
    }

}