package com.ftd.keal.keal_mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.ftd.keal.keal_mvvm.model.NewsListResponse;
import com.ftd.keal.keal_mvvm.retrofit.NewsRetrofitProvider;
import com.ftd.keal.keal_mvvm.retrofit.NewsService;
import com.ftd.keal.keal_mvvm.ui.interfaces.onDataLoadedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sdhuang on 16/6/22 11:06.
 */
public class NewsListViewModel implements ViewModel{
    private static final String TAG = "NewsListViewModel";
    private Context context;
    private Subscription subscription;
    private onDataLoadedListener listener;
    private NewsListResponse NewsResponse;
    public ObservableBoolean isRefreshing;       //下拉刷新
    public ObservableInt progressVisibility;     //加载中
    //public ObservableBoolean progressRefreshing = new ObservableBoolean(true);

    public NewsListViewModel(Context context,onDataLoadedListener listener) {
        this.context = context;
        this.listener = listener;
        isRefreshing = new ObservableBoolean(false);
        progressVisibility = new ObservableInt(View.VISIBLE);
        loadNews(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()));
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = null;
        context = null;
    }

    private void loadNews(String date){
        subscription = NewsRetrofitProvider.getInstance().create(NewsService.class)
                .getNewsList(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsListResponse>() {
                    @Override
                    public void onCompleted() {
                        progressVisibility.set(View.GONE);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e(TAG, "Error loading response ", error);
                        progressVisibility.set(View.GONE);
                    }

                    @Override
                    public void onNext(NewsListResponse response) {
                        Log.i(TAG, "response loaded " + response);
                        NewsResponse = response;
                        listener.onLoaded(response);
                    }
                });
    }

//    public final ReplyCommand onRefreshCommand = new ReplyCommand<>(() -> {
//        Observable.just(Calendar.getInstance())
//                .doOnNext(c -> c.add(Calendar.DAY_OF_MONTH, 1))
//                .map(c -> NewsListHelper.DAY_FORMAT.format(c.getTime()))
//                .subscribe(d -> loadTopNews(d));
//    });
//
//    public final ReplyCommand<Integer> onLoadMoreCommand = new ReplyCommand<Integer>(new Action1<Integer>(){
//        @Override
//        public void call(Integer integer) {
//            loadNews(NewsResponse.getDate());
//        }
//    });

}
