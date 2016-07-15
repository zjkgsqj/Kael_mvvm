package com.ftd.keal.keal_mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.ftd.keal.keal_mvvm.model.NewsDetailBean;
import com.ftd.keal.keal_mvvm.retrofit.NewsRetrofitProvider;
import com.ftd.keal.keal_mvvm.retrofit.NewsService;
import com.ftd.keal.keal_mvvm.retrofit.command.ViewAction;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 16/7/13 15:57.
 */
public class NewsDetailViewModel implements ViewModel{
    private static final String TAG = "NewsDetailViewModel";
    private Context mContext;
    private long mId;
    private NewsDetailBean newsDetail;
    private Subscription subscription;
    public ObservableBoolean isRefreshing = new ObservableBoolean(false); //下拉刷新
    public ObservableInt progressVisibility = new ObservableInt(View.VISIBLE);     //加载中
    public ObservableField<String> imageUrl = new ObservableField<>();
    public ObservableField<String> html = new ObservableField<>();
    public ObservableField<String> title = new ObservableField<>();

    public NewsDetailViewModel(Context context,long id){
        this.mContext = context;
        this.mId = id;
        loadData(id);
    }

    public ViewAction onRefreshAction = new ViewAction(new Action0() {
        @Override
        public void call() {
            isRefreshing.set(true);
            loadData(mId);
        }
    });

    private void loadData(long id) {
        subscription = NewsRetrofitProvider.getInstance().create(NewsService.class)
                .getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsDetailBean>() {
                    @Override
                    public void onCompleted() {
                        progressVisibility.set(View.GONE);
                        if(isRefreshing.get()){
                            isRefreshing.set(false);
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e(TAG, "Error loading response ", error);
                        progressVisibility.set(View.GONE);
                        if(isRefreshing.get()){
                            isRefreshing.set(false);
                        }
                    }

                    @Override
                    public void onNext(NewsDetailBean response) {
                        Log.i(TAG, "response loaded " + response);
                        newsDetail = response;
                        loadHtmlCss(newsDetail.getCss());
                    }
                });
    }

    private void loadHtmlCss(List<String> urls) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new Converter<ResponseBody, String>(){
                            @Override
                            public String convert(ResponseBody value) throws IOException {
                                return value.string();
                            }
                        };
                    }
                }).build();

        Observable.from(urls)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return retrofit.create(NewsService.class)
                                .getNewsDetailCss(s)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());

                    }
                })
                .scan(new Func2<String, String, String>() {
                    @Override
                    public String call(String s, String s2) {
                        return s + s2;
                    }
                })
                .last()
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        newsDetail.setCssStr(s);
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        progressVisibility.set(View.GONE);
                        if(isRefreshing.get()){
                            isRefreshing.set(false);
                        }
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        initViewModelField();
                    }
                });
    }


    private void initViewModelField() {
        isRefreshing.set(false);
        imageUrl.set(newsDetail.getImage());
        Observable.just(newsDetail.getBody())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "<style type=\"text/css\">" + newsDetail.getCssStr();
                    }
                })
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "</style>";
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        html.set(s);
                    }
                });
        title.set(newsDetail.getTitle());
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = null;
        mContext = null;
    }
}
