package com.service;



import com.example.entity.MinePageInformation;

import java.util.ArrayList;

public class MinePageInformationService {

    private static MinePageInformationService instance;

    public  static MinePageInformationService getInstance() {
        if(instance==null)
        {
            return new MinePageInformationService();
        }
        return  instance;
    }

    ArrayList<MinePageInformation> list;

    public ArrayList<MinePageInformation> getList()
    {
        return list;
    }

    private MinePageInformationService()
    {
        list =new ArrayList<>();
        InitEntity();
    }
    private void InitEntity()
    {
        MinePageInformation minePageA1 = new MinePageInformation("我的关注","用户/圈子");
        list.add(minePageA1);
        MinePageInformation minePageA2 = new MinePageInformation("我的消息","回复/赞/通知");
        list.add(minePageA2);
        MinePageInformation minePageA3 = new MinePageInformation("我的已购","购买的课程/直播");
        list.add(minePageA3);
        MinePageInformation minePageA4 = new MinePageInformation("金币商城","0元好物限时抢");
        list.add(minePageA4);
        MinePageInformation minePageA5 = new MinePageInformation("京东特供","新人领188元红包");
        list.add(minePageA5);
        MinePageInformation minePageA6 = new MinePageInformation("易览天下","三分钟了解天下事");
        list.add(minePageA6);
        MinePageInformation minePageA7 = new MinePageInformation("用户鉴帖","邀你鉴定跟帖质量");
        list.add(minePageA7);
        MinePageInformation minePageA8 = new MinePageInformation("我的钱包","");
        list.add(minePageA8);
        MinePageInformation minePageA9 = new MinePageInformation("免流量看新闻","");
        list.add(minePageA9);
        MinePageInformation minePageA = new MinePageInformation("意见反馈","");
        list.add(minePageA);
        MinePageInformation minePageAB = new MinePageInformation("扫一扫","");
        list.add(minePageAB);
        MinePageInformation minePageAC = new MinePageInformation("设置","");
        list.add(minePageAC);
    }

}
