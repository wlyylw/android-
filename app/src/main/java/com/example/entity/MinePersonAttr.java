package com.example.entity;

public class MinePersonAttr {
    String name;
    String detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public MinePersonAttr(String name, String detail)
    {
        this.name = name;
        this.detail = detail;
    }
}
