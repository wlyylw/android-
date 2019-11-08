package com.example.entity;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class MinePagePerson  extends LitePalSupport {
    String name;
    String sex;
    String birthday;
    String phonenumber;
    String password;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public MinePagePerson() {

    }
    public MinePagePerson(String name)
    {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

}
