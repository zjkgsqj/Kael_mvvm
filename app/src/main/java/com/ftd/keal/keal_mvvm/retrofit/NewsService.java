package com.ftd.keal.keal_mvvm.retrofit;

import com.ftd.keal.keal_mvvm.model.NewsListResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by sdhuang on 16/7/4 14:31.
 */
public interface NewsService {
    @GET("/api/4/news/before/{date}")
    public Observable<NewsListResponse> getNewsList(@Path("date") String date);
}
