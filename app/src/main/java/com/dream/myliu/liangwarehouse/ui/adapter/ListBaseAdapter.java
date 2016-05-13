package com.dream.myliu.liangwarehouse.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Amethyst on 16/1/10/14/31.
 */
public abstract class ListBaseAdapter<T extends List> extends BaseAdapter {
    protected T entities;

    private boolean isEntityEmpty() {
        return entities != null && entities.size() > 0;
    }
    abstract protected void addData(T datas, boolean isRefresh);

    @Override
    public int getCount() {
        return isEntityEmpty() ? entities.size() : 0;
    }

    protected void addRefreshData(T ts) {
        addData(ts, true);

    }
    protected void addLoadDatas(T ts){
        addData(ts, false);
    }
    @Override
    public Object getItem(int position) {
        return isEntityEmpty() ? entities.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return convertView;
    }

}
