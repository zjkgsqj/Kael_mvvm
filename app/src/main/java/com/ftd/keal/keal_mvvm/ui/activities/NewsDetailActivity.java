package com.ftd.keal.keal_mvvm.ui.activities;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ftd.keal.keal_mvvm.BR;
import com.ftd.keal.keal_mvvm.R;
import com.ftd.keal.keal_mvvm.viewmodel.NewsDetailViewModel;

public class NewsDetailActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_NEWS_ID = "key_news_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long id = getIntent().getLongExtra(EXTRA_KEY_NEWS_ID, -1);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);
        binding.setVariable(BR.viewModel, new NewsDetailViewModel(this, id));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedText);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedTitleText);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
