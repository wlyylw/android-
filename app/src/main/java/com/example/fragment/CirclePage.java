package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.PictureAdapter;
import com.example.entity.Picture;
import com.example.news.PictureSource;
import com.example.news.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CirclePage extends Fragment {

    List<Picture> pictures = null;
    String Data = null;
    RecyclerView recyclerView;
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        InitTab();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circle_page, null);

        recyclerView = view.findViewById(R.id.display_pictures);
        LinearLayoutManager layoutManagerPictures = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManagerPictures);

        ParsePicture();
        getActivity().runOnUiThread(()->
        {
            Gson gson = new Gson();
            pictures = gson.fromJson(Data,new TypeToken<List<Picture>>(){}.getType());

            PictureAdapter pictureAdapter = new PictureAdapter(pictures);
            recyclerView.setAdapter(pictureAdapter);
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private String GetPictureId(){

        List<String> PictureIds = new ArrayList<>();

        PictureIds.add("0001/00AN0001,00AO0001");
        PictureIds.add("0031/6LRK0031,6LRI0031");
        PictureIds.add("0003/00AJ0003,0AJQ0003,3LF60003,00B70003,00B50003");

        int id = (int) (Math.random() * 3);

        return PictureIds.get(id);

    }

    private void ParsePicture()  {
        PictureSource pictureSource = new PictureSource();
        try {
            new Thread(()-> {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(pictureSource.getBeginurl() + GetPictureId() + pictureSource.getEndurl())
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
//                    int length = Data.length();
//                    Data = Data.substring(13, length - 1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(1000);
        }
        catch (Exception e)
        {

        }
    }
}
