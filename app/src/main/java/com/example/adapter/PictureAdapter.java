package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.entity.Picture;
import com.example.news.R;

import java.util.List;


public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder>{

    List<Picture> pictures;
    Info mRectF;

    static class ViewHolder extends RecyclerView.ViewHolder{

        PhotoView photoView;
        PhotoView photoView2;
        Context context;
        TextView textView;

        public ViewHolder(View view)
        {
            super(view);
            photoView = view.findViewById(R.id.imageid);
            photoView2 = view.findViewById(R.id.imageid2);
            textView = view.findViewById(R.id.imagetext);
            context = view.getContext();
        }

    }

    public PictureAdapter(List<Picture> pictures){

        this.pictures = pictures;
    }




    @NonNull
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item,parent,false);
        PictureAdapter.ViewHolder holder = new PictureAdapter.ViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull PictureAdapter.ViewHolder holder, int position) {

        Picture picture = pictures.get(position);


        Glide.with(holder.context).load(picture.getCover()).into(holder.photoView);
        Glide.with(holder.context).load(picture.getCover()).into(holder.photoView2);

        holder.photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.photoView.setVisibility(View.GONE);
                holder.photoView2.setVisibility(View.VISIBLE);

                mRectF = holder.photoView.getInfo();
                holder.photoView2.animaFrom(mRectF);
            }
        });


        holder.photoView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.photoView2.enable();
                holder.photoView2.animaTo(mRectF, new Runnable() {
                    @Override
                    public void run() {
                        holder.photoView2.setVisibility(View.GONE);
                        holder.photoView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });


        holder.textView.setText(picture.getSetname());
    }

    @Override
    public int getItemCount() {

        return pictures.size();
    }


}
