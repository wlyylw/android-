package com.example.entity;

public class News {

    private String source; //新闻来源
    private String title; //标题
    private String commentCount; //评论数
    private String imgsrc3gtype; //三张图的参数不太一样?
    private String digest;  //摘要
    private String imgsrc;  //图片来源
    private String ptime;   //发帖时间
    private String url;
    private String docid;

    public String getDocid() {
        return docid;
    }

    public String getUrl() {
        return url;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public String getImgsrc3gtype() {
        return imgsrc3gtype;
    }

    public String getDigest() {
        return digest;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public String getPtime() {
        return ptime;
    }
}
