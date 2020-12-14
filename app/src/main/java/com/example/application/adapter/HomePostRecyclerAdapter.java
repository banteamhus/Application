package com.example.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.function.WritingActivity;
import com.example.application.postmanage.Post;

import java.util.List;

public class HomePostRecyclerAdapter extends RecyclerView.Adapter<HomePostRecyclerAdapter.ItemViewHolder> {

    Context context;
    List<Post> list;

    public HomePostRecyclerAdapter(Context context, List<Post> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_recycler_row_item,parent,false));
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_row_item_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Post post= list.get(position);

        holder.textTitle.setText(list.get(position).getTitle());
        holder.textName.setText(list.get(position).getUserName());
        Glide.with(context).load(list.get(position).getPicture()).into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, WritingActivity.class);
                intent.putExtra("Id",post.getTimeStamp().toString());
                intent.putExtra("Content",post.getPostContentId());
                intent.putExtra("Id_user",post.getUserId());
                intent.putExtra("Name",post.getUserName());
                intent.putExtra("Image",post.getPicture());
                intent.putExtra("Photo",post.getUserPhoto());
                // đang test cái name: title là post_id
                intent.putExtra("Title",post.getTimeStamp().toString());
                /*
                // passing data to the book activity
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                */
                // start the activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView;
        LinearLayout linearLayout;
        TextView textName;
        TextView textTitle;

        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);

            textTitle=itemView.findViewById(R.id.textview_Desc);
            linearLayout=itemView.findViewById(R.id.linear_item_home);
            imageView = itemView.findViewById(R.id.image_post);
            textName =itemView.findViewById(R.id.textview_userName);

        }
    }
}
