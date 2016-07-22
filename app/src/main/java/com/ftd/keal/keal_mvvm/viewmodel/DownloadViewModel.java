package com.ftd.keal.keal_mvvm.viewmodel;

import android.content.Context;

import com.ftd.keal.keal_mvvm.ui.interfaces.onDataLoadedListener;

import rx.Subscription;

/**
 * Created by sdhuang on 16/7/22 11:06.
 */
public class DownloadViewModel implements ViewModel{
    private static final String TAG = "NewsListViewModel";
    private Context context;
    private Subscription subscription;
    private onDataLoadedListener listener;
//    public ObservableBoolean isRefreshing;       //下拉刷新
//    public ObservableInt progressVisibility;     //加载中
    //public ObservableBoolean progressRefreshing = new ObservableBoolean(true);

    public DownloadViewModel(Context context, onDataLoadedListener listener) {
        this.context = context;
        this.listener = listener;
//        isRefreshing = new ObservableBoolean(false);
//        progressVisibility = new ObservableInt(View.VISIBLE);
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = null;
        context = null;
    }
}
