package com.ftd.keal.keal_mvvm.ui.bindingadapters;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.drawee.view.SimpleDraweeView;

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
}
