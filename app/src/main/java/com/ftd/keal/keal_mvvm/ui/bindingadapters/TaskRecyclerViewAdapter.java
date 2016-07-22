package com.ftd.keal.keal_mvvm.ui.bindingadapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ftd.keal.keal_mvvm.R;
import com.ftd.keal.keal_mvvm.databinding.FragmentNewsItemBinding;
import com.ftd.keal.keal_mvvm.model.NewsBean;
import com.ftd.keal.keal_mvvm.viewmodel.NewsItemViewModel;

import java.util.List;

/**
 *
 */
public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {
    private List<NewsBean> mList;

    public TaskRecyclerViewAdapter(List<NewsBean> list) {
        this.mList = list;
    }

    public void setData(List<NewsBean> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         FragmentNewsItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.fragment_news_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindRepository(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final FragmentNewsItemBinding binding;

        public ViewHolder(FragmentNewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindRepository(NewsBean newsBean) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new NewsItemViewModel(itemView.getContext(), newsBean));
            } else {
                binding.getViewModel().setData(newsBean);
            }
        }
    }
}
