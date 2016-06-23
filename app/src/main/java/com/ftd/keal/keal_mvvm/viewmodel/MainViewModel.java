package com.ftd.keal.keal_mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

/**
 * Created by sdhuang on 16/6/22 11:06.
 */
public class MainViewModel implements ViewModel{
    private Context context;
    public final ObservableField<String> mainText;
    public MainViewModel(Context context) {
        this.context = context;
        this.mainText = new ObservableField<>();
        //setText();
    }

    @Override
    public void destroy() {
        context = null;
    }

    private void setText(){
        mainText.set("Keal MVVM");
    }
}
