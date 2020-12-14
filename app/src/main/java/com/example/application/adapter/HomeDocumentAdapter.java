package com.example.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.postmanage.Post;

import java.util.List;

public class HomeDocumentAdapter extends PagerAdapter {

    Context context;

    List<Post> list;

    public HomeDocumentAdapter(Context context, List<Post> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.banner_documnet_layout,null);

        ImageView bannerImage = view.findViewById(R.id.image_banner_document);

        Glide.with(context).load(list.get(position).getPicture()).into(bannerImage);
        container.addView(view);

        return view;

    }
}
