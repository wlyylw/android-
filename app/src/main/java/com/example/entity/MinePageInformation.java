package com.example.entity;

public class MinePageInformation {
    String title;
    String Information;

    public MinePageInformation(String title, String Information){
        this.title =title;
        this.Information = Information;
    }
    public MinePageInformation(String title){
        this.title =title;
        this.Information = "";
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }
}
