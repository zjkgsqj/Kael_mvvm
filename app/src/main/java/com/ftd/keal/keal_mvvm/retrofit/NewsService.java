package com.ftd.keal.keal_mvvm.retrofit;

import com.ftd.keal.keal_mvvm.model.NewsDetailBean;
import com.ftd.keal.keal_mvvm.model.NewsListResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by sdhuang on 16/7/4 14:31.
 */
public interface NewsService {
    @GET("/api/4/news/before/{date}")
    public Observable<NewsListResponse> getNewsList(@Path("date") String date);

    @GET("/api/4/news/{id}")
    public Observable<NewsDetailBean> getNewsDetail(@Path("id") long id);

    @GET
    Observable<String> getNewsDetailCss(@Url String url);
}
