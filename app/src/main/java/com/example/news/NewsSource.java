package com.example.news;

public class NewsSource {

    private String baseurl = "https://3g.163.com";
    private String news = "/touch/reconstruct/article/list/BBM54PGAwangning/0-10.html";
    private String amusement = "/touch/reconstruct/article/list/BA10TA81wangning/0-10.html";
    private String sports = "/touch/reconstruct/article/list/BA8E6OEOwangning/0-10.html";
    private String economy = "/touch/reconstruct/article/list/BA8EE5GMwangning/0-10.html";
    private String military = "/touch/reconstruct/article/list/BAI67OGGwangning/0-10.html";
    private String technology = "/touch/reconstruct/article/list/BA8D4A3Rwangning/0-10.html";
    private String phone = "/touch/reconstruct/article/list/BAI6I0O5wangning/0-10.html";
    private String fashion = "/touch/reconstruct/article/list/BAI6JOD9wangning/0-10.html";
    private String game = "/touch/reconstruct/article/list/BAI6RHDKwangning/0-10.html";
    private String education = "/touch/reconstruct/article/list/BA8FF5PRwangning/0-10.html";
    private String health = "/touch/reconstruct/article/list/BDC4QSV3wangning/0-10.html";
    private String travel = "/touch/reconstruct/article/list/BEO4GINLwangning/0-10.html";
    private String video = "https://c.m.163.com/nc/video/list/VAP4BFE3U/y/0-10.html";  //视频



    private String detail_front ="http://c.m.163.com/nc/article/";//加id + /full.html
    private static NewsSource newsSource = null;

    public static NewsSource getInstance()
    {
        if(newsSource == null)
        {
            newsSource = new NewsSource();
        }
        return newsSource;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public String getNews() {
        return  news;
    }

    public String getAmusement() {
        return amusement;
    }

    public String getSports() {
        return sports;
    }

    public String getEconomy() {
        return  economy;
    }

    public String getMilitary() {
        return  military;
    }

    public String getTechnology() {
        return technology;
    }

    public String getPhone() {
        return phone;
    }

    public String getFashion() {
        return fashion;
    }

    public String getGame() {
        return  game;
    }

    public String getEducation() {
        return education;
    }

    public String getHealth() {
        return health;
    }

    public String getTravel() {
        return travel;
    }


    public String getVideo() {

        return  video;
    }
    public String getDetail_front() {
        return detail_front;
    }
}
