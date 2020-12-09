package com.example.application.function;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.application.R;
import com.example.application.adapter.ViewPageAdapter;
import com.example.application.fragment.DownFragment;
import com.example.application.fragment.LikeFragment;
import com.example.application.fragment.PublishFragment;
import com.example.application.fragment.SeenFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class LibraryActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_library);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_library:
                        return true;
                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_publish:
                        startActivity(new Intent(getApplicationContext(),PublishActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_menu:
                        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        tabLayout = findViewById(R.id.TabLayout);
        viewPager = findViewById(R.id.viewPage);
        getTab();
    }

    public void getTab(){
        final ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
                viewPageAdapter.addFragment(SeenFragment.getInstance(), "Đã xem");
                viewPageAdapter.addFragment(LikeFragment.getInstance(), "Đã thích");
                viewPageAdapter.addFragment(DownFragment.getInstance(), "Đã tải");
                viewPageAdapter.addFragment(PublishFragment.getInstance(), "Đã xuất bản");

                viewPager.setAdapter(viewPageAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}