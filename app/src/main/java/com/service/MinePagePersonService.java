package com.service;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.activity.LoginActivity;
import com.example.activity.MainActivity;
import com.example.entity.MinePagePerson;


import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class MinePagePersonService {
    ArrayList<MinePagePerson> list = new ArrayList<>();
    boolean logined;
    String phonenumber;
    String passwrod;
    MinePagePerson minePagePerson;
    public void setList(ArrayList<MinePagePerson> list) {
        this.list = list;
    }


    public MinePagePerson getMinePagePerson() {
        return this.minePagePerson;
    }

    public void setMinePagePerson(MinePagePerson minePagePerson) {
        this.minePagePerson = minePagePerson;

    }

    public boolean Login(String phonenumber, String passwrod)
    {
        this.passwrod = passwrod;
        this.phonenumber = phonenumber;
        logined = true;
        return true;
    }
    public boolean isLogined()
    {
        return logined;
    }
    public String getPhonenumber()
    {
        return  phonenumber;
    }
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
        //TODO此处还要更改为读取.
        minePagePerson = new MinePagePerson();
        minePagePerson.setBirthday("");
        minePagePerson.setName("");
        minePagePerson.setSex("");
        minePagePerson.setPhonenumber(phonenumber);
        minePagePerson.setPassword(passwrod);
        list.add(minePagePerson);
    }

    public void setLogined(boolean logined) {
        this.logined = logined;
    }

    public void UpdateLitePalData()
    {
        List<MinePagePerson> listLitePal;
        listLitePal =  LitePal.findAll(MinePagePerson.class);
        for(MinePagePerson minePagePerson: listLitePal) {
            if (minePagePerson.getPhonenumber().equals(this.minePagePerson.getPhonenumber())) {
                if (minePagePerson.getPassword().equals(this.minePagePerson.getPassword())) {
                    //TODO：完成更新的逻辑
                    minePagePerson.setSex(this.minePagePerson.getSex());
                    minePagePerson.setName(this.minePagePerson.getName());
                    minePagePerson.setBirthday(this.minePagePerson.getBirthday());
                    return;
                }
            }
        }
    }

}
