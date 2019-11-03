package com.service;



import com.example.entity.MineDynamic;

import java.util.ArrayList;
import java.util.Random;

public class MinePageDynamicService {

    private static MinePageDynamicService instance;

    public  static MinePageDynamicService getInstance() {
        if(instance==null)
        {
            return new MinePageDynamicService();
        }
        return  instance;
    }

    ArrayList<MineDynamic> list;

    public ArrayList<MineDynamic> getList()
    {
        return list;
    }

    private MinePageDynamicService()
    {
        list =new ArrayList<>();
        InitEntity();
    }
    private void InitEntity()
    {
        Random  random = new Random();
        MineDynamic minedynamic1 = new MineDynamic("动态",random.nextInt(10));
        MineDynamic minedynamic2 = new MineDynamic("跟帖",random.nextInt(10));
        MineDynamic minedynamic3 = new MineDynamic("收藏",random.nextInt(10));
        MineDynamic minedynamic4 = new MineDynamic("历史",random.nextInt(10));
        list.add(minedynamic1);
        list.add(minedynamic2);
        list.add(minedynamic3);
        list.add(minedynamic4);
    }
}
