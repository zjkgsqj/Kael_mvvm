package com.ftd.keal.keal_mvvm.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ftd.keal.keal_mvvm.R;
import com.ftd.keal.keal_mvvm.databinding.ActivityDownloadBinding;
import com.ftd.keal.keal_mvvm.model.NewsListResponse;
import com.ftd.keal.keal_mvvm.ui.adapters.TaskItemAdapter;
import com.ftd.keal.keal_mvvm.ui.download.TasksManager;
import com.ftd.keal.keal_mvvm.ui.interfaces.onDataLoadedListener;
import com.ftd.keal.keal_mvvm.viewmodel.DownloadViewModel;

import java.lang.ref.WeakReference;

public class DownloadActivity extends AppCompatActivity implements onDataLoadedListener {
    private ActivityDownloadBinding binding;
    private DownloadViewModel viewModel;
    private TaskItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_download);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_download);
        viewModel = new DownloadViewModel(this,this);
        binding.setViewModel(viewModel);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new TaskItemAdapter());


        TasksManager.getImpl().onCreate(new WeakReference<>(DownloadActivity.this));
    }

    @Override
    public void onLoaded(NewsListResponse response) {

    }

    public void postNotifyDataChanged() {
        if (adapter != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
        //TasksManager.getImpl().onDestroy();
        adapter = null;
        //FileDownloader.getImpl().pauseAll();
    }
}
