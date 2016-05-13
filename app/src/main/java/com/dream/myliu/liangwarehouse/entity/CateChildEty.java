package com.dream.myliu.liangwarehouse.entity;

import com.dream.myliu.liangwarehouse.greendao.CateListDataEty;

import java.util.List;

/**
 * Created by Amethyst on 16/1/20/10/02.
 */
public class CateChildEty {
    private List<CateListDataEty>  data;

    public List<CateListDataEty> getData() {
        return data;
    }

    public void setData(List<CateListDataEty> data) {
        this.data = data;
    }
}
