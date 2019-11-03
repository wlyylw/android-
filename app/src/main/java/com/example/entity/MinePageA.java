package com.example.entity;

public class MinePageA {
    String name;
    String information;

    public MinePageA(){

    }

    public MinePageA(String name, String information)
    {
        this.name = name;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
