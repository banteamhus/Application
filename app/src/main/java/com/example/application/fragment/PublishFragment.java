package com.example.application.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.application.R;
import com.example.application.activity.AddPost;
import com.example.application.viewmodel.PublishViewModel;

public class PublishFragment extends Fragment {

    private PublishViewModel mViewModel;
    private View view;

    TextView createpost;
    public static PublishFragment newInstance() {
        return new PublishFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_publish, container, false);
        createpost = view.findViewById(R.id.create_post);
        createpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddPost.class));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PublishViewModel.class);
        // TODO: Use the ViewModel
    }

}