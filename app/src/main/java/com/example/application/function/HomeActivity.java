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
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.application.adapter.HomeMainRecyclerAdapter;
import com.example.application.model.AllPost;
import com.example.application.R;
import com.example.application.adapter.HomeDocumentAdapter;

import com.example.application.postmanage.Post;
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

    HomeDocumentAdapter documentAdapter;
    TabLayout tabLayout, postTab;
    ViewPager documentViewPager;
    List<Post> list_1;
    List<Post>  list_2;
    List<Post>  list_3;

    HomeMainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllPost> allPostList;



    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        tabLayout = findViewById(R.id.tablayout_home_indicator);
        postTab =findViewById(R.id.tablayout_home);

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

        tabLayout = findViewById(R.id.tablayout_home_indicator);
        postTab =findViewById(R.id.tablayout_home);
        //
        list_1 = new ArrayList<Post>();
        //
        list_2 = new ArrayList<Post>();
        //
        list_3 = new ArrayList<Post>();

        //

        postTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 1:
                        setDocumentAdapter(list_2);;
                        return;
                    case 2:
                        setDocumentAdapter(list_3);
                        return;
                    default:
                        setDocumentAdapter(list_1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //
        List<Post> postList1 =new ArrayList<>();
        List<Post> postList2 =new ArrayList<>();
        List<Post> postList3 =new ArrayList<>();
        List<Post> postList4 =new ArrayList<>();

        //
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("Posts");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {
                    Post post = postsnap.getValue(Post.class);
                    //
                    postList1.add(post);
                    postList2.add(post);
                    postList3.add(post);
                    postList4.add(post);
                    //
                    list_1.add(post);
                    list_2.add(post);
                    list_3.add(post);
                }

                //
                setDocumentAdapter(list_1);
                //
                allPostList =new ArrayList<>();
                allPostList.add(new AllPost(1, "Giải tích 3",postList1));
                allPostList.add(new AllPost(2, "Đại số tuyến tính",postList2 ));
                allPostList.add(new AllPost(3, "Phương trình vi phân",postList3 ));
                allPostList.add(new AllPost(4, "Chú Đại và toán học",postList4 ));
                //
                setMainRecycler(allPostList);

                /*
                String size = String.valueOf(postList1.size());
                Toast.makeText(HomeActivity.this,size,Toast.LENGTH_SHORT).show();*/


                // homeAdapter = new HomeAdapter(HomeActivity.this,postDataList);
                //postRecycler.setAdapter(homeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        //
        Toolbar toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
    }


    private void setDocumentAdapter(List<Post> list){
        documentViewPager = findViewById(R.id.viewpager_banner);
        documentAdapter = new HomeDocumentAdapter(this,list);
        documentViewPager.setAdapter(documentAdapter);
        tabLayout.setupWithViewPager(documentViewPager);

        Timer sliderTimer =new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(),4000,6000);
        tabLayout.setupWithViewPager(documentViewPager,true);
    }

    class AutoSlider extends TimerTask {

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(documentViewPager.getCurrentItem()<list_1.size()-1){
                        documentViewPager.setCurrentItem(documentViewPager.getCurrentItem()+1);

                    }else{
                        documentViewPager.setCurrentItem(0);
                    }
                }
            });
        }


    }

    public void setMainRecycler(List<AllPost> allPostList){

        mainRecycler=findViewById(R.id.recycler_main);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new HomeMainRecyclerAdapter(this, allPostList);
        mainRecycler.setAdapter(mainRecyclerAdapter);

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