package com.ftd.keal.keal_mvvm.viewmodel;

import android.content.Context;

/**
 * Created by sdhuang on 16/7/22 14:12.
 */
public class TaskItemViewModel implements ViewModel{
    private Context mContext;

    public TaskItemViewModel(Context context){
        this.mContext = context;
    }

    @Override
    public void destroy() {
        mContext = null;
    }
}
