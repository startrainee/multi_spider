package com.startrainee.spider.data;

public class News {
    private String date;
    private String URL;
    private String title;
    private String summary;

    public News(String date, String URL, String title, String summary) {
        this.date = date;
        this.URL = URL;
        this.title = title;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }


    public String getDate() {
        return date;
    }


    public String getURL() {
        return URL;
    }

    public String getSummary() {
        return summary;
    }

    public String toString(){
        return "["+ getDate() +
                "$"+ getURL() +
                "$"+ getTitle() +
                "$"+ getSummary() +"]";
    }
}
