package com.example.application.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.adapter.HomeAdapter;
import com.example.application.model.PostData;
import com.example.application.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

;

public class HomeFragment extends Fragment {

    RecyclerView postRecycler;
    HomeAdapter homeAdapter;

    //CharSequence search="";


    private HomeViewModel mViewModel;
    private View view;


    public static PublishFragment newInstance() {
        return new PublishFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home, container, false);

        List<PostData> postDataList = new ArrayList<>();
        postDataList .add(new PostData("Anderson Thomas","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_1));
        postDataList .add(new PostData("Adams Green","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_2));
        postDataList .add(new PostData("Laura Michelle","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_3));
        postDataList .add(new PostData("Betty L","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_4));
        postDataList .add(new PostData("Garcia Lewis","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_1));
        postDataList .add(new PostData("Roberts Turner","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_2));
        postDataList .add(new PostData("Mary Jackson","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_3));
        postDataList .add(new PostData("Sarah Scott","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_4));
        postDataList .add(new PostData("Anderson Thomas","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_1));
        postDataList .add(new PostData("Adams Green","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_2));
        postDataList .add(new PostData("Laura Michelle","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_3));
        postDataList .add(new PostData("Betty L","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_4));
        postDataList .add(new PostData("Garcia Lewis","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_1));
        postDataList .add(new PostData("Roberts Turner","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_2));
        postDataList .add(new PostData("Mary Jackson","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_3));
        postDataList .add(new PostData("Sarah Scott","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_4));


        setPostRecycler(postDataList);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    private  void  setPostRecycler(List<PostData> postDataList){
        postRecycler = view.findViewById(R.id.viewHome);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        postRecycler.setLayoutManager(layoutManager);
        homeAdapter = new HomeAdapter(view.getContext(),postDataList);
        postRecycler.setAdapter(homeAdapter);
    }



}