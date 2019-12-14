package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.adapter.VideoAdapter;
import com.example.entity.Video;
import com.example.news.NewsSource;
import com.example.news.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class VideoPage extends Fragment {

    String Data = null;
    List<Video> videos = null;
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_video_page, null);
        recyclerView = view.findViewById(R.id.display_videos);
        LinearLayoutManager layoutManagerVideos = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManagerVideos);

        ParseVideo();
        getActivity().runOnUiThread(()->
        {
            Gson gson = new Gson();
            videos = gson.fromJson(Data,new TypeToken<List<Video>>(){}.getType());

            VideoAdapter videoAdapter = new VideoAdapter(videos);
            recyclerView.setAdapter(videoAdapter);
        });
        return view;
    }

    private void ParseVideo()  {
        NewsSource newsSource = NewsSource.getInstance() ;
        try {
            new Thread(()-> {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(newsSource.getVideo())
                        .addHeader("User-Agent", "ozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Data = response.body().string();
                    int length = Data.length();
                    Data = Data.substring(13, length - 1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(3000);
        }
        catch (Exception e)
        {

        }
    }

}
