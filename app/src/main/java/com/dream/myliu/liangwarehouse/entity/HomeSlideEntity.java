package com.dream.myliu.liangwarehouse.entity;

import java.util.List;

/**
 * Created by Amethyst on 16/1/6/10/31.
 */
public class HomeSlideEntity {
    private int sum;
    /**
     * slide_id : 74
     * content_type : 2
     * content_id : 76
     * pic_url : http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17498.jpg
     * order_num : 6
     * topic_name :
     * topic_url :
     */

    private List<HomeSlideDataEntity> data;

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setData(List<HomeSlideDataEntity> data) {
        this.data = data;
    }

    public int getSum() {
        return sum;
    }

    public List<HomeSlideDataEntity> getData() {
        return data;
    }

}
