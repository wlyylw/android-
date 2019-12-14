package com.example.entity;


public class Video {
    private String videosource; //作者
    private String title;   //标题
    private String cover;   //封面连接
    private String vid;     //视频ID

    public String getVideosource() {
        return videosource;
    }

    public String getTitle() {
        return title;
    }

    public String getVid() {
        return vid;
    }

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public String getPlayCount() {
        return playCount;
    }

    public String getVotecount() {
        return votecount;
    }

    private String mp4Hd_url; //视频连接
    private String playCount;//播放次数
    private String votecount; //点赞数

    private String mp4_url;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }
}
