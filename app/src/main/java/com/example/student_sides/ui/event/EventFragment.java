package com.example.student_sides.ui.event;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference reference;
    List<ModelClassEvent> listdata;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView=root.findViewById(R.id.recycle_events);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        listdata = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Events");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.d("Title",dataSnapshot.getValue().toString());
                    String title= (String) dataSnapshot.child("Title").getValue();
                    String desc= (String) dataSnapshot.child("Description").getValue();
                    String Date= (String) dataSnapshot.child("Date").getValue();
                    String Time= (String) dataSnapshot.child("Time").getValue();
                    ModelClassEvent l=new ModelClassEvent(title,desc,Date,Time);
                //    ModelClassEvent l = dataSnapshot.getValue(ModelClassEvent.class);
                    listdata.add(l);
                }
                CustomAdapterEvent customAdapter = new CustomAdapterEvent((ArrayList<ModelClassEvent>) listdata);
                recyclerView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        return root;
    }
}