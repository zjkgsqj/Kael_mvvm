package com.ftd.keal.keal_mvvm;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadLog;

/**
 * Created by sdhuang on 16/7/7 13:54.
 */
public class KealMvvmApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        FileDownloadLog.NEED_LOG = true;
        FileDownloader.init(getApplicationContext());
    }
}
