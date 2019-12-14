package com.example.news;


public class PictureSource {

    private String beginurl = "http://pic.news.163.com/photocenter/api/list/";
    private String endurl = "/0/20.json";

    private static PictureSource pictureSource = null;

    public static PictureSource getInstance()
    {
        if(pictureSource == null)
        {
            pictureSource = new PictureSource();
        }
        return pictureSource;
    }

    public String getBeginurl() {
        return beginurl;
    }

    public void setBeginurl(String beginurl) {
        this.beginurl = beginurl;
    }

    public String getEndurl() {
        return endurl;
    }

    public void setEndurl(String endurl) {
        this.endurl = endurl;
    }
}
