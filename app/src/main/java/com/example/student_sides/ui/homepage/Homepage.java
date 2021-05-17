package com.example.student_sides.ui.homepage;

import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.staff_navigation.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class Homepage extends Fragment {

    private HomepageViewModel mViewModel;
    TextView home;

    public static Homepage newInstance() {
        return new Homepage();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.homepage_fragment, container, false);
        SharedPreferences preferences =root.getContext().getSharedPreferences("NUMBER",MODE_PRIVATE);
        String s=preferences.getString("Register","default");
        home=root.findViewById(R.id.message);

        final CircleImageView circleImageView=root.findViewById(R.id.profile_image);
        DatabaseReference reference= reference=FirebaseDatabase.getInstance().getReference("STUDENT DETAILS").child(s);
        final String[] url = new String[1];
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                url[0] =snapshot.child("profile_img").getValue().toString();
                Log.d("imageurl",url[0].toString());
                home.setText(snapshot.child("Student_Name").getValue().toString());
                Picasso.get().load(url[0]).into(circleImageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomepageViewModel.class);
        // TODO: Use the ViewModel
    }

}