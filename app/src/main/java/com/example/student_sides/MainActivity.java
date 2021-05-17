package com.example.student_sides;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.staff_navigation.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public String email;
    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView;

    Intent intent;

    ImageView nav;
    TextView head;

    String name_student;



    View hview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        email=intent.getStringExtra("email");
//        Log.d("email",email);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        hview=navigationView.getHeaderView(0);
        nav=hview.findViewById(R.id.imageView);
        head=hview.findViewById(R.id.nav_header_text);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homepage2,
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.accountFragment,R.id.help2,R.id.signout2)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        SharedPreferences preferences =getSharedPreferences("NUMBER",MODE_PRIVATE);
        String s=preferences.getString("Register","default");
        Log.d("nameuser",s);

        DatabaseReference reference= reference= FirebaseDatabase.getInstance().getReference("STUDENT DETAILS").child(s);
        Log.d("reference",reference.getRef().toString());
        final String[] url = new String[1];
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                url[0] =snapshot.child("profile_img").getValue().toString();
                name_student=snapshot.child("Student_Name").getValue().toString();
                Log.d("imageurl",url[0].toString());
                head.setText(name_student);
                Picasso.get().load(url[0]).into(nav);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        navigationView.bringToFront();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.sign_out){
            SharedPreferences preferences =getSharedPreferences("NUMBER",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            finish();
            startActivity(new Intent(getApplicationContext(),LoginStaffSide.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

   // public static String getemail(){
   //     return email;
   // }

}