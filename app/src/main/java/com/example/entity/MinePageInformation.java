package com.example.entity;

public class MinePageInformation {
    String name;
    String information;


    public MinePageInformation(String name, String information)
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

}
