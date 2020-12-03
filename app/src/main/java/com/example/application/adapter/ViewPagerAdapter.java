package com.example.application.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.application.fragment.HomeFragment;
import com.example.application.fragment.LibraryFragment;
import com.example.application.fragment.MenuFragment;
import com.example.application.fragment.PublishFragment;
import com.example.application.fragment.SearchFragment;

public class ViewPagerAdapter  extends FragmentPagerAdapter {



    static final int NUM_ITEMS = 5;


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new LibraryFragment();
            case 2:
                return new SearchFragment();
            case 3:
                return new PublishFragment();
            case 4:
                return new MenuFragment();
            default:
                return  null;
        }

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }



}

