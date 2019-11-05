package com.example.entity;

import java.util.Date;

public class MinePagePerson {
    String name;
    String sex;
    Date birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
