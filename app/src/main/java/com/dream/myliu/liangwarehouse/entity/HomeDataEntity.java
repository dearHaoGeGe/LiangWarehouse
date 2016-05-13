package com.dream.myliu.liangwarehouse.entity;

/**
 * Created by Amethyst on 16/1/6/10/23.
 */
public class HomeDataEntity {
    private HomeSlideEntity slide;
    /**
     * sum : 3
     * data : [{"home_id":"11","home_type":"4","order_num":"4","one":{"home_id":"11","content_type":"2","content_id":"80","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17500.jpg","pos":"1","topic_name":"","topic_url":""},"two":{"home_id":"11","content_type":"2","content_id":"77","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17503.jpg","pos":"2","topic_name":"","topic_url":""},"three":{"home_id":"11","content_type":"2","content_id":"79","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17502.jpg","pos":"3","topic_name":"","topic_url":""},"four":{"home_id":"11","content_type":"1","content_id":"882","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17505.jpg","pos":"4","topic_name":"向艺术家致敬的袜子 ","topic_url":"http://www.iliangcang.com/i/topicapp/201512315850"}},{"home_id":"20","home_type":"1","order_num":"1","one":{"home_id":"20","content_type":"1","content_id":"855","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17506.jpg","pos":"1","topic_name":"把伦敦V&amp;A博物馆带回家","topic_url":"http://www.iliangcang.com/i/topicapp/201512200441"}},{"home_id":"21","home_type":"2","order_num":"0","one":{"home_id":"21","content_type":"2","content_id":"82","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17501.jpg","pos":"1","topic_name":"","topic_url":""},"two":{"home_id":"21","content_type":"2","content_id":"84","pic_url":"http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17504.jpg","pos":"2","topic_name":"","topic_url":""}}]
     */

    private HomeListEntity list;

    public void setSlide(HomeSlideEntity slide) {
        this.slide = slide;
    }

    public void setList(HomeListEntity list) {
        this.list = list;
    }

    public HomeSlideEntity getSlide() {
        return slide;
    }

    public HomeListEntity getList() {
        return list;
    }
}