package com.example.application.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.application.R;
import com.example.application.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Postlish_main_testActivity extends AppCompatActivity {


    private BottomNavigationView navigationView;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlish_main_test);

        navigationView = findViewById(R.id.bottom_navigation_view);
        viewPager =findViewById(R.id.view_pager);

        setUpViewPager();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_library:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_search:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.nav_publish:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.nav_menu:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });

    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter =new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.nav_library).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.nav_search).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.nav_publish).setChecked(true);
                        break;
                    case 4:
                        navigationView.getMenu().findItem(R.id.nav_menu).setChecked(true);
                        break;

                }
            }

            @Override
                public void onPageScrollStateChanged(int state) {

            }
        });

    }



}