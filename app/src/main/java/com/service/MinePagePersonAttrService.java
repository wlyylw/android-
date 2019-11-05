package com.service;



import com.example.entity.MinePersonAttr;

import java.util.ArrayList;

public class MinePagePersonAttrService {

    private static MinePagePersonAttrService instance;

    public  static MinePagePersonAttrService getInstance() {
        if(instance==null)
        {
            return new MinePagePersonAttrService();
        }
        return  instance;
    }

    ArrayList<MinePersonAttr> list;

    public ArrayList<MinePersonAttr> getList()
    {
        return list;
    }

    private MinePagePersonAttrService()
    {
        list =new ArrayList<>();
        InitEntity();
    }
    private void InitEntity()
    {
        MinePersonAttr minePersonAttr1 = new MinePersonAttr("昵称","五月她如景");
        list.add(minePersonAttr1);
        MinePersonAttr minePersonAttr2 = new MinePersonAttr("性别","女");
        list.add(minePersonAttr2);
        MinePersonAttr minePersonAttr3 = new MinePersonAttr("生日","");
        list.add(minePersonAttr3);

    }

}
