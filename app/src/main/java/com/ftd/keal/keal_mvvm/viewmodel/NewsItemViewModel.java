package com.ftd.keal.keal_mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.ftd.keal.keal_mvvm.model.NewsBean;

/**
 * Created by sdhuang on 16/7/5 11:22.
 */
public class NewsItemViewModel implements ViewModel{
    private Context mContext;
    private NewsBean newsBean;
    public ObservableField<String> title;
    public ObservableField<String> imageUrl;
    public ObservableField<String> date;
    public ObservableInt titleTextColor;

    public NewsItemViewModel(Context context,NewsBean newsBean){
        this.mContext = context;
        this.newsBean = newsBean;
        title = new ObservableField<>();
        imageUrl = new ObservableField<>();
        date = new ObservableField<>();
        titleTextColor = new ObservableInt();
        setData(newsBean);
    }

    public void setData(NewsBean bean){
        this.newsBean = bean;
        title.set(newsBean.getTitle());
        imageUrl.set(newsBean.getImages().get(0));
        //date.set(newsBean.getExtraField().getDate());
        titleTextColor.set(mContext.getResources().getColor(android.R.color.black));
    }

    public void onItemClick(View view) {
        titleTextColor.set(mContext.getResources().getColor(android.R.color.darker_gray));
    }

    @Override
    public void destroy() {
        mContext = null;
    }
}
