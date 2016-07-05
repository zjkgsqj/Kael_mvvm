package com.ftd.keal.keal_mvvm.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ftd.keal.keal_mvvm.R;
import com.ftd.keal.keal_mvvm.databinding.FragmentNewsListBinding;
import com.ftd.keal.keal_mvvm.model.NewsBean;
import com.ftd.keal.keal_mvvm.model.NewsListResponse;
import com.ftd.keal.keal_mvvm.ui.interfaces.onDataLoadedListener;
import com.ftd.keal.keal_mvvm.viewmodel.NewsListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class NewsListFragment extends Fragment implements onDataLoadedListener {
    private FragmentNewsListBinding binding;
    private NewsListViewModel viewModel;
    private List<NewsBean> mList;

    public static NewsListFragment newInstance() {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_news_list, container, false);
        viewModel = new NewsListViewModel(getActivity(),this);
        binding.setViewModel(viewModel);
        mList = new ArrayList<>();
        MyNewsRecyclerViewAdapter adapter = new MyNewsRecyclerViewAdapter(mList);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
    }

    @Override
    public void onLoaded(NewsListResponse response) {
        MyNewsRecyclerViewAdapter adapter = (MyNewsRecyclerViewAdapter) binding.recyclerView.getAdapter();
        adapter.setData(response.getStories());
    }

}
