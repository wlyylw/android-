package com.service;



import com.example.entity.MinePageA;

import java.util.ArrayList;

public class MinePageService {

    private static MinePageService instance;

    public  static MinePageService getInstance() {
        if(instance==null)
        {
            return new MinePageService();
        }
        return  instance;
    }

    ArrayList<MinePageA> list;

    public ArrayList<MinePageA> getList()
    {
        return list;
    }

    private MinePageService()
    {
        list =new ArrayList<>();
        InitEntity();
    }
    private void InitEntity()
    {
        MinePageA minePageA1 = new MinePageA("我的关注","用户/圈子");
        list.add(minePageA1);
        MinePageA minePageA2 = new MinePageA("我的消息","回复/赞/通知");
        list.add(minePageA2);
        MinePageA minePageA3 = new MinePageA("我的已购","购买的课程/直播");
        list.add(minePageA3);
        MinePageA minePageA4 = new MinePageA("金币商城","0元好物限时抢");
        list.add(minePageA4);
        MinePageA minePageA5 = new MinePageA("京东特供","新人领188元红包");
        list.add(minePageA5);
        MinePageA minePageA6 = new MinePageA("易览天下","三分钟了解天下事");
        list.add(minePageA6);
        MinePageA minePageA7 = new MinePageA("用户鉴帖","邀你鉴定跟帖质量");
        list.add(minePageA7);
        MinePageA minePageA8 = new MinePageA("我的钱包","");
        list.add(minePageA8);
        MinePageA minePageA9 = new MinePageA("免流量看新闻","");
        list.add(minePageA9);
        MinePageA minePageA = new MinePageA("意见反馈","");
        list.add(minePageA);
        MinePageA minePageAB = new MinePageA("扫一扫","");
        list.add(minePageAB);
        MinePageA minePageAC = new MinePageA("设置","");
        list.add(minePageAC);
    }

}
