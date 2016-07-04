package com.ftd.keal.keal_mvvm.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sdhuang on 16/6/24 16:59.
 */
public class NewsRetrofitProvider {
    private static Retrofit retrofit;

    private NewsRetrofitProvider() {
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://news-at.zhihu.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
