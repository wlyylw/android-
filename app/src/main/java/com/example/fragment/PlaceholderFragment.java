package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.widget.PullRefreshLayout;
import com.example.activity.MainActivity;
import com.example.adapter.NewsAdapter;
import com.example.entity.News;
import com.example.news.NewsSource;
import com.example.news.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    List<News> list;
    RecyclerView recyclerView;
    String Data = null;
    NewsSource newsSource = NewsSource.getInstance() ;
    private PageViewModel pageViewModel;
    int index = 1;
    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        SendRequest(index);
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_index_main, container, false);

        recyclerView = root.findViewById(R.id.display_news);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManagerNews);
        getActivity().runOnUiThread(()->
                {
                    //Data 提取Json中的数据
                    Gson gson = new Gson();
                    list = gson.fromJson(Data,new TypeToken<List<News>>(){}.getType());
                    NewsAdapter newsAdapter = new NewsAdapter(list);
                    recyclerView.setAdapter(newsAdapter);
                }

        );


        return root;
    }
    private void SendRequest(int index) {

        try {
            new Thread(()-> {

                OkHttpClient client = new OkHttpClient();
                String newstype = null;
                switch (index)
                {
                    case 1:
                        newstype = newsSource.getBaseurl() + newsSource.getNews();
                        break;
                    case 2:
                        newstype = newsSource.getBaseurl() + newsSource.getAmusement();
                        break;
                    case 3:
                        newstype = newsSource.getBaseurl() + newsSource.getSports();
                        break;
                    case 4:
                        newstype = newsSource.getBaseurl() + newsSource.getEconomy();
                        break;
                    case 5:
                        newstype = newsSource.getBaseurl() + newsSource.getMilitary();
                        break;
                    case 6:
                        newstype = newsSource.getBaseurl() + newsSource.getTechnology();
                        break;
                    case 7:
                        newstype = newsSource.getBaseurl() + newsSource.getPhone();
                        break;
                    case 8:
                        newstype = newsSource.getBaseurl() + newsSource.getTravel();
                        break;
                    case 9:
                        newstype = newsSource.getBaseurl() +newsSource.getFashion();
                        break;
                    case 10:
                        newstype = newsSource.getBaseurl() + newsSource.getGame();
                    default:
                        break;
                }
                Request request = new Request.Builder()
                        .url(newstype)//不同种类
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
                    Data = Data.substring(29, length - 2);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(1000); //TODO:
        }
        catch (Exception e){

        }
    }

}