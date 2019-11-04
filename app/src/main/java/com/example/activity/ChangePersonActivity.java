package com.example.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.R;

public class ChangePersonActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_person);
        SetObj();
        ListenerManager();

    }
    private void ListenerManager()
    {
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void SetObj()
    {
        toolbar   = findViewById(R.id.change_person_toolbar);
        imageView = findViewById(R.id.mine_picture_second);
    }

    public void On_Change_picture(View view) {
        //TODO:照片相关
    }
}
