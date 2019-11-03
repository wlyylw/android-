package com.service;

import com.example.entity.MinePageInformation;

import java.util.ArrayList;

public class MineListService {
    ArrayList<MinePageInformation> list;
    private static MineListService instance;
    public static MineListService getInstance() {
        if(instance==null)
            instance = new MineListService();
        return instance;
    }
    private MineListService(){
        list = new ArrayList<MinePageInformation>();
        InitEntity();
    }
    private void InitEntity(){
        MinePageInformation Information1 =new MinePageInformation("我的关注","用户/圈子");
        MinePageInformation Information2 =new MinePageInformation("我的消息","回复/赞/通知");
//        MinePageInformation Information3 =new MinePageInformation("我的关注","购买的课程/直播");
//        MinePageInformation Information4 =new MinePageInformation("金币商城","0元好物限时抢");
//        MinePageInformation Information5 =new MinePageInformation("京东特供","新人领188元红包");
//        MinePageInformation Information6 =new MinePageInformation("易览天下","三分钟了解天下事");
//        MinePageInformation Information7 =new MinePageInformation("用户鉴帖","邀请你鉴定跟帖质量");
//        MinePageInformation Information8 =new MinePageInformation("我的钱包");
//        MinePageInformation Information9 =new MinePageInformation("免流量看新闻");
//        MinePageInformation Information10 =new MinePageInformation("意见反馈");
//        MinePageInformation Information11 =new MinePageInformation("扫一扫");
//        MinePageInformation Information12 =new MinePageInformation("设置");
        list.add(Information1);
        list.add(Information2);
//        list.add(Information3);
//        list.add(Information4);
//        list.add(Information5);
//        list.add(Information6);
//        list.add(Information7);
//        list.add(Information8);
//        list.add(Information9);
//        list.add(Information10);
//        list.add(Information11);
//        list.add(Information12);
    }
    public ArrayList<MinePageInformation> getMinePageList(){return  list;}

}
