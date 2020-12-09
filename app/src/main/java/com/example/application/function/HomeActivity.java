package com.example.application.function;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.example.application.PostManage.Post;
import com.example.application.R;
import com.example.application.adapter.BannerDocumentPagerAdapter;
import com.example.application.adapter.HomeAdapter;
import com.example.application.model.BannerDocument;
import com.example.application.model.PostData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    BannerDocumentPagerAdapter bannerDocumentPagerAdapter;
    TabLayout tabLayout, categoryTab;
    ViewPager bannerDocumentViewPager;
    List<BannerDocument> bannerDocumentList;

    private DatabaseReference mDatabase;
    RecyclerView postRecycler;
    HomeAdapter homeAdapter;
    List<PostData> postDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_library:
                        startActivity(new Intent(getApplicationContext(),LibraryActivity.class));
                        overridePendingTransition(0,0);
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
        //
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("Posts");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postDataList = new ArrayList<>();
                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {

                    Post post = postsnap.getValue(Post.class);
                    postDataList.add(new PostData(post.getUserName(),post.getTitle(),post.getPicture()));


                }

                homeAdapter = new HomeAdapter(HomeActivity.this,postDataList);
                postRecycler.setAdapter(homeAdapter);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        setPostRecycler(postDataList);

        //
        tabLayout = findViewById(R.id.tablayout_home_indicator);

        bannerDocumentList = new ArrayList<BannerDocument>();
        bannerDocumentList.add(new BannerDocument(1,"Giải tích 3","https://images.indianexpress.com/2020/04/books_1200.jpg"));
        bannerDocumentList.add(new BannerDocument(2,"Giải tích 3","https://zshop.vn/images/link/63/1045.jpg?t=1474958283"));
        bannerDocumentList.add(new BannerDocument(3,"Giải tích 3","data:image/jpeg;base64,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"));

        setBannerDocumentPagerAdapter(bannerDocumentList);

        //
        Toolbar toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
    }

    private  void  setPostRecycler(List<PostData> postDataList){
        postRecycler = findViewById(R.id.viewHome);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        postRecycler.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter(this,postDataList);
        postRecycler.setAdapter(homeAdapter);
    }

    private void setBannerDocumentPagerAdapter(List<BannerDocument> bannerDocumentList){
        bannerDocumentViewPager = findViewById(R.id.viewpager_banner);
        bannerDocumentPagerAdapter = new BannerDocumentPagerAdapter(this,bannerDocumentList);
        bannerDocumentViewPager.setAdapter(bannerDocumentPagerAdapter);
        tabLayout.setupWithViewPager(bannerDocumentViewPager);

        Timer sliderTimer =new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(),4000,6000);
        tabLayout.setupWithViewPager(bannerDocumentViewPager,true);
    }

    class AutoSlider extends TimerTask {

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(bannerDocumentViewPager.getCurrentItem()<bannerDocumentList.size()-1){
                        bannerDocumentViewPager.setCurrentItem(bannerDocumentViewPager.getCurrentItem()+1);

                    }else{
                        bannerDocumentViewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.nav_notification){
            Intent intent = new Intent(HomeActivity.this,NotificationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}