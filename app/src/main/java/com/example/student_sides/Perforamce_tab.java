
package com.example.student_sides;

import android.os.Bundle;


import com.example.staff_navigation.R;
import com.example.student_sides.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Perforamce_tab extends AppCompatActivity {
    String cls,sec,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perforamce_tab);
        cls=getIntent().getStringExtra("class");
        sec=getIntent().getStringExtra("section");
        name=getIntent().getStringExtra("namee");
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),cls,sec,name);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }
}