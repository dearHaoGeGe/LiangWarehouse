package com.dream.myliu.liangwarehouse.entity;

import com.dream.myliu.liangwarehouse.greendao.SearchData;

import java.util.List;

/**
 * Created by Amethyst on 16/1/17/22/09.
 */
public class SearchEty {
    private List<SearchData> data;

    public List<SearchData> getData() {
        return data;
    }

    public void setData(List<SearchData> data) {
        this.data = data;
    }
}
