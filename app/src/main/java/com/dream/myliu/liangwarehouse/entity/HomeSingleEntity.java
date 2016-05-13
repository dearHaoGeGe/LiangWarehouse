package com.dream.myliu.liangwarehouse.entity;

/**
 * Created by Amethyst on 16/1/6/10/41.
 */
public class HomeSingleEntity {
    private String home_id;
    private String content_type;
    private String content_id;
    private String pic_url;
    private String pos;
    private String topic_name;
    private String topic_url;

    public void setHome_id(String home_id) {
        this.home_id = home_id;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
    }

    public String getHome_id() {
        return home_id;
    }

    public String getContent_type() {
        return content_type;
    }

    public String getContent_id() {
        return content_id;
    }

    public String getPic_url() {
        return pic_url;
    }

    public String getPos() {
        return pos;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public String getTopic_url() {
        return topic_url;
    }
}
