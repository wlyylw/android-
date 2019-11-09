package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.entity.MinePagePerson;
import com.example.fragment.CirclePage;
import com.example.fragment.IndexPage;
import com.example.fragment.MinePage;
import com.example.fragment.VideoPage;
import com.example.news.R;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.service.MinePagePersonService;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment  = new Fragment();
    private IndexPage indexPage = new IndexPage();
    private VideoPage videoPage = new VideoPage();
    private MinePage minePage = new MinePage();
    private CirclePage circlePage = new CirclePage();
    int page_id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LitePal.getDatabase();
        BottomInit();
    }


    @Override
    protected  void onResume() {
        super.onResume();
        page_id = getIntent().getIntExtra("page_id", 0);
        ChooseWhichFragment();
    }

    private void switchFragment(Fragment targetFragment){
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {//如果要显示的targetFragment没有添加过
            transaction
                    .hide(currentFragment)//隐藏当前Fragment
                    .add(R.id.main_frame_content, targetFragment,targetFragment.getClass().getName())//添加targetFragment
                    .commit();
        } else {//如果要显示的targetFragment已经添加过
            transaction//隐藏当前Fragment
                    .hide(currentFragment)
                    .show(targetFragment)//显示targetFragment
                    .commit();
        }
        //更新当前Fragment为targetFragment
        currentFragment = targetFragment;

    }
    private void switchToIndex() {
        switchFragment(indexPage);
    }
    private void switchToVideo(){
        switchFragment(videoPage);
    }
    private void switchToCircle(){
        switchFragment(circlePage);
    }
    private void switchToMine(){
        MinePagePersonService minePagePersonService = MinePagePersonService.getInstance();
        if(!minePagePersonService.isLogined())
        {
            Toast.makeText(this, "您尚未登录！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            //TODO:可以考虑再传参数..再写的话
            return;
        }
        switchFragment(minePage);
    }
    private void BottomInit() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.Bottom_Layout);
        BottomNavigationItem itemindex  = new BottomNavigationItem("首页", ContextCompat.getColor(this, R.color.colorActive), R.drawable.ic_indexpage);
        BottomNavigationItem itemvideo  = new BottomNavigationItem   ("视频", ContextCompat.getColor(this, R.color.colorActive), R.drawable.ic_videopage);
        BottomNavigationItem itemcircle = new BottomNavigationItem   ("圈子", ContextCompat.getColor(this, R.color.colorActive), R.drawable.ic_circlepage);
        BottomNavigationItem itemmine   = new BottomNavigationItem   ("我的", ContextCompat.getColor(this, R.color.colorActive), R.drawable.ic_minepage);
        bottomNavigationView.addTab(itemindex);
        bottomNavigationView.addTab(itemvideo);
        bottomNavigationView.addTab(itemcircle);
        bottomNavigationView.addTab(itemmine);
        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                switch (index)
                {
                    case 0:
                        switchToIndex();
                        break;
                    case 1:
                        switchToVideo();
                        break;
                    case 2:
                        switchToCircle();
                        break;
                    case 3:
                        switchToMine();
                        break;
                    default:
                        break;
                }
            }
        });
        switchToIndex();//default
    }
    private  void ChooseWhichFragment()
    {
        switch (page_id)
        {
            case 0:
                switchToIndex();
                break;
            case 1:
                switchToVideo();
                break;
            case 2:
                switchToCircle();
                break;
            case 3:
                switchToMine();
                break;
                default:
                    break;
        }
    }


}
