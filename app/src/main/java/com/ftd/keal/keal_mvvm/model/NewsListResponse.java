package com.ftd.keal.keal_mvvm.model;

import java.util.List;

/**
 * Created by sdhuang on 16/7/4 14:14.
 */
public class NewsListResponse {
    private String date;

    private List<NewsBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<NewsBean> getStories() {
        return stories;
    }

    public void setStories(List<NewsBean> stories) {
        this.stories = stories;
    }
}
