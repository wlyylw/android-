package com.example.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.entity.Video;
import com.example.news.R;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    List<Video> videos = null;

    static class ViewHolder extends RecyclerView.ViewHolder{

        JCVideoPlayerStandard player;
        Context context;
        TextView title;
        TextView author;
        TextView playcount;
        TextView votecount;
        public ViewHolder(View view)
        {
            super(view);
            player = view.findViewById(R.id.videoView);
            title = view.findViewById(R.id.video_title);
            author = view.findViewById(R.id.video_author);
            playcount = view.findViewById(R.id.video_play_count);
            votecount = view.findViewById(R.id.video_vote_count);
            context = view.getContext();
        }

    }

    public VideoAdapter(List<Video> videos){

        this.videos = videos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Video video = videos.get(position);
        if (holder.player != null) {
            holder.player.release();
        }
        boolean setUp = holder.player.setUp(video.getMp4_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {

            Glide.with(holder.context).load(video.getCover()).into(holder.player.thumbImageView);
        }
        holder.votecount.setText(video.getVotecount());
        holder.playcount.setText(video.getPlayCount());
        holder.author.setText(video.getVideosource());
        holder.title.setText(video.getTitle());


    }

    @Override
    public int getItemCount() {

        return videos.size();
    }




}
