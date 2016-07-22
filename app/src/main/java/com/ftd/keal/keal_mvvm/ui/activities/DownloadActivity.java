package com.ftd.keal.keal_mvvm.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ftd.keal.keal_mvvm.R;
import com.ftd.keal.keal_mvvm.databinding.ActivityDownloadBinding;
import com.ftd.keal.keal_mvvm.model.NewsListResponse;
import com.ftd.keal.keal_mvvm.ui.interfaces.onDataLoadedListener;
import com.ftd.keal.keal_mvvm.viewmodel.DownloadViewModel;

public class DownloadActivity extends AppCompatActivity implements onDataLoadedListener {
    private ActivityDownloadBinding binding;
    private DownloadViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_download);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_download);
        viewModel = new DownloadViewModel(this,this);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onLoaded(NewsListResponse response) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
    }
}
