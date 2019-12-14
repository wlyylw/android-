package com.example.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.activity.DetailNews;
import com.example.entity.News;
import com.example.news.R;

import java.util.List;


public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> list;

    static  class ViewHolder extends RecyclerView.ViewHolder{
        View ClickView;
        TextView title;
        TextView source;
        TextView count;
        ImageView imageview;
        Context context;
        public ViewHolder(View view)
        {
            super(view);
            source = view.findViewById(R.id.news_source);
            title = view.findViewById(R.id.news_title);
            count = view.findViewById(R.id.news_count);
            imageview = view.findViewById(R.id.index_news_img);
            ClickView =view;
            context = view.getContext();
        }

    }
    public NewsAdapter(List<News> list){
        this.list=list;
    }


    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_item_news,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ClickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:具体的消息页
                int position = holder.getAdapterPosition();
                News news = list.get(position);
                Intent intent = new Intent(holder.context, DetailNews.class);
                intent.putExtra("docid",news.getDocid());
                holder.context.startActivity(intent);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = list.get(position);
        holder.title.setText(news.getTitle());
        holder.source.setText(news.getSource());
        holder.count.setText( news.getCommentCount() + "跟帖" );
        String url = news.getImgsrc();
        url = url.replace("http","https");
        Glide.with(holder.context).load(url).into(holder.imageview);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
