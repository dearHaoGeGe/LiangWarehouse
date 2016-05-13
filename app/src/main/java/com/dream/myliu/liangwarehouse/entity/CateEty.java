package com.dream.myliu.liangwarehouse.entity;


import com.dream.myliu.liangwarehouse.greendao.CateDataEty;

/**
 * Created by Amethyst on 16/1/11/14/22.
 */
public class CateEty  {
    private CateDataEty data;

    public CateDataEty getData() {
        return data;
    }

    public void setData(CateDataEty data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.getNum_items() + "";
    }
}
