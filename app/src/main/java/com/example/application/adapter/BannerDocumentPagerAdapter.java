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
import com.example.application.model.BannerDocument;

import java.util.List;

public class BannerDocumentPagerAdapter extends PagerAdapter {

    Context context;
    List<BannerDocument> bannerDocumentList;

    public BannerDocumentPagerAdapter(Context context, List<BannerDocument> bannerDocumentList) {
        this.context = context;
        this.bannerDocumentList = bannerDocumentList;
    }

    @Override
    public int getCount() {
        return bannerDocumentList.size();
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

        Glide.with(context).load(bannerDocumentList.get(position).getImageUrl()).into(bannerImage);
        container.addView(view);

        return view;

    }
}
