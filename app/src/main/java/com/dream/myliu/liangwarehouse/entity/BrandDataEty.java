package com.dream.myliu.liangwarehouse.entity;

import java.util.List;

/**
 * Created by Amethyst on 16/1/11/19/27.
 */
public class BrandDataEty {
    private boolean has_more;
    private int num_items;
    private int next_start;
    private List<BrandItem> items;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getNum_items() {
        return num_items;
    }

    public void setNum_items(int num_items) {
        this.num_items = num_items;
    }

    public int getNext_start() {
        return next_start;
    }

    public void setNext_start(int next_start) {
        this.next_start = next_start;
    }

    public List<BrandItem> getItems() {
        return items;
    }

    public void setItems(List<BrandItem> items) {
        this.items = items;
    }
}
