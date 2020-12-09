package com.example.application.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.application.R;


public class DownFragment extends Fragment {


    // TODO: Rename and change types and number of parameters
    public static DownFragment getInstance() {
        DownFragment downFragment = new DownFragment();
        return downFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_down, container, false);
    }
}