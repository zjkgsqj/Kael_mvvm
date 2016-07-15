package com.ftd.keal.keal_mvvm.ui.bindingadapters;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.webkit.WebView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ftd.keal.keal_mvvm.retrofit.command.ViewAction;

/**
 * Created by Administrator on 16/7/5 14:32.
 */
public class ViewBindingAdapter {
    @BindingAdapter({"uri"})
    public static void setImageUri(SimpleDraweeView simpleDraweeView, String uri) {
        if (!TextUtils.isEmpty(uri)) {
            simpleDraweeView.setImageURI(Uri.parse(uri));
        }
    }

    @BindingAdapter({"onRefreshAction"})
    public static void onRefreshAction(SwipeRefreshLayout swipeRefreshLayout, final ViewAction viewAction) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (viewAction != null) {
                    viewAction.call();
                }
            }
        });
    }

    @BindingAdapter({"render"})
    public static void loadHtml(WebView webView, final String html) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        }
    }
}
