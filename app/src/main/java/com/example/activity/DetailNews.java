package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.ConcreteNews;
import com.example.news.NewsSource;
import com.example.news.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailNews extends AppCompatActivity {
    TextView title;
    TextView textcontent;
    ImageView imageView;
    String Data = null;
    List<ConcreteNews> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        title = findViewById(R.id.detail_news_title);
        String docid = getIntent().getStringExtra("docid");
        NewsSource newsSource = NewsSource.getInstance();
        String url = newsSource.getDetail_front()+docid+"/full.html";
        textcontent = findViewById(R.id.detail_news_content);
        SendRequest(url);
        runOnUiThread(()->{
//            title.setText(Data);
            //TODO:处理Json
//            Data = Data.substring(0,Data.length()-1);
            if (Data.length()>30) { //..TODO：太懒了
                Gson gson = new Gson();
                ConcreteNews concreteNews = new ConcreteNews();
                concreteNews = gson.fromJson(Data, ConcreteNews.class);
                title.setText(concreteNews.getTitle());
                String s = concreteNews.getBody();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    textcontent.setText(Html.fromHtml(s,Html.FROM_HTML_MODE_COMPACT));
                }else {
                    textcontent.setText(Html.fromHtml(s));
                }
//                textcontent.setText(s);
            }
            else
                title.setText("资源未找到");
        });

    }

    public void On_Quit_DeatailNews(View view) {
        finish();
    }
    private void SendRequest(String detail_url) {

        try {
            new Thread(()-> {

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(detail_url)//不同种类
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
                    Data = Data.substring(20, length-1 );   //TODO:这么写有问题
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(500);
        }
        catch (Exception e){

        }
    }
}
