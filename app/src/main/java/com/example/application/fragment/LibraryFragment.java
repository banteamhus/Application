package com.example.application.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.application.R;
import com.example.application.viewmodel.LibraryViewModel;
import com.google.android.material.tabs.TabLayout;

public class LibraryFragment extends Fragment{

    private LibraryViewModel galleryViewModel;
    TabLayout tabLayout;
    ViewPager viewPager;

    public static LibraryFragment newInstance() {
        LibraryFragment f = new LibraryFragment();
        return f;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_library, container,false);

        /*
        galleryViewModel = new ViewModelProvider(this).get(LibraryViewModel.class);

        final TextView textView = root.findViewById(R.id.text_gallery);
        tabLayout = tabLayout.findViewById();
        viewPager = viewPager.findViewById();
        getTab();
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */
        return root;
    }
    /*
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
    }*/
}