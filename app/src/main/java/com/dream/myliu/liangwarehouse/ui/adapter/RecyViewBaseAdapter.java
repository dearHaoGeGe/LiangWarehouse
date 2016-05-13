package com.dream.myliu.liangwarehouse.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Amethyst on 16/1/10/15/24.
 */
public abstract class  RecyViewBaseAdapter<T extends List> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected T datas;

    protected void initData(T datas) {

    }

    private boolean isEntityEmpty() {
        return datas != null && datas.size() > 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return isEntityEmpty() ? datas.size() : 0;
    }


}
