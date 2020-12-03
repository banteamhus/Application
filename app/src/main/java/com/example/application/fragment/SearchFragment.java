package com.example.application.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.adapter.RecyclerviewAdapter;
import com.example.application.model.UserData;
import com.example.application.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {


    RecyclerView userRecycler;
    RecyclerviewAdapter recyclerviewAdapter;
    EditText searchView;
    CharSequence search="";

    private View view;
    private SearchViewModel mViewModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = view.findViewById(R.id.search_bar);

        List<UserData> userDataList = new ArrayList<>();
        userDataList.add(new UserData("Anderson Thomas","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_1));
        userDataList.add(new UserData("Adams Green","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_2));
        userDataList.add(new UserData("Laura Michelle","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_3));
        userDataList.add(new UserData("Betty L","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_4));
        userDataList.add(new UserData("Garcia Lewis","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_1));
        userDataList.add(new UserData("Roberts Turner","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_2));
        userDataList.add(new UserData("Mary Jackson","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_3));
        userDataList.add(new UserData("Sarah Scott","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_4));
        userDataList.add(new UserData("Anderson Thomas","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_1));
        userDataList.add(new UserData("Adams Green","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_2));
        userDataList.add(new UserData("Laura Michelle","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_3));
        userDataList.add(new UserData("Betty L","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_male_4));
        userDataList.add(new UserData("Garcia Lewis","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_1));
        userDataList.add(new UserData("Roberts Turner","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_2));
        userDataList.add(new UserData("Mary Jackson","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_3));
        userDataList.add(new UserData("Sarah Scott","Android is awesome and this is the part 3 of recyclerview.", R.drawable.photo_female_4));




        setUserRecycler(userDataList);


        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                recyclerviewAdapter.getFilter().filter(charSequence);
                search = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        // TODO: Use the ViewModel
    }

    private  void  setUserRecycler(List<UserData> userDataList){
        userRecycler = view.findViewById(R.id.userRecycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        userRecycler.setLayoutManager(layoutManager);
        recyclerviewAdapter = new RecyclerviewAdapter(view.getContext(),userDataList);
        userRecycler.setAdapter(recyclerviewAdapter);
    }






}