package com.service;

import com.example.entity.MinePagePerson;


import java.util.ArrayList;

public class MinePagePersonService {
    ArrayList<MinePagePerson> list;

    private static  MinePagePersonService instance;

    public static MinePagePersonService getInstance() {
        if(instance==null)
            instance = new MinePagePersonService();
        return  instance;
    }

    public ArrayList<MinePagePerson> getList(){
        return list;
    }

    private MinePagePersonService()
    {
        list = new ArrayList<>();
        InitEntity();
    }

    private void InitEntity() {
        MinePagePerson minePagePerson = new MinePagePerson("五月她如景");
        list.add(minePagePerson);

    }
}
