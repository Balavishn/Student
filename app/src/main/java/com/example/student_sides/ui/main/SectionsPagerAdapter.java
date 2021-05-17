package com.example.student_sides.ui.main;

import android.content.Context;

import com.example.staff_navigation.R;
import com.example.student_sides.Individual;
import com.example.student_sides.Overall;
import com.example.student_sides.Perforamce_tab;
import com.example.student_sides.Section_mark;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_3,R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    String cls,sec,name;

    public SectionsPagerAdapter(Context context, FragmentManager fm, String cls, String sec, String name) {
        super(fm);
        mContext = context;
        this.cls=cls;
        this.sec=sec;
        this.name=name;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment f=null;
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                f=new Individual(cls,sec,name);
                break;
            case 1:
                f=new Section_mark(cls,sec,name);
                break;
            case 2:
                f=new Overall(cls,sec,name);
                break;
        }
        return f;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}