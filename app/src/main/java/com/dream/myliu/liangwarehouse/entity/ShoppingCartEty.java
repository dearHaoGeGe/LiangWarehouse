package com.dream.myliu.liangwarehouse.entity;

import java.io.Serializable;

/**
 * Created by Amethyst on 16/1/22/22/35.
 */
public class ShoppingCartEty extends OrderItemEty implements Serializable {
    private int count;
    private boolean isChecked=true;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
