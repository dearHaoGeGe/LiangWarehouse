package com.dream.myliu.liangwarehouse.entity;

/**
 * Created by Amethyst on 16/1/6/10/38.
 */
public class HomeListDataEntity {
    private String home_id;
    private String home_type;
    private String order_num;
    /**
     * home_id : 11
     * content_type : 2
     * content_id : 80
     * pic_url : http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17500.jpg
     * pos : 1
     * topic_name :
     * topic_url :
     */

    private HomeSingleEntity one;
    /**
     * home_id : 11
     * content_type : 2
     * content_id : 77
     * pic_url : http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17503.jpg
     * pos : 2
     * topic_name :
     * topic_url :
     */

    private HomeSingleEntity two;
    /**
     * home_id : 11
     * content_type : 2
     * content_id : 79
     * pic_url : http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17502.jpg
     * pos : 3
     * topic_name :
     * topic_url :
     */

    private HomeSingleEntity three;
    /**
     * home_id : 11
     * content_type : 1
     * content_id : 882
     * pic_url : http://7qn7hi.com1.z0.glb.clouddn.com/ware/orig/2/17/17505.jpg
     * pos : 4
     * topic_name : 向艺术家致敬的袜子
     * topic_url : http://www.iliangcang.com/i/topicapp/201512315850
     */

    private HomeSingleEntity four;

    public void setHome_id(String home_id) {
        this.home_id = home_id;
    }

    public void setHome_type(String home_type) {
        this.home_type = home_type;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public void setOne(HomeSingleEntity one) {
        this.one = one;
    }

    public void setTwo(HomeSingleEntity two) {
        this.two = two;
    }

    public void setThree(HomeSingleEntity three) {
        this.three = three;
    }

    public void setFour(HomeSingleEntity four) {
        this.four = four;
    }

    public String getHome_id() {
        return home_id;
    }

    public String getHome_type() {
        return home_type;
    }

    public String getOrder_num() {
        return order_num;
    }

    public HomeSingleEntity getOne() {
        return one;
    }

    public HomeSingleEntity getTwo() {
        return two;
    }

    public HomeSingleEntity getThree() {
        return three;
    }

    public HomeSingleEntity getFour() {
        return four;
    }
}
