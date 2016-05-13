package com.dream.myliu.liangwarehouse.entity;

import java.util.List;

/**
 * Created by Amethyst on 16/1/6/10/37.
 */
public class HomeListEntity {
    private int sum;
    /**
     * home_id : 11
     * home_type : 4
     * order_num : 4
     * one : {"home_id":"11","content_type":"2","content_id":"80","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17500.jpg","pos":"1","topic_name":"","topic_url":""}
     * two : {"home_id":"11","content_type":"2","content_id":"77","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17503.jpg","pos":"2","topic_name":"","topic_url":""}
     * three : {"home_id":"11","content_type":"2","content_id":"79","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17502.jpg","pos":"3","topic_name":"","topic_url":""}
     * four : {"home_id":"11","content_type":"1","content_id":"882","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17505.jpg","pos":"4","topic_name":"向艺术家致敬的袜子 ","topic_url":"http://www.iliangcang.com/i/topicapp/201512315850"}
     */

    private List<HomeListDataEntity> data;

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setData(List<HomeListDataEntity> data) {
        this.data = data;
    }

    public int getSum() {
        return sum;
    }

    public List<HomeListDataEntity> getData() {
        return data;
    }
}
