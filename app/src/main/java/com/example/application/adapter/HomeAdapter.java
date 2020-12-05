package com.example.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;
import com.example.application.model.ItemAnimation;
import com.example.application.model.PostData;
import com.example.application.model.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter
        extends RecyclerView.Adapter<HomeAdapter.RecyclerviewHolder> {

    Context context;
    List<PostData> postDataList;
    List<PostData> filteredPostDataList;

    public HomeAdapter(Context context,  List<PostData> userDataList) {
        this.context = context;
        this.postDataList= userDataList;
        this.filteredPostDataList = userDataList;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_item_home, parent, false);
        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, final int position) {

        holder.userName.setText(filteredPostDataList.get(position).getUserName());
        holder.postDesc.setText(filteredPostDataList.get(position).getDescp());
        holder.postImage.setImageResource(filteredPostDataList.get(position).getImageUrl());

        ItemAnimation.animateFadeIn(holder.itemView, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UserDetails.class);
                intent.putExtra("username", filteredPostDataList.get(position).getUserName());
                intent.putExtra("userDesc",filteredPostDataList.get(position).getDescp());
                context.startActivity(intent);
            }
        });

        holder.postImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "User Name Clicked", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return filteredPostDataList.size();
    }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder {


        ImageView postImage;
        TextView userName, postDesc;

        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);

            postImage = itemView.findViewById(R.id.image_post);
            userName = itemView.findViewById(R.id.textview_userName);
            postDesc = itemView.findViewById(R.id.textview_Desc);


        }
    }

    public Filter getFilter(){

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String Key = charSequence.toString();
                if(Key.isEmpty()){
                    filteredPostDataList = postDataList;
                }
                else{

                    List<PostData> lstFiltered = new ArrayList<>();
                    for(PostData row: postDataList){
                        if(row.getUserName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);

                        }
                    }

                    filteredPostDataList  = lstFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values =  filteredPostDataList ;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filteredPostDataList  = (List<PostData>)filterResults.values;
                notifyDataSetChanged();

            }
        };

    }


}
