package com.lungesoft.larch.models;

import java.util.ArrayList;

public class ResponseList<T extends News> {
    private long time;
    private long totalHits;
    private ArrayList<T> news;

    public ResponseList() {
    }

    public ResponseList(long time, long totalHits, ArrayList<T> news) {
        this.time = time;
        this.totalHits = totalHits;
        this.news = news;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ArrayList<T> getNews() {
        return news;
    }

    public void setNews(ArrayList<T> news) {
        this.news = news;
    }

    public long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(long totalHits) {
        this.totalHits = totalHits;
    }
}
