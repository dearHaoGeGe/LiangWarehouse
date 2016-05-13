package com.dream.myliu.liangwarehouse.entity;


import java.util.List;

/**
 * Created by Amethyst on 16/1/13/14/45.
 */
public class MzgDateEty {
    private String key;
    private List<MzgInfosEty> dateEty;

    public MzgDateEty(List<MzgInfosEty> dateEty, String key) {
        this.dateEty = dateEty;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<MzgInfosEty> getDateEty() {
        return dateEty;
    }

    public void setDateEty(List<MzgInfosEty> dateEty) {
        this.dateEty = dateEty;
    }
}
