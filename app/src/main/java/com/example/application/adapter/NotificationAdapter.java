package com.example.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application.R;
import com.example.application.model.NotificationData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.RecyclerviewHolder> {
    Context context;
    List<NotificationData> notifiDataList;


    public NotificationAdapter(Context context, List<NotificationData> notifiDataList ) {
        this.context = context;
        this.notifiDataList = notifiDataList ;
    }

    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row_item_notification, parent, false);
        return new RecyclerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewHolder holder, final int position) {

        holder.textDesc.setText(notifiDataList.get(position).getDescp());
        holder.textTime.setText(notifiDataList.get(position).getTime());
        Glide.with(context).load(notifiDataList.get(position).getImageUrl()).into(holder.notifiImage);
    }

    @Override
    public int getItemCount() {
        return notifiDataList.size();
    }

    public static final class RecyclerviewHolder extends RecyclerView.ViewHolder {


        CircleImageView notifiImage;
        TextView textTime, textDesc;

        public RecyclerviewHolder(@NonNull View itemView) {
            super(itemView);

            notifiImage = itemView.findViewById(R.id.notifiImage);
            textTime= itemView.findViewById(R.id.notifiTime);
            textDesc = itemView.findViewById(R.id.notifiDesc);


        }
    }






}
